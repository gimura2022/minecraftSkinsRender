package org.main.engine.util;

import org.lwjgl.BufferUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46C.*;

public class ImageSaver {
    public static void saveImage(String path, int width, int height) {
        int size = width * height * 3;

        IntBuffer imageBuffer = BufferUtils.createIntBuffer(size);

        glReadPixels(0, 0, width, height, GL_RGB, GL_UNSIGNED_INT, imageBuffer);

        int[] image = new int[size];

        for (int i = 0; i < size; i++) {  }

        imageBuffer = null;

        for (int color : image) {
            if (color != 0) System.out.println(color);
        }

        StringBuilder imageToString = new StringBuilder();
        for (int color : image) { imageToString.append(Integer.toString(color)).append("\n"); }

        FileUtils.writeFile("C:\\Users\\glebm\\Downloads\\image.txt", imageToString.toString());
    }
}
