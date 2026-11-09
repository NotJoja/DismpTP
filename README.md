Texturepack for the DisabledSMP project!

## Colorblind pack

Players with Color Blindness get the Colorblind pack on top of the main pack. It no longer turns everything gray, it
changes only what a colorblind player could not tell apart:

- **Ores** all look like coal ore (deepslate ores like deepslate coal ore). The nether ores are coal pieces on
  netherrack. Fake ores from Schizophrenia look the same, as they use the same blocks.
- **Tools and armor** look like their iron version: wooden, stone, golden, diamond, netherite and copper tools and
  spears, and leather, chainmail, golden, diamond, netherite, copper (and turtle) armor, horse armor and nautilus armor.
  Armor that is worn looks like iron armor too. This is done with item definitions (`items/`) and equipment
  definitions (`equipment/`).
- **Colored blocks** use grayscale textures: wool (also carpets, wool slabs and stairs), concrete and concrete powder,
  terracotta and glazed terracotta, stained glass and panes, candles, beds, shulker boxes, cushions and llama carpets.

Everything else keeps its normal colors.

### Classic mode

`ColorblindClassic/` is the old version of the pack: everything is grayscale (textures plus a terrain shader override).
Players with Color Blindness can switch to it in the game with `/dis menu` -> Color Blindness. Known limitation: the
shader override only works in vanilla, so clients that replace the terrain rendering (Sodium) still show colored water.

