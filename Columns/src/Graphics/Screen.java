package Graphics;

public class Screen {

	// setup location handling variables
	public static int width;
	public static int height;
	public int[] pixels;

	public Screen(int width, int height) {
		Screen.width = width;
		Screen.height = height;
		pixels = new int[width * height];
	}

	public void renderEntity(int xp, int yp, Sprite sprite, int flip) {
		for (int y = 0; y < sprite.getHeight(); y++) {

			int ya = y + yp;
			int ys = y;
			if (flip == 2) {
				ys = 31 - y;
			}
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1) {
					xs = 31 - x;
				}
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) {
					break;
				}
				if (xa < 0) {
					xa = 0;
				}
				int pColor = sprite.pixels[xs + ys * sprite.getWidth()];
				if (pColor != 0xffff00ff) {
					pixels[xa + ya * width] = pColor+0x111111;
				}
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				if (sprite.pixels[x + y * sprite.getWidth()] != 0xffff00ff) {
					pixels[xa + ya * width] = sprite.pixels[x + y
							* sprite.getWidth()];
				}
			}
		}
	}

	// clear the screen to black
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}
}