package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Entity.Column;
import Graphics.Screen;
import Input.Keyboard;
import Level.world;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int width = 333;
	private static int height = width / 16 * 9;
	private static int scale = 10;
	private boolean running;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private world world;
	private Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	int pos = 0;
	static int fps = 0;

	private Main() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
		screen = new Screen(width, height);
		world = new world(width, height, screen);
		world.Columns.add(new Column(world, key, 0));

	}

	public static void main(String[] args) {
		// call the class
		Main Main = new Main();

		// setup the window properties
		Main.frame.setResizable(false);
		Main.frame.setTitle("Popertots Engine");
		Main.frame.add(Main);
		Main.frame.add(Main);
		Main.frame.pack();
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setLocationRelativeTo(null);
		Main.frame.setVisible(true);

		// start the Main
		Main.start();
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public void run() {
		long lt = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lt) / ns;
			lt = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			// display the correct pixels to the screen
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Popertots Columns | " + frames + " | "
						+ updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void render() {
		screen.clear();
		world.render();

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0xffffff));
		g.drawString("Score: " + world.Score, 10, 15);
		g.drawString("Level:  " + (world.Columns.get(0).level+1), 10, 30);
		g.dispose();
		bs.show();
	}

	private void update() {
		key.update();
		world.update();
	}
}
