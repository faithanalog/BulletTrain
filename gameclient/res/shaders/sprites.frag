#version 330
uniform sampler2D u_texture;
in vec2 v_texCoords;

out vec4 color;

void main() {
    color = texture(u_texture, v_texCoords);
}