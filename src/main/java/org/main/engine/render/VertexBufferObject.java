package org.main.engine.render;

import org.main.engine.util.MemoryManagement;

import static org.lwjgl.opengl.GL46C.*;

public class VertexBufferObject {
    private int id;
    private float[] data;
    private int usage;
    private BufferLayout bufferLayout;

    public VertexBufferObject(float[] data) {
        this.data = data;
        this.usage = GL_STATIC_DRAW;

        create();
        bind();
        putData();
    }

    private void create() { id = glCreateBuffers(); }
    public void destroy() { glDeleteBuffers(id); }

    private void putData() { glBufferData(GL_ARRAY_BUFFER, MemoryManagement.putData(data), usage); }

    private void bind() { glBindBuffer(GL_ARRAY_BUFFER, id); }
    public void unBind() { glBindBuffer(GL_ARRAY_BUFFER, 0); }

    public BufferLayout getBufferLayout() { return bufferLayout; }
    public void setBufferLayout(BufferLayout bufferLayout) { this.bufferLayout = bufferLayout; }
    public float[] getData() { return data; }
    public int getUsage() { return usage; }
}
