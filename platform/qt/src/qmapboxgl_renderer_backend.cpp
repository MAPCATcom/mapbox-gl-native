#include "qmapboxgl_renderer_backend.hpp"

#include <QtGlobal>

#if QT_VERSION >= 0x050000
#include <QOpenGLContext>
#else
#include <QGLContext>
#endif

QMapboxGLRendererBackend::QMapboxGLRendererBackend(qreal pixelRatio,
        mbgl::DefaultFileSource &fs, mbgl::ThreadPool &tp, QMapboxGLSettings::GLContextMode mode)
    : m_renderer(*this, pixelRatio, fs, tp, static_cast<mbgl::GLContextMode>(mode))
{
    // FIXME: crete the renderer on the render thread
}

QMapboxGLRendererBackend::~QMapboxGLRendererBackend()
{
}

/*!
    Initializes an OpenGL extension function such as Vertex Array Objects (VAOs),
    required by Mapbox GL Native engine.
*/
mbgl::gl::ProcAddress QMapboxGLRendererBackend::getExtensionFunctionPointer(const char* name)
{
#if QT_VERSION >= 0x050000
    QOpenGLContext* thisContext = QOpenGLContext::currentContext();
    return thisContext->getProcAddress(name);
#else
    const QGLContext* thisContext = QGLContext::currentContext();
    return reinterpret_cast<mbgl::gl::ProcAddress>(thisContext->getProcAddress(name));
#endif
}

void QMapboxGLRendererBackend::updateParameters(std::shared_ptr<mbgl::UpdateParameters> newParameters)
{
    std::lock_guard<std::mutex> lock(m_updateMutex);
    m_updateParameters = std::move(newParameters);
}

void QMapboxGLRendererBackend::updateFramebufferObject(quint32 fbo)
{
    std::lock_guard<std::mutex> lock(m_updateFramebuffer);
    m_fbo = fbo;
}

void QMapboxGLRendererBackend::updateFramebufferSize(const QSize &fbSize)
{
    std::lock_guard<std::mutex> lock(m_updateFramebuffer);
    m_fbSize = fbSize;
}

void QMapboxGLRendererBackend::render()
{
    std::shared_ptr<mbgl::UpdateParameters> params;
    {
        // Lock on the parameters
        std::unique_lock<std::mutex> lock(m_updateMutex);
        if (!m_updateParameters) return;

        // Hold on to the update parameters during render
        params = m_updateParameters;
    }

    // The OpenGL implementation automatically enables the OpenGL context for us.
    mbgl::BackendScope scope { *this, mbgl::BackendScope::ScopeType::Implicit };

    m_renderer.render(*params);
}
