import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

public class JImageDisplay extends JComponent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**/
	private BufferedImage mImage;

    public BufferedImage getImage() {
        return mImage;
    }

    public JImageDisplay(int width, int height) {
	    mImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Dimension size = new Dimension(width, height);
        super.setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mImage, 0, 0, mImage.getWidth(), mImage.getHeight(), null);
    }

    public void drawPixel(int x, int y, int rgbColor) {
	    mImage.setRGB(x, y, rgbColor);
    }

    public void clearImage() {
        for (int i = 0; i < mImage.getWidth(); i++) {
            for (int j = 0; j < mImage.getHeight(); j++) {
                drawPixel(i, j, 0);
            }
        }
    }
}