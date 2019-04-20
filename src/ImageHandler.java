import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.*;

public class ImageHandler {
    public WritableImage loadImage(String file) {
        try {
            InputStream io = new FileInputStream(file);
            Image img = new Image(io);
            WritableImage ww = new WritableImage((int) img.getWidth(), (int) img.getHeight());
            for (int j = 0; j < (int) img.getWidth(); j++)
                for (int i = 0; i < (int) img.getHeight(); i++) {
                    Color color = img.getPixelReader().getColor(j, i);
                    ww.getPixelWriter().setColor(j, i, color);
                }
            return ww;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveImage(WritableImage writableImage, String name) {
        File file = new File(name);
        if (file != null) {
            try {
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }
}
