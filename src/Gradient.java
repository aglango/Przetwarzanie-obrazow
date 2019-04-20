import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Gradient {

    public void gradient1(WritableImage writableImage, String name) {

        WritableImage w = new WritableImage((int) writableImage.getWidth(), (int) writableImage.getHeight());
        WritableImage picture = erosion(writableImage);
        for (int k = 0; k < (int) writableImage.getHeight(); k++) {
            for (int l = 0; l < (int) writableImage.getWidth(); l++) {
                int color = DecHex.hexToDec(writableImage.getPixelReader().getColor(l, k).toString());
                int color2 = DecHex.hexToDec(picture.getPixelReader().getColor(l, k).toString());
                w.getPixelWriter().setColor(l, k, Color.web("#" + DecHex.decToHex(color - color2)));
            }
        }
        ImageHandler.saveImage(w, name);
    }

    public void gradient2(WritableImage writableImage, String name) {

        WritableImage w = new WritableImage((int) writableImage.getWidth(), (int) writableImage.getHeight());
        WritableImage picture = dilatation(writableImage);
        for (int k = 0; k < (int) writableImage.getHeight(); k++) {
            for (int l = 0; l < (int) writableImage.getWidth(); l++) {
                int color = DecHex.hexToDec(picture.getPixelReader().getColor(l, k).toString());
                int color2 = DecHex.hexToDec(writableImage.getPixelReader().getColor(l, k).toString());
                w.getPixelWriter().setColor(l, k, Color.web("#" + DecHex.decToHex(color - color2)));
            }
        }
        ImageHandler.saveImage(w, name);
    }

    public void gradient3(WritableImage writableImage, String name) {

        WritableImage w = dilatation(writableImage);
        WritableImage picture = erosion(writableImage);
        for (int k = 0; k < (int) writableImage.getHeight(); k++) {
            for (int l = 0; l < (int) writableImage.getWidth(); l++) {
                int color = DecHex.hexToDec(w.getPixelReader().getColor(l, k).toString());
                int color2 = DecHex.hexToDec(picture.getPixelReader().getColor(l, k).toString());
                w.getPixelWriter().setColor(l, k, Color.web("#" + DecHex.decToHex(color - color2)));
            }
        }
        ImageHandler.saveImage(w, name);
    }

    private WritableImage dilatation(WritableImage writableImage) {
        WritableImage w = new WritableImage((int) writableImage.getWidth(), (int) writableImage.getHeight());
        for (int i = 0; i < (int) writableImage.getHeight(); i++)
            for (int j = 0; j < (int) writableImage.getWidth(); j++)
                w.getPixelWriter().setColor(j, i, writableImage.getPixelReader().getColor(j, i));

        for (int k = 0; k < (int) writableImage.getHeight(); k++) {
            for (int l = 0; l < (int) writableImage.getWidth(); l++) {
                w.getPixelWriter().setColor(l, k, Color.web("#" + maximumColor(k, l, writableImage)));
            }
        }
        return w;
    }

    private WritableImage erosion(WritableImage writableImage) {
        WritableImage w = new WritableImage((int) writableImage.getWidth(), (int) writableImage.getHeight());

        for (int i = 0; i < (int) writableImage.getHeight(); i++)
            for (int j = 0; j < (int) writableImage.getWidth(); j++)
                w.getPixelWriter().setColor(j, i, writableImage.getPixelReader().getColor(j, i));

        for (int k = 0; k < (int) writableImage.getHeight(); k++) {
            for (int l = 0; l < (int) writableImage.getWidth(); l++) {
                w.getPixelWriter().setColor(l, k, Color.web("#" + minimumColor(k, l, writableImage)));
            }
        }
        return w;
    }

    private String minimumColor(int x0, int y0, WritableImage writableImage) {
        int minimum = Integer.MAX_VALUE;
        for (int i = x0 - 1; i <= x0 + 1; i++) {
            for (int j = y0 - 1; j <= y0 + 1; j++) {
                if (i < 0 || i >= (int) writableImage.getHeight() || j < 0 || j >= (int) writableImage.getWidth())
                    continue;
                int temp = DecHex.hexToDec(writableImage.getPixelReader().getColor(j, i).toString());
                if (temp < minimum)
                    minimum = temp;
                if (minimum == 0) return DecHex.decToHex(minimum);
            }
        }
        return DecHex.decToHex(minimum);
    }

    private String maximumColor(int x0, int y0, WritableImage writableImage) {
        int maximum = Integer.MIN_VALUE;
        for (int i = x0 - 1; i <= x0 + 1; i++) {
            for (int j = y0 - 1; j <= y0 + 1; j++) {
                if (i < 0 || i >= (int) writableImage.getHeight() || j < 0 || j >= (int) writableImage.getWidth())
                    continue;
                int temp = DecHex.hexToDec(writableImage.getPixelReader().getColor(j, i).toString());
                if (temp > maximum)
                    maximum = temp;
                if (maximum == 16777215) return DecHex.decToHex(maximum);
            }
        }
        return DecHex.decToHex(maximum);
    }

}
