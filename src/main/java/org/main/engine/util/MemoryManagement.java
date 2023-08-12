package org.main.engine.util;

import org.lwjgl.BufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import static org.lwjgl.system.MemoryStack.*;

public class MemoryManagement {
    private static final Logger logger = LoggerFactory.getLogger(MemoryManagement.class);

    public static ByteBuffer resourceToByteBuffer(final String resource)
    {
        logger.debug("Reading file as stream: " + resource);

        File file = new File(resource);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer buffer = BufferUtils.createByteBuffer((int) fileChannel.size() + 1);

            while (fileChannel.read(buffer) != -1)

            fileInputStream.close();
            fileChannel.close();
            buffer.flip();

            return buffer;
        } catch (IOException e) {
            logger.error("Unable to read file!");
            System.exit(-1);
        }

        return null;
    }

    public static IntBuffer putData(int[] data) { return (IntBuffer) stackGet().mallocInt(Integer.BYTES * data.length).put(data).flip(); }
    public static FloatBuffer putData(float[] data) { return (FloatBuffer) stackGet().mallocFloat(Float.BYTES * data.length).put(data).flip(); }
    public static DoubleBuffer putData(double[] data) { return (DoubleBuffer) stackGet().mallocDouble(Double.BYTES * data.length).put(data).flip(); }
}
