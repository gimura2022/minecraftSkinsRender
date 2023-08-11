package org.main.engine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.main.engine.Window;

public class Mouse {
    public static float mouseX;
    public static float mouseY;

    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public static boolean buttonPressed(int key) { return buttonDown(key) && !buttons[key]; }
    public static boolean buttonReleased(int key) { return !buttonDown(key) && buttons[key]; }

    public static void handelMouse() {
        for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
            buttons[i] = buttonDown(i);
        }
    }

    public static boolean buttonDown(int key) { return GLFW.glfwGetMouseButton(Window.getWindow().getWindowId(), key) == GLFW.GLFW_TRUE; }

    public static void setMouseCallbacks(long windowId) {
        setCursorPositionCallback(windowId);
    }

    public static void setCursorPositionCallback(long windowId) {
        GLFW.glfwSetCursorPosCallback(windowId, new GLFWCursorPosCallbackI() {
            @Override
            public void invoke(long arg1, double arg2, double arg3) {
                mouseX = (float) arg2;
                mouseY = (float) arg3;
            }
        });
    }
}
