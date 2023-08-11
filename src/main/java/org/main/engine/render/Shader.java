package org.main.engine.render;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.main.engine.util.FileUtils;

import static org.lwjgl.opengl.GL46C.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shader {
    private int programId;

    public Shader(String vsSrc, String fsSrc) {
        Map<Integer, String> shaderSources = new HashMap<Integer, String>(2);

        shaderSources.put(1, FileUtils.readFile(vsSrc));
        shaderSources.put(2, FileUtils.readFile(fsSrc));

        compile(shaderSources);
    }

    private void compile(Map<Integer, String> shaderSources) {
        int program = glCreateProgram();

        List<Integer> shadersIds = new ArrayList<Integer>();
        int shaderIdIndex = 1;

        for (int i = 0; i < shaderSources.size(); i++) {
            int type = i == 0 ? GL_VERTEX_SHADER : i == 1 ? GL_FRAGMENT_SHADER : -1;
            String source = shaderSources.get(shaderIdIndex);

            int shader = glCreateShader(type);
            glShaderSource(shader, source);

            glCompileShader(shader);

            if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
                String log = glGetShaderInfoLog(shader, glGetShaderi(shader, GL_INFO_LOG_LENGTH));
                System.err.println("Error compile "+type+" shader:\n"+log);
                glDeleteShader(shader);

                System.exit(-1);
            }

            glAttachShader(program, shader);
            shaderIdIndex++;
        }

        glLinkProgram(program);

        if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
            String log = glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH));
            System.err.println("Error link program:\n"+log);

            for (int shaderId : shadersIds) {
                glDetachShader(program, shaderId);
                glDeleteShader(shaderId);
            }

            glDeleteProgram(program);

            System.exit(-1);
        }

        for (int shaderId : shadersIds) { glDetachShader(program, shaderId); }

        programId = program;
    }

    public void bind() { glUseProgram(programId); }
    public void unBind() { glUseProgram(0); }

    public void setUniform(String name, int val) { glUniform1i(glGetUniformLocation(programId, name), val); }
    public void setUniform(String name, int val1, int val2) { glUniform2i(glGetUniformLocation(programId, name), val1, val2); }
    public void setUniform(String name, int val1, int val2, int val3) { glUniform3i(glGetUniformLocation(programId, name), val1, val2, val3); }
    public void setUniform(String name, int val1, int val2, int val3, int val4) { glUniform4i(glGetUniformLocation(programId, name), val1, val2, val3, val4); }

    public void setUniform(String name, float val) { glUniform1f(glGetUniformLocation(programId, name), val); }
    public void setUniform(String name, float val1, float val2) { glUniform2f(glGetUniformLocation(programId, name), val1, val2); }
    public void setUniform(String name, float val1, float val2, float val3) { glUniform3f(glGetUniformLocation(programId, name), val1, val2, val3); }
    public void setUniform(String name, float val1, float val2, float val3, float val4) { glUniform4f(glGetUniformLocation(programId, name), val1, val2, val3, val4); }

    public void setUniform(String name, Matrix4f val) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
        val.get(buffer);

        glUniformMatrix4fv(glGetUniformLocation(programId, name), false, buffer);
    }
}
