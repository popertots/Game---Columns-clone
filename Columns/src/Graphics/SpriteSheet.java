package Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE, WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet Background = new SpriteSheet(
			"/Textures/Background.png", 333);
	public static SpriteSheet Brick = new SpriteSheet("/Textures/Brick.png", 5);
	public static SpriteSheet BrickRed = new SpriteSheet(
			"/Textures/BrickRed.png", 5);
	public static SpriteSheet BrickBlue = new SpriteSheet(
			"/Textures/BrickBlue.png", 5);
	public static SpriteSheet BrickGreen = new SpriteSheet(
			"/Textures/BrickGreen.png", 5);
	public static SpriteSheet BrickYellow = new SpriteSheet(
			"/Textures/BrickYellow.png", 5);
	public static SpriteSheet BrickPurple = new SpriteSheet("/Textures/BrickPurple.png", 5);
	public static SpriteSheet Over = new SpriteSheet("/Textures/gameover.png", 333);


	private Sprite[] Sprites;

	public Sprite[] getSprite() {
		return Sprites;
	}

	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		WIDTH = SIZE;
		HEIGHT = SIZE;
		pixels = new int[size * size];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height,
			int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		WIDTH = w;
		HEIGHT = h;
		SIZE = w;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		Sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa
								* spriteSize)
								+ (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				Sprites[frame++] = sprite;
			}
		}
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class
					.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}