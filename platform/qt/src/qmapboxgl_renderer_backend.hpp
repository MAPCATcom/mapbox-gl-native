#pragma once

#include "qmapboxgl.hpp"

#include <mbgl/renderer/renderer.hpp>
#include <mbgl/renderer/renderer_backend.hpp>
#include <mbgl/storage/default_file_source.hpp>
#include <mbgl/util/shared_thread_pool.hpp>

#include <memory>
#include <mutex>

namespace mbgl {
class Renderer;
class UpdateParameters;
} // namespace mbgl

class QMapboxGLRendererBackend : public mbgl::RendererBackend
{
public:
    QMapboxGLRendererBackend(qreal pixelRatio, mbgl::DefaultFileSource &,
            mbgl::ThreadPool &, QMapboxGLSettings::GLContextMode);
    virtual ~QMapboxGLRendererBackend();

    void render();

    // mbgl::RendererBackend implementation
    void updateAssumedState() final {}
    void bind() final {}
    mbgl::Size getFramebufferSize() const final { return mbgl::Size(800, 600); }
    mbgl::gl::ProcAddress getExtensionFunctionPointer(const char*) final;
    void activate() {}
    void deactivate() {}

    // Thread-safe methods called by the Frontend
    void updateParameters(std::shared_ptr<mbgl::UpdateParameters>);
    void updateFramebufferObject(quint32 fbo);
    void updateFramebufferSize(const QSize &fbSize);

private:
    Q_DISABLE_COPY(QMapboxGLRendererBackend)

    mbgl::Renderer m_renderer;

    std::mutex m_updateMutex;
    std::shared_ptr<mbgl::UpdateParameters> m_updateParameters;

    std::mutex m_updateFramebuffer;
    quint32 m_fbo = -1;
    QSize m_fbSize = { 0, 0 };
};
