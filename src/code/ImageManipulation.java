package code;

import image.APImage;
import image.Pixel;
import java.lang.Math;

import static image.APImage.*;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        //APImage image = new APImage("cyberpunk2077.jpg");
        //image.draw();

        //grayScale("cyberpunk2077.jpg");

        //blackAndWhite("cyberpunk2077.jpg");

        //edgeDetection("cyberpunk2077.jpg", 20);

        //reflectImage("cyberpunk2077.jpg");

        rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String s) {
        APImage image2 = new APImage(s);

        int height = image2.getHeight();
        int width = image2.getWidth();

        for (int w = 0; w < width; w++){
            for (int h = 0; h < height; h++){
                Pixel temp = image2.getPixel(w, h);

                int avg = getAverageColour(temp);

                Pixel pixel = new Pixel(avg, avg, avg);
                image2.setPixel(w, h, pixel);
            }
        }
        image2.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int blue = pixel.getBlue();
        int green = pixel.getGreen();

        return((red + blue + green) / 3);
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String image) {
        APImage image3 = new APImage(image);

        int height = image3.getHeight();
        int width = image3.getWidth();

        for (int w = 0; w < width; w++){
            for (int h = 0; h < height; h++){
                Pixel temp = image3.getPixel(w, h);

                int avg = getAverageColour(temp);

                if (avg >= 128) avg = 255;
                else avg = 0;

                Pixel pixel = new Pixel(avg, avg, avg);
                image3.setPixel(w, h, pixel);
            }
        }
        image3.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String image, int threshold) {
        APImage image3 = new APImage(image);

        int height = image3.getHeight();
        int width = image3.getWidth();

        for (int w = width - 1; w > 0; w--){
            for (int h = height - 1; h > 0; h--){
                Pixel temp = image3.getPixel(w, h);
                Pixel templ = image3.getPixel(w - 1, h);
                Pixel tempd = image3.getPixel(w, h - 1);

                int avg = getAverageColour(temp);
                int avgl = getAverageColour(templ);
                int avgd = getAverageColour(tempd);


                Pixel pixel = new Pixel(avg, avg, avg);
                image3.setPixel(w, h, pixel);
            }
        }
        image3.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String image) {
        APImage image3 = new APImage(image);

        int height = image3.getHeight();
        int width = image3.getWidth();

        for (int w = width / 2; w > 0; w--){
            for (int h = height - 1; h > 0; h--){
                Pixel temp1 = image3.getPixel(w, h);
                Pixel temp2 = image3.getPixel(width - w, h);

                image3.setPixel(width - w, h, temp1);
                image3.setPixel(w, h, temp2);
            }
        }
        image3.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String image) {
        APImage image1 = new APImage(image);
        APImage image2 = new APImage(image1.getHeight(), image1.getWidth());

        for (int w = 1; w < image1.getWidth(); w++) {
            for (int h = 1; h < image1.getHeight(); h++) {
                Pixel pixel = image1.getPixel(w, h);
                image2.setPixel((image1.getHeight() - h - 1), w, pixel);
            }
        }
        image2.draw();
    }

}

