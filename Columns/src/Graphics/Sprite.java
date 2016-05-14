package Graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width, height;

	public static Sprite Background = new Sprite(333, 333, SpriteSheet.Background);
	public static Sprite Brick = new Sprite(5, 5, SpriteSheet.Brick);
	public static Sprite BrickRed = new Sprite(5, 5, SpriteSheet.BrickRed);
	public static Sprite BrickBlue = new Sprite(5, 5, SpriteSheet.BrickBlue);
	public static Sprite BrickGreen = new Sprite(5, 5, SpriteSheet.BrickGreen);
	public static Sprite BrickYellow = new Sprite(5, 5, SpriteSheet.BrickYellow);
	public static Sprite BrickPurple = new Sprite(5, 5, SpriteSheet.BrickPurple);
	public static Sprite GameOver = new Sprite(333, 333, SpriteSheet.Over);


	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.width = size;
		this.height = size;
		SIZE = size;
		pixels = new int[size * size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		SIZE = width == height ? width : -1;
		pixels = new int[width * height];
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		this.width = size;
		this.height = size;
		SIZE = size;
		pixels = new int[size * size];
		setColor(color);
	}

	public Sprite(int width, int height, int color) {
		SIZE = 0;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = width == height ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
