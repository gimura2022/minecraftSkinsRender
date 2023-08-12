package org.main.engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.main.engine.input.Mouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.IntBuffer;

public class Window {
    private int height;
    private int width;

    private IntBuffer bufferedHeight;
    private IntBuffer bufferedWidth;

    private GLFWVidMode videoMode;

    private String title;
    private long windowId;

    public static Window instance;

    private static final Logger logger = LoggerFactory.getLogger(Window.class);

    public Window(int height, int width, String title) {
        instance = this;

        this.height = height;
        this.width = width;
        this.title = title;
    }

    public void createWindow() {
        logger.info("Creating window");

        if (!GLFW.glfwInit()) {
            logger.error("GLFW not installed!");
            System.exit(-1);
        }

        windowId = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        logger.debug("Window width: " + width);
        logger.debug("Window height: " + height);
        logger.debug("Window title: " + title);

        if (windowId == 0) {
            logger.error("GLFW error to create window!");
            System.exit(-1);
        }

        try (MemoryStack mem = MemoryStack.stackPush()) {
            bufferedHeight = BufferUtils.createIntBuffer(1);
            bufferedWidth = BufferUtils.createIntBuffer(1);

            GLFW.glfwGetWindowSize(windowId, bufferedWidth, bufferedHeight);
        } catch (Exception e) {
        }

        videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

        GLFW.glfwSetWindowTitle(windowId, title);
        GLFW.glfwSetWindowSize(windowId, width, height);
        GLFW.glfwSetWindowAspectRatio(windowId, width, height);
        GLFW.glfwSetWindowPos(windowId,
                ((videoMode.width() - bufferedWidth.get(0)) / 2),
                (videoMode.height() - bufferedHeight.get(0))/ 2);
        GLFW.glfwSetWindowSizeLimits(windowId, width, height, videoMode.width(), videoMode.height());

        GLFW.glfwMakeContextCurrent(windowId);
        GL.createCapabilities();
        GL11.glViewport(0, 0, bufferedWidth.get(0), bufferedHeight.get(0));

        Mouse.setMouseCallbacks(windowId);
    }

    public void updateWindow() {
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(windowId);
    }

    public void destroyWindow() {
        GLFW.glfwDestroyWindow(windowId);
        logger.info("Destroying window");
    }

    public boolean isCloseRequest() { return GLFW.glfwWindowShouldClose(windowId); }

    public void setHeight(int height) { this.height = height; }
    public void setWidth(int width) { this.width = width; }
    public void setTitle(String title) { this.title = title; }

    public static Window getWindow() { return instance; }

    public int getHeight() { return this.height; }
    public int getWidth() { return this.width; }
    public long getWindowId() { return this.windowId; }
    public String getTitle() { return this.title; }
}
