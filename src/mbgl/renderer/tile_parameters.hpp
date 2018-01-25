#pragma once

#include <mbgl/map/mode.hpp>

namespace mbgl {

namespace util {

class LanguageConfig;

} // namespace util

class TransformState;
class Scheduler;
class FileSource;
class AnnotationManager;
class ImageManager;
class GlyphManager;

class TileParameters {
public:
    const float pixelRatio;
    const MapDebugOptions debugOptions;
    const TransformState& transformState;
    Scheduler& workerScheduler;
    FileSource& fileSource;
    const MapMode mode;
    AnnotationManager& annotationManager;
    ImageManager& imageManager;
    GlyphManager& glyphManager;
    const uint8_t prefetchZoomDelta;
    std::shared_ptr<util::LanguageConfig> languageConfig;
};

} // namespace mbgl
