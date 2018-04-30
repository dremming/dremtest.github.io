import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator{
    // Maximum iterations:
    public static final int MAX_ITERATIONS = 2000;
    /**
     * @param range - The range borders of the fractal
     */
    public void getInitialRange (Rectangle2D.Double range){
        range.x=-2;
        range.y=-2;
        range.width=range.height=4;
    }

    //Use formulas for fractal Mandelbrot
    /**
     * @param x - The abscissa of the point
     * @param y - The ordinate of the point
     */
    public int numIterations(double x, double y){
        double re=0;
        double im=0;
        for (int i=0; i<MAX_ITERATIONS; i++){
            double re1=re*re-im*im+x;
            double im1=-2*re*im+y;

            //If we have reached the Mandelbrot condition, we will return the number of iterations
            if ((im*im+re*re) > 4)
                return i;

            //The change the real and imaginary parts for the next iteration
            re=re1;
            im=im1;
        }
        return -1;
    }
    public String toString (){
        return "Tricorn";
    }
}