#pragma once

#include <functional>
#include <mutex>
#include <unordered_set>
#include "online_file_source.hpp"

namespace mbgl {

namespace util {
class AsyncTask;
} // namespace util


struct LayerOptions {
    LayerOptions(bool _cycleRoad = false,
                 bool _cycleRoute = false);
    bool cycleRoad = false;
    bool cycleRoute = false;
};

const std::string MAPCAT_API_URL = "https://api.mapcat.com";

class MapInit {
public:
    MapInit(FileSource& _fileSource,
            const std::string& _apiUrl = MAPCAT_API_URL);
    std::unique_ptr<AsyncRequest> initVectorView(std::function<void(Response)> callback,
                                                 const std::string& accessToken,
                                                 const LayerOptions& layerOptions = LayerOptions());
private:
    FileSource& fileSource;
    std::string apiUrl;
};

} // namespace mbgl
