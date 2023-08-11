package org.main.engine.object;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.main.engine.render.VertexArrayObject;

public class GameObject {
    private VertexArrayObject vertexArrayObject;

    protected Vector3f position;
    protected Vector3f rotation;
    protected Vector3f scale;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = new Vector3f(position);
        this.rotation = new Vector3f(rotation);
        this.scale = new Vector3f(scale);
    }

    public GameObject setModel(VertexArrayObject vertexArrayObject) {
        this.vertexArrayObject = vertexArrayObject;
        return this;
    }

    public Matrix4f getModelMatrix() {
        Matrix4f modelMatrix = new Matrix4f()
                .translate(position)

                .rotate(rotation.x, new Vector3f(1, 0, 0))
                .rotate(rotation.y, new Vector3f(0, 1, 0))
                .rotate(rotation.z, new Vector3f(0, 0, 1))

                .scale(scale);

        return modelMatrix;
    }

    public Vector3f getPosition() { return position; }
    public Vector3f getRotation() { return rotation; }
    public Vector3f getScale() { return scale; }

    public VertexArrayObject getVertexArrayObject() { return vertexArrayObject; }
}
