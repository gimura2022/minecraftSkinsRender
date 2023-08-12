#version 460

layout (location = 0) out vec4 out_color;

in vec4 color;
in vec3 fragPos;
in vec3 normal;

uniform int u_view_mode;
uniform vec3 u_light_position;
uniform vec4 u_light_color;

vec4 get_color_studio_mode() { return color; }

vec4 get_color_ambient_light_mode() {
    float ambientStrength = 0.21f;
    vec4 ambient = ambientStrength * u_light_color;

    return color * ambient;
}

vec4 get_color_diffuse_light_mode() {
    vec3 norm = normalize(normal);
    vec3 lightDir = normalize(u_light_position - fragPos);

    float diff = max(dot(norm, lightDir), 0);

    return (u_light_color * diff);
}

vec4 get_color_light_mode() {
    vec4 result = (get_color_ambient_light_mode() + get_color_diffuse_light_mode()) * color;

    return result;
}

void main() {
    if (u_view_mode == 0) out_color = get_color_studio_mode();

    else if (u_view_mode == 1) out_color = get_color_ambient_light_mode();
    else if (u_view_mode == 2) out_color = get_color_diffuse_light_mode();

    else if (u_view_mode == 3) out_color = get_color_light_mode();
}