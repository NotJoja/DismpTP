
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class GrayscaleResourcePack {

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println(
                "Usage: java GrayscaleResourcePack <input> <output>"
            );
            return;
        }

        Path input = Paths.get(args[0]);
        Path output = Paths.get(args[1]);

        if (!Files.isDirectory(input)) {
            System.out.println("Input folder does not exist!");
            return;
        }

        int converted = 0;

        try (Stream<Path> files = Files.walk(input)) {

            for (Path file : (Iterable<Path>) files::iterator) {

                if (!Files.isRegularFile(file)) {
                    continue;
                }

                Path relative = input.relativize(file);
                Path destination = output.resolve(relative);

                Files.createDirectories(destination.getParent());

                String path = relative.toString()
                    .replace("\\", "/");

                // Only convert PNG files inside Minecraft textures
                if (path.startsWith("assets/minecraft/textures/")
                        && path.toLowerCase().endsWith(".png")) {

                    BufferedImage image = ImageIO.read(file.toFile());

                    if (image != null) {

                        BufferedImage gray = toGrayscale(image);

                        ImageIO.write(
                            gray,
                            "PNG",
                            destination.toFile()
                        );

                        converted++;

                        System.out.println(
                            "Converted: " + relative
                        );

                    } else {
                        Files.copy(
                            file,
                            destination,
                            StandardCopyOption.REPLACE_EXISTING
                        );
                    }

                } else {

                    // Copy everything else unchanged
                    Files.copy(
                        file,
                        destination,
                        StandardCopyOption.REPLACE_EXISTING
                    );
                }
            }
        }

        System.out.println();
        System.out.println("Finished!");
        System.out.println("Converted textures: " + converted);
        System.out.println("Output: " + output);
    }

    private static BufferedImage toGrayscale(
            BufferedImage original) {

        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage gray = new BufferedImage(
            width,
            height,
            BufferedImage.TYPE_INT_ARGB
        );

        for (int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                int pixel = original.getRGB(x, y);

                int alpha = (pixel >> 24) & 0xFF;
                int red   = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue  = pixel & 0xFF;

                // Perceptual grayscale / luminance
                int luminance = (int) Math.round(
                    0.2126 * red +
                    0.7152 * green +
                    0.0722 * blue
                );

                int newPixel =
                    (alpha << 24) |
                    (luminance << 16) |
                    (luminance << 8) |
                    luminance;

                gray.setRGB(x, y, newPixel);
            }
        }

        return gray;
    }
}
