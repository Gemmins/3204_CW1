package uk.ac.soton.ecs.ge1n21.hybridimages;

import org.openimaj.image.MBFImage;
public class MyHybridImages {
    /**
     * Compute a hybrid image combining low-pass and high-pass filtered images
     *
     * @param lowImage  the image to which apply the low pass filter
     * @param lowSigma  the standard deviation of the low-pass filter
     * @param highImage the image to which apply the high pass filter
     * @param highSigma the standard deviation of the low-pass component of computing the high-pass filtered image
     * @return the computed hybrid image
     */

    public static MBFImage makeHybrid(MBFImage lowImage, float lowSigma, MBFImage highImage, float highSigma) {


        MyConvolution highConv = new MyConvolution(makeGaussianKernel(highSigma));
        MyConvolution lowConv = new MyConvolution(makeGaussianKernel(lowSigma));

        //low pass on low image
        lowImage.internalAssign(lowImage.process(lowConv));

        MBFImage clone = highImage.process(highConv);

        //subtract low pass from high image
        highImage.subtractInplace(clone);

        //add both images
        lowImage.addInplace(highImage);

        return lowImage;
     }

    public static float[][] makeGaussianKernel(float sigma) {

        int size = (int) Math.floor(8*sigma+1);

        if (size%2 == 0) {
            size++;
        }

        float[][] kernel = new float[size][size];

        int centre = (int) (double) (size / 2);
        float sum = 0F;

        //calculate the value for each cell in the kernel
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                float g = gaussian(i-centre,j-centre,sigma);
                kernel[i][j] = g;
                sum += g;

            }
        }

        // Try here to get values to exactly sum to one but cant due to floating point error
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                kernel[i][j] = kernel[i][j]/sum;
            }
        }


        return kernel;
    }

    //calculation
    public static float gaussian(int x, int y, float sigma) {

        float fraction = (float) (1/(2*Math.PI*(Math.pow(sigma,2))));

        float exponent = (float) Math.exp((-1*(Math.pow(x,2) + Math.pow(y,2)))/(2*Math.pow(sigma,2)));

        return fraction*exponent;
    }


}
