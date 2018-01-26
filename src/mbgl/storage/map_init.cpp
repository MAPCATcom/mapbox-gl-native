#include <sstream>

#include <rapidjson/document.h>
#include <rapidjson/ostreamwrapper.h>
#include <rapidjson/writer.h>
#include "mbgl/storage/map_init.hpp"

using namespace std;


namespace mbgl {


LayerOptions::LayerOptions(bool _cycleRoad,
                           bool _cycleRoute):
    cycleRoad(_cycleRoad),
    cycleRoute(_cycleRoute)
{}


MapInit::MapInit(FileSource& _fileSource,
                 const string& _apiUrl):
    fileSource(_fileSource),
    apiUrl(_apiUrl)
{}

unique_ptr<AsyncRequest> MapInit::initVectorView(function<void(Response)> callback,
                                                 const string& accessToken,
                                                 const LayerOptions& layerOptions)
{
    rapidjson::Document postData;

    rapidjson::Value sources;
    sources.SetObject();
    sources.AddMember("base", "", postData.GetAllocator());
    sources.AddMember("ocean", "", postData.GetAllocator());
    sources.AddMember("relief", "", postData.GetAllocator());
    sources.AddMember("landcover", "", postData.GetAllocator());
    if (layerOptions.cycleRoad || layerOptions.cycleRoute) {
        sources.AddMember("cycle", "", postData.GetAllocator());
        if (!layerOptions.cycleRoad) {
            sources["cycle"] = "--,route";
        } else if (!layerOptions.cycleRoute) {
            sources["cycle"] = "--,road";
        }
    }

    postData.SetObject();
    postData.AddMember("layers", sources, postData.GetAllocator());
    postData.AddMember("type", 1, postData.GetAllocator());

    ostringstream oss;
    rapidjson::OStreamWrapper osw(oss);
    rapidjson::Writer<rapidjson::OStreamWrapper> writer(osw);
    postData.Accept(writer);
    string postDataString = oss.str();

    string url = apiUrl + "/api/mapinit/vector?api_key=" + accessToken;
    unique_ptr<AsyncRequest> request = fileSource.request(Resource::post(url, postDataString), callback);
    return request;
}

} // end of namespace mbgl
