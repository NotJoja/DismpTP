Texturepack for the DisabledSMP project!

## Colorblind pack

Players with Color Blindness get the Colorblind pack on top. Besides grayscale textures it replaces the terrain
shader (`assets/minecraft/shaders/core/terrain.fsh`), because biome tints such as water are applied by the game and not
by the textures.

**Known limitation:** the shader override only works in vanilla. Clients that replace the terrain rendering, such as
Sodium, ignore it, so water still shows its biome color there. Leaves are fixed through models and work everywhere.
