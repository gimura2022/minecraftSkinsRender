package org.main.engine.render;

import org.main.engine.Game;
import org.main.engine.object.GameObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lwjgl.opengl.GL46C.*;

public class Render {
    private static final Logger logger = LoggerFactory.getLogger(Render.class);

    public static void init() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void begin(Shader shader) {
        glClearColor(0.1f, 0.1f, 0.1f, 1f);
        glClear(GL_COLOR_BUFFER_BIT);

        shader.bind();
    }

    public static void end(Shader shader) { shader.unBind(); }

    public static void renderGameObject(GameObject gameObject, Shader shader) {
        if (gameObject.getVertexArrayObject() != null) {
            gameObject.getVertexArrayObject().bind();
//            gameObject.getTexture().bind();
            shader.bind();

            shader.setUniform("u_modelMatrix", gameObject.getModelMatrix());
            shader.setUniform("u_texture_sampler", 0);

            glDrawArrays(GL_TRIANGLES, 0, gameObject.getVertexArrayObject().getVbos().get(0).getData().length / 3);

            shader.unBind();
//            gameObject.getTexture().unBind();
            gameObject.getVertexArrayObject().unBind();
        } else { logger.warn("Vao object is null!"); }
    }

    public static void destroy() { glDisable(GL_BLEND); }
}
