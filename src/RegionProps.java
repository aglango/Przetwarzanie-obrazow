import javafx.scene.image.WritableImage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegionProps {

    public void regionprops(WritableImage writableImage, String name) throws IOException {

        File file = new File(name);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        centroid(writableImage, pw);
        boundingBox(writableImage, pw);
        pw.close();
    }

    public void centroid(WritableImage writableImage, PrintWriter pw){
        double sumBlue = 0;
        double sumGreen = 0;
        double sumRed = 0;
        double pixels = 0;
        for (int i=0; i< writableImage.getWidth(); i++){
            for(int j=0; j< writableImage.getHeight(); j++){
                sumRed += writableImage.getPixelReader().getColor(i,j).getRed()*i*j;
                sumGreen += writableImage.getPixelReader().getColor(i,j).getGreen()*i*j;
                sumBlue += writableImage.getPixelReader().getColor(i,j).getBlue()*i*j;
                pixels+=i*j;
            }
        }
        double centroidRed = sumRed/pixels;
        double centroidGreen = sumGreen/pixels;
        double centroidBlue = sumBlue/pixels;

        pw.println("Centroid");
        if (centroidRed==centroidGreen && centroidRed==centroidBlue){
            pw.println(centroidRed);
        }
        else{
            pw.println(centroidRed);
            pw.println(centroidGreen);
            pw.println(centroidBlue);
        }
    }

    public void boundingBox(WritableImage writableImage, PrintWriter pw) {
        int maxx = 0;
        int minx = (int) writableImage.getWidth();
        int maxy = 0;
        int miny = (int) writableImage.getHeight();
        int picture[][] = new int[minx][miny];
        for (int i = 0; i < writableImage.getWidth(); i++) {
            for (int j = 0; j < writableImage.getHeight(); j++) {
                if (writableImage.getPixelReader().getColor(i, j).getRed() > 0.05) {
                    picture[i][j] = 0;
                } else {
                    picture[i][j] = 1;
                }
                if (picture[i][j] == 1) {
                    if (i < minx) minx = i;
                    if (i > maxx) maxx = i;
                    if (j < miny) miny = j;
                    if (j > maxy) maxy = j;
                }
            }
        }
        if (minx > maxx) {
            int temp = minx;
            minx = maxx;
            maxx = temp;
        }
        if (miny > maxy) {
            int temp = miny;
            miny = maxy;
            maxy = temp;
        }

        pw.println("BoundingBox");
        pw.println(minx);
        pw.println(maxx);
        pw.println(miny);
        pw.println(maxy);
    }
}
