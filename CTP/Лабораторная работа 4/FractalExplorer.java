import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import javax.swing.*;

public class FractalExplorer {

    private int display_size;
    private JImageDisplay mImage;
    private JComboBox<String> choose_fractal;
    private JButton bu_save;
    private JButton bu_reset;
    private FractalGenerator generate;
    private Rectangle2D.Double range;
    private int need_rows;


    private void enableUI(boolean val) {
		choose_fractal.setEnabled(val);
		bu_save.setEnabled(val);
		bu_reset.setEnabled(val);
    }
    
    private class FractalWorker extends SwingWorker<Object, Object> {

		private int mY;
		private int[] RGBValue;

		public FractalWorker(int y) {
			mY = y;
		}

		public Object doInBackground() {
			RGBValue = new int[display_size];
			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, display_size, mY);
			for (int x = 0; x < display_size; x++) {
				double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, display_size, x);
				int n_iters;
				int colors = 0;
				float hue;
				n_iters = generate.numIterations(xCoord, yCoord);
				if (n_iters >= 0) {
					hue = 0.7f + n_iters / 200f;
					colors = Color.HSBtoRGB(hue, 1f, 1f);
				}
				RGBValue[x] = colors;
			}
			return null;
		}

		public void done() {
			for (int x = 0; x < display_size; x++) {
				mImage.drawPixel(x, mY, RGBValue[x]);
			}
			mImage.repaint(0, 0, mY, display_size, 1);
			if (need_rows-- < 1) {
				enableUI(true);
			}
		}
	}


		private class FractalHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (e.getSource() == choose_fractal) {
				String selectedItem = choose_fractal.getSelectedItem().toString();
				if (selectedItem.equals("Mandelbrot"))  {
					generate = new Mandelbrot();
				}
				else if (selectedItem.equals("Tricorn")) {
					generate = new Tricorn();
				}
				else if (selectedItem.equals("BurningShip")) {
					generate = new BurningShip();
				}
				else {
					JOptionPane.showMessageDialog(null, "Error: Couldn't recognize choice");
					return;
				}
				range = new Rectangle2D.Double();
				generate.getInitialRange(range);
				drawFractal();
			}

			else if (cmd.equals("reset")) {
				range = new Rectangle2D.Double();
				generate.getInitialRange(range);

				drawFractal();
			}

			else if (cmd.equals("save")) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File f = chooser.getSelectedFile();
						String filePath = f.getPath();
						if (!filePath.toLowerCase().endsWith(".png")) {
							f = new File(filePath + ".png");
						}
						ImageIO.write(mImage.getImage(), "png", f);
					}
					catch (IOException exc) {
					}
				}
			}
		}
    }

    private class MouseHandler extends MouseAdapter {
	
		public void mouseClicked(MouseEvent e) {
			if (need_rows > 0) {
				return;
			}
			double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, display_size, e.getX());
			double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, display_size, e.getY());
			generate.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
			drawFractal();
		}
    }

    public FractalExplorer(int size) {
		display_size = size;
		generate = new Mandelbrot();
		range = new Rectangle2D.Double();
		generate.getInitialRange(range);
    }

    public void createAndShowGUI() {
		JFrame frame  = new JFrame("Лабораторная работа №4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout( new BorderLayout());
		FractalHandler handler = new FractalHandler();

		JPanel fractalPanel = new JPanel();
		JLabel label = new JLabel("Фрактал: ");
		fractalPanel.add(label);
		choose_fractal = new JComboBox<String>();
		choose_fractal.addItem("Mandelbrot");
		choose_fractal.addItem("Tricorn");
		choose_fractal.addItem("BurningShip");
		choose_fractal.addActionListener(handler);
		fractalPanel.add(choose_fractal);

		frame.getContentPane().add(fractalPanel, BorderLayout.NORTH);
		mImage = new JImageDisplay(display_size, display_size);
		frame.getContentPane().add(mImage, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		bu_save = new JButton("Save Image");
		bu_save.setActionCommand("save");
		bu_save.addActionListener(handler);
		buttonsPanel.add(bu_save);

		bu_reset = new JButton("Reset Display");
		bu_reset.setActionCommand("reset");
		bu_reset.addActionListener(handler);
		buttonsPanel.add(bu_reset);

		frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		frame.getContentPane().addMouseListener(new MouseHandler());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
    }
	
    public void drawFractal() {
		enableUI(false);
		for (int y = 0; y < display_size; y++) {
			FractalWorker worker = new FractalWorker(y);
			worker.execute();
		}
		mImage.repaint();
    }
    
    public static void main(String[] args) {
		FractalExplorer explorer = new FractalExplorer(800);
		explorer.createAndShowGUI();
		explorer.drawFractal();
    }

}
