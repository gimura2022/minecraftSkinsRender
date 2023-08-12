package org.main.engine.render;

import org.lwjgl.BufferUtils;
import org.main.engine.util.ImageSaver;
import org.main.engine.util.MemoryManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL46C.*;

public class Texture {
    private int id;
    private int width, height;
    private int format, internalFormat;
    private int channels;
    private ByteBuffer image;
    private String path;

    private static final Logger logger = LoggerFactory.getLogger(Texture.class);

    public Texture(String path) {
        logger.info("Loading texture");

        this.path = path;

        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer channelBuffer = BufferUtils.createIntBuffer(1);

        image = stbi_load_from_memory(MemoryManagement.resourceToByteBuffer(path), widthBuffer, heightBuffer, channelBuffer, 0);

        width = widthBuffer.get(0);
        height = heightBuffer.get(0);
        channels = channelBuffer.get(0);

        if (channels == 4) {
            logger.debug("Texture format RGBA");

            internalFormat = GL_RGBA8;
            format = GL_RGBA;
        } else if (channels == 3) {
            logger.warn("Texture format RGB!");

            internalFormat = GL_RGB8;
            format = GL_RGB;
        }

        id = glCreateTextures(GL_TEXTURE_2D);
        glTextureStorage2D(id, 1, internalFormat, width, height);
    }

    public int getId() { return id; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getChannels() { return channels; }
    public ByteBuffer getImage() { return image; }
    public String getPath() { return path; }
}
