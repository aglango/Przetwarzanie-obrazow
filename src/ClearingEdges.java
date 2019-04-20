import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClearingEdges {

    public void clearing(WritableImage wImage, String name)
    {
        WritableImage writableImage = wImage;
        int region = 1;
        int height = (int) writableImage.getHeight();
        int width = (int) writableImage.getWidth();
        int pixelsRegion[][] = new int[height][width];
        int picture[][] = pictureToArray(wImage);
        List<ArrayList> everyRegion = new ArrayList();
        everyRegion.add(new ArrayList());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (picture[i][j] != 0) {
                    List neighbors = new ArrayList();
                    for (int kx = -1; kx <= 1; kx++) {
                        for (int ky = -1; ky <= 1; ky++) {
                            if (i + kx < 0 || j + ky < 0 || i + kx > height - 1 || j + ky > width - 1 || (i + kx == 0 && j + ky == 0))
                                continue;
                            else if (pixelsRegion[i + kx][j + ky] != 0) neighbors.add(pixelsRegion[i + kx][j + ky]);
                        }
                    }
                    if (neighbors.isEmpty()) {
                        ArrayList list = new ArrayList();
                        list.add(region);
                        pixelsRegion[i][j] = region;
                        if (everyRegion.size() == region) {
                            everyRegion.add(new ArrayList());
                            everyRegion.get(region).add(i);
                            everyRegion.get(region).add(j);
                        }
                        region++;
                    } else {
                        int minIndex = neighbors.indexOf(Collections.min(neighbors));
                        pixelsRegion[i][j] = Integer.parseInt(neighbors.get(minIndex).toString());
                        everyRegion.get(pixelsRegion[i][j]).add(i);
                        everyRegion.get(pixelsRegion[i][j]).add(j);
                    }
                }
            }
        }

        int out[] = new int[1000];
        int n = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                writableImage.getPixelWriter().setColor(j, i, Color.valueOf("#ffffff"));
                if (picture[i][j] != 0) {
                    for (int kx = -1; kx <= 1; kx++) {
                        for (int ky = -1; ky <= 1; ky++) {
                            if (i + kx < 0 || j + ky < 0 || i + kx > height - 1 || j + ky > width - 1 || (i + kx == 0 && j + ky == 0)) {
                                continue;
                            } else if (pixelsRegion[i + kx][j + ky] != 0 && pixelsRegion[i + kx][j + ky] < pixelsRegion[i][j]) {
                                int tmp = pixelsRegion[i][j];
                                for (int index = 0; index < everyRegion.get(tmp).size(); index += 2) {
                                    pixelsRegion[Integer.parseInt(everyRegion.get(tmp).get(index).toString())][Integer.parseInt(everyRegion.get(tmp).get(index + 1).toString())] = pixelsRegion[i + kx][j + ky];
                                }
                            }
                        }
                    }
                    if (i < 3 || j < 3 || i > (height - 3) || j > (width - 3)) {
                        int temp = 0;
                        for (int l = 0; l <= n; l++) {
                            if (pixelsRegion[i][j] == out[l])
                                temp = 1;
                        }
                        if (temp == 0) {
                            n++;
                            out[n] = pixelsRegion[i][j];

                        }
                    }
                }
            }
        }
        for (int t = 0; t <= n; t++) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (pixelsRegion[i][j] == out[t]) {
                        writableImage.getPixelWriter().setColor(j, i, Color.valueOf("#000000"));
                    }
                }
            }
        }
        ImageHandler.saveImage(writableImage, name);
    }

    private int[][] pictureToArray(WritableImage writableImage) {

        Color white = Color.web("0xffffffff");
        int[][] pixels = new int[(int) writableImage.getHeight()][(int) writableImage.getWidth()];
        for (int i = 0; i < writableImage.getHeight(); i++)
            for (int j = 0; j < writableImage.getWidth(); j++) {

                if (writableImage.getPixelReader().getColor(j, i).equals(white)) pixels[i][j] = 1;
                else pixels[i][j] = 0;
            }
        return pixels;
    }
}
