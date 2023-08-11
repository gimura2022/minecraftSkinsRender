package org.main.engine.util;

import org.lwjgl.BufferUtils;

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
    public static ByteBuffer resourceToByteBuffer(final String resource) throws IOException
    {
        File file = new File(resource);

        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = BufferUtils.createByteBuffer((int) fileChannel.size() + 1);

        while (fileChannel.read(buffer) != -1)

        fileInputStream.close();
        fileChannel.close();
        buffer.flip();

        return buffer;
    }

    public static IntBuffer putData(int[] data) { return (IntBuffer) stackGet().mallocInt(Integer.BYTES * data.length).put(data).flip(); }
    public static FloatBuffer putData(float[] data) { return (FloatBuffer) stackGet().mallocFloat(Float.BYTES * data.length).put(data).flip(); }
    public static DoubleBuffer putData(double[] data) { return (DoubleBuffer) stackGet().mallocDouble(Double.BYTES * data.length).put(data).flip(); }
}
