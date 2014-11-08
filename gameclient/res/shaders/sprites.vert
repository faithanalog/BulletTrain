#version 330
uniform mat3 u_model;
uniform vec2 u_screen_size;

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec4 a_color;

out vec4 v_color;

void main() {
    vec3 pos = u_model * a_position;

    vec2 point = pos.xy / pos.z;

    vec2 screen_point = (point / u_screen_size * 2.0) - vec2(1.0, 1.0);
    gl_Position = vec4(screen_point, 0.0, 1.0);
}
