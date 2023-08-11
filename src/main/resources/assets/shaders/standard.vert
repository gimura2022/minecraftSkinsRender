#version 460

layout (location = 0) in vec3 attrib_position;
layout (location = 1) in vec4 attrib_color;

out vec4 color;
out vec3 position;

uniform mat4 u_modelMatrix;

void main() {
    color = attrib_color;
    position = attrib_position;

    gl_Position = u_modelMatrix * vec4(attrib_position, 1.0f);
}