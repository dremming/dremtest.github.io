import java.awt.geom.Rectangle2D;


public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range) {
		range.x = -2;
		range.y = -2;

		range.width = 4;
		range.height = 4;
    }

    public int numIterations(double x, double y) {
		int count = 0;
		double real = 0;
		double image = 0;
		double z2 = 0;

		while (count < MAX_ITERATIONS && z2 < 4) {
			count++;
			double nextreal = Math.pow(real, 2) - Math.pow(image, 2) + x;
			double nextimage = -2 * real * image + y;
			z2 = Math.pow(nextreal, 2) + Math.pow(nextimage, 2);
			real = nextreal;
			image = nextimage;
		}

		return count < MAX_ITERATIONS ? count : -1;
    }

}
