package uk.ac.soton.ecs.ge1n21.hybridimages;

import org.openimaj.image.*;
import java.io.File;
import java.io.IOException;


public class App {
    public static void main( String[] args ) throws IOException {


        MBFImage image1 = ImageUtilities.readMBF(new File("data/roast.bmp"));

        MBFImage image2 = ImageUtilities.readMBF(new File("data/chickern.bmp"));

        DisplayUtilities.display(image1);

        DisplayUtilities.display(image2);


        DisplayUtilities.display(MyHybridImages.makeHybrid(image1, 4F, image2, 2.5F));

        //File hybrid = new File("data/hybrid1");

        MBFImage hybridI = MyHybridImages.makeHybrid(image1,4F,image2,2.5F);

        DisplayUtilities.display(hybridI);


    }
}
