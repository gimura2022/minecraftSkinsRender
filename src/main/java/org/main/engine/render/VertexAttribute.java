package org.main.engine.render;

import static org.lwjgl.opengl.GL46C.*;

public class VertexAttribute {
    public static enum ShaderDataType {
        none(0),
        t_int(1), t_int2(2), t_int3(3), t_int4(4),
        t_float(5), t_float2(6), t_float3(7), t_float4(8),
        t_mat2(9), t_mat3(10), t_map4(11),
        t_boolean(12);

        public int type;
        ShaderDataType(int type) {
            this.type = type;
        }
    }

    public static int shaderDataTypeSizeInBytes(ShaderDataType shaderDataType) {
        switch (shaderDataType.type) {
            case 1: return 4;
            case 2: return 4 * 2;
            case 3: return 4 * 3;
            case 4: return 4 * 4;

            case 5: return 4;
            case 6: return 4 * 2;
            case 7: return 4 * 3;
            case 8: return 4 * 4;

            case 9: return 4 * 2 * 2;
            case 10: return 4 * 3 * 3;
            case 11: return 4 * 4 * 4;

            case 12: return 4;
        }

        return 0;
    }

    public static int convertShaderTypeData(ShaderDataType type)
    {
        switch (type.type)
        {
            case 5:     return GL_FLOAT;
            case 6:     return GL_FLOAT;
            case 7:     return GL_FLOAT;
            case 8:     return GL_FLOAT;
            case 1:     return GL_INT;
            case 2:     return GL_INT;
            case 3:     return GL_INT;
            case 4:     return GL_INT;
            case 9:     return GL_FLOAT;
            case 10:    return GL_FLOAT;
            case 11:    return GL_FLOAT;
            case 12:    return GL_BOOL;
        }

        if (type.type == 0)
        {
            System.out.println("Can't convert to open gl type, because input data is unknown!");
            Runtime.getRuntime().exit(-1);
        }
        return 0;
    }

    public String name;
    public ShaderDataType shaderDataType;
    public int convertedShaderDataType;
    public int offset;
    public int size;
    public boolean normalize;

    public VertexAttribute(String name, ShaderDataType shaderDataType, boolean normalize) {
        this.name = name;
        this.shaderDataType = shaderDataType;
        this.convertedShaderDataType = convertShaderTypeData(shaderDataType);
        this.normalize = normalize;
        this.size = shaderDataTypeSizeInBytes(shaderDataType);
        this.offset = 0;
    }

    public VertexAttribute(String name, ShaderDataType shaderDataType) { this(name, shaderDataType, false); }

    public int getElementAttribSize() {
        switch (shaderDataType.type) {
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
            case 4: return 4;

            case 5: return 1;
            case 6: return 2;
            case 7: return 3;
            case 8: return 4;

            case 9: return 2 * 2;
            case 10: return 3 * 3;
            case 11: return 4 * 4;

            case 12: return 1;
        }

        return 0;
    }
}
