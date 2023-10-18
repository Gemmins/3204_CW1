package uk.ac.soton.ecs.ge1n21.hybridimages;

import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;
    public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
        private float[][] kernel;
        public MyConvolution(float[][] kernel) {
            //note that like the image pixels kernel is indexed by [row][column]
            this.kernel = kernel;
        }

    @Override
    public void processImage(FImage image) {
        //convolve image with kernel and store result back in image
        //hint: use FImage#internalAssign(FImage) to set the contents of your temporary buffer image to the image.
        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;

        int widthPad = imageWidth%kernelWidth;
        int heightPad = imageHeight%kernelHeight;

        

    }

}