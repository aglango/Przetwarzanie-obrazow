import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Kirsch {
    public void kirsch(WritableImage writableImage, String name) {

        WritableImage w = new WritableImage((int) writableImage.getWidth(), (int) writableImage.getHeight());

        for (int i = 1; i < (int) writableImage.getHeight() - 1; i++) {
            for (int j = 1; j < (int) writableImage.getWidth() - 1; j++) {
                w.getPixelWriter().setColor(j, i, filtrate(writableImage, j, i));
            }
        }
        ImageHandler.saveImage(w, name);
    }

    private Color filtrate(WritableImage writableImage, int i, int j)
    {
        int[][] mask1 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
        int[][] mask2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
        int[][] mask3 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
        int[][] mask4 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
        int[][] mask5 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
        int[][] mask6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
        int[][] mask7 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
        int[][] mask8 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
        Color temp;

        double maxBlue;
        double maxRed;
        double maxGreen;
        maxBlue = filtBlue(writableImage, j, i, mask1);
        double tmp = filtBlue(writableImage, j, i, mask2);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask3);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask4);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask5);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask6);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask7);
        if (tmp > maxBlue) maxBlue = tmp;
        tmp = filtBlue(writableImage, j, i, mask8);
        if (tmp > maxBlue) maxBlue = tmp;

        maxRed = filtRed(writableImage, j, i, mask1);
        tmp = filtRed(writableImage, j, i, mask2);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask3);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask4);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask5);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask6);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask7);
        if (tmp > maxRed) maxRed = tmp;
        tmp = filtRed(writableImage, j, i, mask8);
        if (tmp > maxRed) maxRed = tmp;

        maxGreen = filtGreen(writableImage, j, i, mask1);
        tmp = filtGreen(writableImage, j, i, mask2);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask3);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask4);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask5);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask6);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask7);
        if (tmp > maxGreen) maxGreen = tmp;
        tmp = filtGreen(writableImage, j, i, mask8);
        if (tmp > maxGreen)maxGreen = tmp;

        maxBlue = (maxBlue + 15) / 30;
        maxGreen = (maxGreen + 15) / 30;
        maxRed = (maxRed + 15) / 30;
        temp = Color.color(maxRed, maxGreen, maxBlue);
        return temp;
    }

    private double filtBlue(WritableImage writableImage, int i, int j, int[][] mask) {
        double Blue1 = writableImage.getPixelReader().getColor(j - 1, i - 1).getBlue() * mask[0][0];
        Blue1 += writableImage.getPixelReader().getColor(j - 1, i).getBlue() * mask[0][1];
        Blue1 += writableImage.getPixelReader().getColor(j - 1, i + 1).getBlue() * mask[0][2];
        Blue1 += writableImage.getPixelReader().getColor(j, i - 1).getBlue() * mask[1][0];
        Blue1 += writableImage.getPixelReader().getColor(j, i + 1).getBlue() * mask[1][2];
        Blue1 += writableImage.getPixelReader().getColor(j + 1, i - 1).getBlue() * mask[2][0];
        Blue1 += writableImage.getPixelReader().getColor(j + 1, i).getBlue() * mask[2][1];
        Blue1 += writableImage.getPixelReader().getColor(j + 1, i + 1).getBlue() * mask[2][2];
        return Blue1;
    }

    private double filtRed(WritableImage writableImage, int i, int j, int[][] mask) {
        double Red1 = writableImage.getPixelReader().getColor(j - 1, i - 1).getRed() * mask[0][0];
        Red1 += writableImage.getPixelReader().getColor(j - 1, i).getRed() * mask[0][1];
        Red1 += writableImage.getPixelReader().getColor(j - 1, i + 1).getRed() * mask[0][2];
        Red1 += writableImage.getPixelReader().getColor(j, i - 1).getRed() * mask[1][0];
        Red1 += writableImage.getPixelReader().getColor(j, i + 1).getRed() * mask[1][2];
        Red1 += writableImage.getPixelReader().getColor(j + 1, i - 1).getRed() * mask[2][0];
        Red1 += writableImage.getPixelReader().getColor(j + 1, i).getRed() * mask[2][1];
        Red1 += writableImage.getPixelReader().getColor(j + 1, i + 1).getRed() * mask[2][2];
        return Red1;
    }

    private double filtGreen(WritableImage writableImage, int i, int j, int[][] mask) {
        double Green1 = writableImage.getPixelReader().getColor(j - 1, i - 1).getGreen() * mask[0][0];
        Green1 += writableImage.getPixelReader().getColor(j - 1, i).getGreen() * mask[0][1];
        Green1 += writableImage.getPixelReader().getColor(j - 1, i + 1).getGreen() * mask[0][2];
        Green1 += writableImage.getPixelReader().getColor(j, i - 1).getGreen() * mask[1][0];
        Green1 += writableImage.getPixelReader().getColor(j, i + 1).getGreen() * mask[1][2];
        Green1 += writableImage.getPixelReader().getColor(j + 1, i - 1).getGreen() * mask[2][0];
        Green1 += writableImage.getPixelReader().getColor(j + 1, i).getGreen() * mask[2][1];
        Green1 += writableImage.getPixelReader().getColor(j + 1, i + 1).getGreen() * mask[2][2];
        return Green1;
    }
}
