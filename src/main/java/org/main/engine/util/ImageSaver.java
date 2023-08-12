package org.main.engine.util;

import org.lwjgl.BufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL46C.*;

public class ImageSaver {
    private static final Logger logger = LoggerFactory.getLogger(ImageSaver.class);

    public static void saveImage(String path, int width, int height) {
        logger.trace("Saving image");



        logger.debug("Save image to " + path);
    }
}
