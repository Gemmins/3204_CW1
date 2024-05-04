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

        int imageHeight = image.getHeight();
        int imageWidth = image.getWidth();
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;
        int kernelCentreHeight  = (int) Math.floor(((double)kernelHeight/2));
        int kernelCentreWidth = (int) Math.floor(((double)kernelWidth/2));

        //Rotate the kernel first
        float[][] rKernel = rotateKernel(kernel, kernelHeight, kernelWidth);

        FImage clone = image.clone();

        //loops position of kernel over whole image
        for (int i=0; i<imageHeight; i++) {
            for (int j=0; j<imageWidth; j++) {

                float nPixel = 0;

                //loops through all the pixels the kernel is over and adds their float value to a list
                for (int k = 0; k < kernelHeight; k++) {
                    for (int l = 0; l < kernelWidth; l++) {

                        int[] pixel = {(k - kernelCentreHeight) + i, (l - kernelCentreWidth) + j};
                        float val;
                        //if the coordinate of the pixel would be out of bounds then the value of it is 0
                        if(pixel[0] < 0 || pixel[1] < 0 || pixel[0] > imageHeight - 1|| pixel[1] > imageWidth -1) {
                            continue;
                        } else {
                            //pixel value * corresponding kernel value
                            val = image.getPixel(pixel[1], pixel[0])*rKernel[k][l];
                        }
                        nPixel += val;
                    }
                }
                clone.setPixel(j,i,nPixel);
            }
        }

        image.internalAssign(clone);

    }

    private float[][] rotateKernel(float[][] kernel, int height, int width) {

        float[][] rKernel = new float[height][width];
        //flips the array vertically
        for (int i = 0; i < height; i++) {
            rKernel[height-(i+1)] = kernel[i];
        }

        //reverses each of the rows?
        int i = 0;
        for (float[] row:rKernel) {
            float[] temp = new float[width];
            for (int j = 0; j < width; j++) {
                temp[j] = row[width - (j+1)];
            }
            rKernel[i] = temp;
            i++;
        }

        return rKernel;
    }






}