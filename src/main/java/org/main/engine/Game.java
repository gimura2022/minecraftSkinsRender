package org.main.engine;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.main.engine.input.Keyboard;
import org.main.engine.object.GameObject;
import org.main.engine.render.*;
import org.main.engine.render.VertexAttribute.ShaderDataType;
import org.main.engine.util.ImageSaver;

public class Game {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;
    public static final String TITLE = "3d graphics";

    public static final boolean DEMO = true;

    private Shader shader;

    private Window window;

    private int viewMode;

    public void run() {
        init();
        update();
    }

    private void init() {
        // Logger logger = Logger.getLogger("MAIN");
        // logger.setLevel(Level.ALL);

        window = new Window(HEIGHT, WIDTH, TITLE);
        window.createWindow();

        Render.init();

        shader = new Shader("\\assets\\shaders\\standard.vert", "\\assets\\shaders\\standard.frag");
    }

    private void update() {
        float objectR = 0.7f;
        float objectG = 0.7f;
        float objectB = 0.7f;

        float[] vertex = {
            0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,

            0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,


            0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,

            0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
            0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,


           -0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,

           -0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,


            0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,

            0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,


            0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,

            0.4f,-0.4f,-0.4f,  objectR, objectG, objectB, 1f,
            0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f,-0.4f, 0.4f,  objectR, objectG, objectB, 1f,


            0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,

            0.4f, 0.4f,-0.4f,  objectR, objectG, objectB, 1f,
            0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
           -0.4f, 0.4f, 0.4f,  objectR, objectG, objectB, 1f,
        };

        VertexArrayObject vertexArrayObject = new VertexArrayObject();
        VertexBufferObject vertexBufferObject = new VertexBufferObject(vertex);

        vertexBufferObject.setBufferLayout(new BufferLayout(
                new VertexAttribute("attrib_position", ShaderDataType.t_float3),
                new VertexAttribute("attrib_color", ShaderDataType.t_float4)
        ));

        vertexArrayObject.putBuffer(vertexBufferObject);

        GameObject gameObject = new GameObject(
                new Vector3f(0, 0, 0), // Position
                new Vector3f(0.5f, 0.5f, 0.5f), // Rotation
                new Vector3f(1, 1, 1)  // Scale
        );

        gameObject.setModel(vertexArrayObject);

        float speed = 0.02f;

        while (!window.isCloseRequest() && DEMO) {
            if (Keyboard.keyPressed(GLFW.GLFW_KEY_M)) { viewMode++; }
            if (viewMode > 1) { viewMode = 0; }

            Keyboard.handelKeyboard();
//            Mouse.handelMouse();

            gameObject.getRotation().x += speed;
            gameObject.getRotation().y += speed;
            gameObject.getRotation().z += speed;

            Render.begin(shader);

            shader.setUniform("u_view_mode", viewMode);
            shader.setUniform("u_light_position", 0, 0.7f, 0.3f);
            shader.setUniform("u_light_rotation", 0, 0.7f, 0.3f);

            Render.renderGameObject(gameObject, shader);

            Render.end(shader);

            window.updateWindow();

            try { Thread.sleep(16); }
            catch (InterruptedException e) {}
        }

        Render.begin(shader);

        shader.setUniform("u_view_mode", viewMode);
        shader.setUniform("u_light_position", 0, 0.7f, 0.3f);
        shader.setUniform("u_light_rotation", 0, 0.7f, 0.3f);

        Render.renderGameObject(gameObject, shader);

        Render.end(shader);

        window.updateWindow();

//        ImageSaver.saveImage("C:\\Users\\glebm\\Downloads\\image.txt", WIDTH, HEIGHT);

        Render.destroy();
        window.destroyWindow();
    }
}
