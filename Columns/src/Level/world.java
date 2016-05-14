package Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entity.Column;
import Graphics.Screen;
import Graphics.Sprite;

public class world {

	public int width, height, num = 0, c = 0, Score = 0;
	private Screen Screen;
	protected Random rnd = new Random();
	public List<Column> Columns = new ArrayList<Column>();
	public static int[] grid = new int[29 * 14];
	public static int[] blocks = new int[29 * 14];
	public static boolean[] killBuff = new boolean[29 * 14];
	public boolean active = true;
	private int ups=0;

	public world(int width, int height, Screen screen) {
		this.width = width;
		this.height = height;
		this.Screen = screen;
		for (int i = 0; i < grid.length; i++) {
			grid[i] = 0;
		}
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = 0;
		}
	}
	

	public void update() {
		if (c == 0){
			for (int i = 0; i < Columns.size(); i++) {
				if (Columns.get(i).isRemoved()) {
					Columns.remove(i);
				} else {
					Columns.get(i).update();
				}
			}
			for (int i = 0; i < 28; i++) {
				if (blocks[i + 27 * 14] != 0) {
					c++;
				}
			}
	
			for (int o = 0; o < Columns.size(); o++) {
				if (Columns.get(o).control)
					active = true;
			}
	
			for (int i = 0; i < grid.length; i++) {
				grid[i] = 0;
			}
	
			for (int i = 0; i < grid.length; i++) {
				if (blocks[i] != 0)
					grid[i] = blocks[i];
			}
			for (int y = 0; y < 27; y++) {
				for (int x = 0; x < 14; x++) {
					if ((x) + (y - 1) * 14 >= 0) {
						if (blocks[(x) + (y - 1) * 14] == 0&&blocks[(x) + (y) * 14]!=0) {
							blocks[(x) + (y - 1) * 14] = blocks[x + y * 14];
							blocks[x + y * 14] = 0;
						}
					}
				}
			}
			clear();
		}
	}

	public void Spec(int col){
		for (int y = 0; y < 27; y++) {
			for (int x = 0; x < 14; x++) {
				if(blocks[x+y*14]==col){
					killBuff[x+y*14]=true;
				}
			}
		}
		kill();
	}
	
	public void clear() {
		for (int i = 0; i < killBuff.length; i++) {
			killBuff[i]=false;
		}
		for (int y = 0; y < 27; y++) {
			for (int x = 0; x < 14; x++) {
				int col = blocks[x + y * 14];

				boolean valid;
				int count = 0;
				int i;

				if ((x + 1) + (y) * 14 < 406) {
					valid = true;
					count = 0;
					i = 0;
					while (valid) {
						i++;
						if ((x + i) + (y) * 14 >= 406)
							break;
						if (blocks[(x + i) + (y) * 14] == col && col != 0)
							count++;
						else
							valid = false;
					}
					if (count >= 2) {
						for (int o = 0; o < count + 1; o++) {
							killBuff[(x + o) + (y) * 14] = true;
						}

					}
				}
				if ((x - 1) + (y) * 14 >= 0) {
					valid = true;
					count = 0;
					i = 0;
					while (valid) {
						i++;
						if ((x - i) + (y) * 14 < 0)
							break;
						if (blocks[(x - i) + (y) * 14] == col && col != 0)
							count++;
						else
							valid = false;
					}
					if (count >= 2) {
						for (int o = 0; o < count + 1; o++) {
							killBuff[(x - o) + (y) * 14] = true;
						}

					}
				}
				if ((x) + (y - 1) * 14 >= 0) {
					valid = true;
					count = 0;
					i = 0;
					while (valid) {
						i++;
						if ((x) + (y - i) * 14 < 0)
							break;
						if (blocks[(x) + (y - i) * 14] == col && col != 0)
							count++;
						else
							valid = false;
					}
					if (count >= 2) {
						for (int o = 0; o < count + 1; o++) {
							killBuff[(x) + (y - o) * 14] = true;
						}

					}
				}
				if ((x) + (y + 1) * 14 < 406) {
					valid = true;
					count = 0;
					i = 0;
					while (valid) {
						i++;
						if ((x) + (y + i) * 14 > 406)
							break;
						if (blocks[(x) + (y + i) * 14] == col && col != 0)
							count++;
						else
							valid = false;
					}
					if (count >= 2) {
						for (int o = 0; o < count + 1; o++) {
							killBuff[(x) + (y + o) * 14] = true;
						}
					}
				}
			}
		}
		kill();
	}
	public void kill(){
		for(int i=0;i<killBuff.length;i++){
			if(killBuff[i]){
				blocks[i]=0;
				Score += 50;
				
			}
		}
	}

	public void add(Column t) {
		Columns.add(t);
	}

	public void render() {
		Screen.renderSprite(0, 0, Sprite.Background);
		for (int i = 0; i < Columns.size(); i++) {
			Columns.get(i).render();
		}
		if (c == 0){
			for (int y = 0; y < 28; y++) {
				for (int x = 0; x < 14; x++) {
	
					if (grid[x + y * 14] == 1) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.BrickRed, 0);
					}
					if (grid[x + y * 14] == 2) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.BrickBlue, 0);
					}
					if (grid[x + y * 14] == 3) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.BrickGreen, 0);
					}
					if (grid[x + y * 14] == 4) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.BrickYellow, 0);
					}
					if (grid[x + y * 14] == 5) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.BrickPurple, 0);
					}
					if (killBuff[x + y * 14] == true) {
						Screen.renderEntity(131 + (x * 5), 156 - (y * 5),
								Sprite.Brick, 0);
					}
				}
			}
		}
		if (c >= 1){
			Screen.renderSprite(0, 0, Sprite.GameOver);
		}		
	}
}
