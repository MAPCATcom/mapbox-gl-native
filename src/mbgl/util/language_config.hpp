#pragma once

#include <string>
#include <mbgl/util/feature.hpp>


namespace mbgl {

namespace util {


class LanguageConfig { //not thread-safe
public:
    LanguageConfig();
    void setLanguage(const std::string& value);
    const std::string& getLanguage() const;
    std::string getText(const mbgl::PropertyMap& featureProperties, float zoom) const;
private:
    struct NameRule {
        NameRule(int rule_, bool canRead_, bool canTransliterate_, bool isLatin_);
        NameRule(int rule_);
        NameRule();
        int rule;
        bool canRead;
        bool canTransliterate;
        bool isLatin;
    };
    void extendLanguageToCodepage();
    int getCodepageOfLanguage(const std::string& languageCode) const;
    std::string getMainLanguageCode(const std::string& languageCode) const; //"ar-DZ" -> "ar"
    bool canReadOther(int languageCodeA, int languageCodeB) const;
    bool isLatin(int lc) const;
    NameRule getNameRule(const std::string& dataCountryCode) const;
    std::string getUiName(const mbgl::PropertyMap& featureProperties, const std::string& nameProperty, const NameRule& nameRule) const;
    const std::string& extractStringProperty(const mbgl::PropertyMap& featureProperties, const std::string& propertyName) const; //can throw ENoPropertyError
    std::string transliterate(const std::string& input) const;

    std::string languageCode;
};


} // namespace util

} // namespace mbgl
