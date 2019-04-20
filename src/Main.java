import javafx.scene.image.WritableImage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ImageHandler image = new ImageHandler();
        RegionProps region = new RegionProps();
        Kirsch filt = new Kirsch();
        Gradient gradient = new Gradient();
        ClearingEdges edges = new ClearingEdges();

        WritableImage rice =image.loadImage("rice.png");
        WritableImage cameraman = image.loadImage("cameraman.png");
        WritableImage lena = image.loadImage("lena.png");

        try {
            region.regionprops(cameraman, "cameraman_RegionProps.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            region.regionprops(lena, "lena_RegionProps.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        filt.kirsch(cameraman, "cameraman_Kirsch.png");
        filt.kirsch(lena, "lena_Kirsch.png");

        gradient.gradient1(rice, "rice_Gradient1.png");
        gradient.gradient2(rice, "rice_Gradient2.png");
        gradient.gradient3(rice,"rice_Gradient3.png");

        gradient.gradient1(cameraman, "cameraman_Gradient1.png");
        gradient.gradient2(cameraman, "cameraman_Gradient2.png");
        gradient.gradient3(cameraman, "cameraman_Gradient3.png");

        edges.clearing(rice, "rice_ClearingEdges.png");
    }


}