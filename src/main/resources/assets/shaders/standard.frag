#version 460

layout (location = 0) out vec4 out_color;

in vec4 color;
in vec3 position;

uniform int u_view_mode;
uniform vec3 u_light_position;
uniform vec3 u_light_rotation;

vec4 get_color_studio_mode() { return color; }
vec4 get_color_light_mode() {
    vec4 color_out = vec4(0.0f);
    vec3 lightDirection = normalize(u_light_position);

//    float diffuseAngle = max(dot(n, lightDirection), 0.0);



    return color_out + color;
}

void main() {
    if (u_view_mode == 0) out_color = get_color_studio_mode();
    else if (u_view_mode == 1) out_color = get_color_light_mode();
}