package org.main.engine.input;

import org.lwjgl.glfw.GLFW;
import org.main.engine.Window;

public class Keyboard {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    public static boolean keyPressed(int key) { return keyDown(key) && !keys[key]; }
    public static boolean keyReleased(int key) { return !keyDown(key) && keys[key]; }

    public static void handelKeyboard() {
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            keys[i] = keyDown(i);
        }
    }

    public static boolean keyDown(int key) { return GLFW.glfwGetKey(Window.getWindow().getWindowId(), key) == GLFW.GLFW_TRUE; }
}
