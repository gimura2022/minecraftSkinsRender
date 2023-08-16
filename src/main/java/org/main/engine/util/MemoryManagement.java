package org.main.engine.util;

import org.lwjgl.BufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import static org.lwjgl.system.MemoryStack.*;

public class MemoryManagement {
    public static IntBuffer putData(int[] data) { return (IntBuffer) stackGet().mallocInt(Integer.BYTES * data.length).put(data).flip(); }
    public static FloatBuffer putData(float[] data) { return (FloatBuffer) stackGet().mallocFloat(Float.BYTES * data.length).put(data).flip(); }
    public static DoubleBuffer putData(double[] data) { return (DoubleBuffer) stackGet().mallocDouble(Double.BYTES * data.length).put(data).flip(); }
}
