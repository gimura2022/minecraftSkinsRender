#version 460

layout (location = 0) in vec3 attrib_position;
layout (location = 1) in vec4 attrib_color;
layout (location = 2) in vec3 attrib_normal;

out vec4 color;
out vec3 fragPos;
out vec3 normal;

uniform mat4 u_modelMatrix;

void main() {
    color = attrib_color;
    normal = vec3(u_modelMatrix * vec4(attrib_normal, 1.0f));
    fragPos = vec3(u_modelMatrix * vec4(attrib_position, 1.0f));

    gl_Position = u_modelMatrix * vec4(attrib_position, 1.0f);
}