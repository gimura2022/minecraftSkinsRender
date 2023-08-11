package org.main.engine.render;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL46C.*;

public class VertexArrayObject {
    private int id;
    private List<VertexBufferObject> vbos = new ArrayList<VertexBufferObject>();

    public VertexArrayObject() {
        create();
        bind();
    }

    public void create() { id = glCreateVertexArrays(); }
    public void destroy() {
        glDeleteVertexArrays(id);

        for (VertexBufferObject vertexBufferObject : vbos) { vertexBufferObject.destroy(); }
        vbos.clear();
    }

    public void bind() { glBindVertexArray(id); }
    public void unBind() { glBindVertexArray(0); }

    private static void addAttribute(int attributeId, VertexAttribute attribute, int bufferStride) {
        glEnableVertexAttribArray(attributeId);
        glVertexAttribPointer(
                attributeId,
                attribute.getElementAttribSize(),
                attribute.convertedShaderDataType,
                attribute.normalize ? true : false,
                bufferStride,
                attribute.offset
        );
    }

    public void putBuffer(VertexBufferObject vertexBufferObject) {
        vertexBufferObject.getBufferLayout().calcOffsetAndStride();

        int attributeId = 0;
        for (VertexAttribute vertexAttribute : vertexBufferObject.getBufferLayout().getAttributes()) {
            addAttribute(attributeId, vertexAttribute, vertexBufferObject.getBufferLayout().getStride());
            attributeId++;
        }

        vbos.add(vertexBufferObject);
    }

    public List<VertexBufferObject> getVbos() { return vbos; }
}
