#version 150

uniform sampler2D Color;
uniform sampler2D Depth;
uniform sampler2D Grainy;
uniform sampler2D Manifold;
uniform sampler2D Milky;
uniform sampler2D Swirl;
uniform sampler2D SpaceSkybox;
uniform vec3 CameraPos;
uniform vec2 ScreenSize;
uniform mat4 InverseProjMat;
uniform mat4 InverseModelViewMat;
uniform float FarPlane;

in vec4 vertexColor;

out vec4 fragColor;

vec3 projectAndDivide(mat4 projectionMatrix, vec3 position) {
    vec4 homogeneousPos = projectionMatrix * vec4(position, 1.0);
    return homogeneousPos.xyz / homogeneousPos.w;
}

void main() {
    vec2 normCoords = gl_FragCoord.xy / ScreenSize;
    vec3 screenPos = vec3(normCoords, texture(Depth, normCoords));
    vec3 ndcPos = screenPos * 2.0 - 1.0;
    vec3 viewPos = projectAndDivide(InverseProjMat, ndcPos);
    vec3 eyePlayerPos = vec3(mat3(InverseModelViewMat) * viewPos);
    vec3 eyeCameraPosition = CameraPos + InverseModelViewMat[3].xyz;
    vec3 worldPos = eyePlayerPos + eyeCameraPosition;
    vec3 bloodPos = vec3(703, 72, -1336);

    //if (length(worldPos - bloodPos) > 175) {
    //    discard;
    //}

    if (length(viewPos) > FarPlane) {
        discard;
    }


    vec4 color = texture(Color, normCoords);
    vec4 space = texture(SpaceSkybox, normCoords);

    vec2 sampleCoords = vec2((worldPos.x + worldPos.y), (worldPos.z + worldPos.y));
    vec4 grainy = texture(Grainy, sampleCoords * 0.05575);
    vec4 manifold = texture(Manifold, sampleCoords * 0.0075);
    vec4 milky = texture(Milky, sampleCoords * 0.0025);
    vec4 swirl = texture(Swirl, sampleCoords * 0.0175);
    //fragColor = color * vertexColor;

    float noise = (grainy.r * manifold.r * milky.r * swirl.r * 1.75);

    if (noise > 0) {
        noise = 1;
    }

    vec4 final = mix(color, vec4(space.xyz, 1.0), noise);

    fragColor = final;
}
