#version 460

layout (location = 0) out vec4 out_color;

in vec4 color;
in vec3 position;

uniform int u_view_mode;
uniform vec3 u_light_position;
uniform vec3 u_light_rotation;

vec4 get_color_studio_mode() { return color; }
vec4 get_color_light_mode() {


    return color;
}

void main() {
    if (u_view_mode == 0) out_color = get_color_studio_mode();
    else if (u_view_mode == 1) out_color = get_color_light_mode();
}