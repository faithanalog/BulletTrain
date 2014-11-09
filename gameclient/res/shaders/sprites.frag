#version 330
uniform sampler2D u_texture;
uniform vec4 u_color;
in vec2 v_texCoords;

out vec4 color;

void main() {
    color = texture(u_texture, v_texCoords) * u_color;
    if (color.a == 0.0) {
        discard;
    }
}