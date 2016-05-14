package Entity;

import java.util.Random;

import Input.Keyboard;
import Level.world;

public class Column {

	private int x;
	public int y;
	private int flash=0;
	private int sizeX;
	private int clockX = 0, clockY = 0, clockS = 0, grav = 1;
	public int level;
	private Keyboard key;
	private world world;
	private int[] grid = new int[3];
	public boolean removed = false, control = true, done = false,spec=false;
	private Random rnd = new Random();

	public Column(world world, Keyboard key, int level) {
		this.world = world;
		this.key = key;
		this.level = level;
		x = 7;
		y = 26;
		sizeX = 1;
		world.active = true;

		for (int i = 0; i < 3; i++) {
			grid[i] = 0;
		}

		// 1 red
		// 2 blue
		// 3 green
		// 4 yellow
		// 5 purple
		spec = (rnd.nextInt(50)==0);
		if(!spec){
			grid[0] =  rnd.nextInt(5) + 1;;
			grid[1] =  rnd.nextInt(5) + 1;;
			grid[2] =  rnd.nextInt(5) + 1;;
		}
	}

	public void update() {
		flash++;
		if(flash>3){
			flash=0;
		}
		
		int rndcol=rnd.nextInt(5) + 1;
		while(rndcol==grid[0]){
			rndcol = rnd.nextInt(5) + 1;
		}

		if(spec&&flash==3){
			grid[0] = rndcol;
			grid[1] = rndcol;
			grid[2] = rndcol;
		}
		level = (int) world.Score / 1000;
		if (done) {
			control = false;
			remove();
		}

		if (control) {
			int xa, ya;
			clockX++;
			clockY++;
			clockS++;
			grav = 20 - (level/2);
			xa = 0;
			ya = -1;

			if (key.left && x % 14 != 0
					&& (Level.world.grid[x - 1 + y * 14] == 0))
				xa = -1;
			if (key.right && x % (14 - (sizeX - 1)) != (13 - (sizeX - 1))
					&& (Level.world.grid[x + sizeX + y * 14] == 0))
				xa = 1;
			if (key.down)
				grav /= 10;
			if(key.up){
				boolean land=false;
				while(!land){
					if(x+(y-1)*14>=0){
						land = (Level.world.grid[x+(y-1)*14]!=0);
					}else land=true;
					if(land)break;
					if(x+(y-1)*14>=0){
						y--;
					}
				}
			}
			if (clockX >= 5) {
				move(xa, 0);
				clockX = 0;
			}
			if (clockY >= grav) {
				move(0, ya);
				clockY = 0;
			}
			if (key.space && clockS >= 10) {
				rotate(grid);
				clockS = 0;
			}
		}
	}

	public void move(int xa, int ya) {
		if (!done) {
			if ((x + (y+ya) * 14) < 0) {
				if (!done) {
					done = true;
					for (int o = 0; o < 3; o++) {
						Level.world.blocks[(x) + (y + o) * 14] = grid[o];
					}
					world.clear();
					world.Columns.add(new Column(world, key, level));
				}
			}
		}
		if (!done) {
			for (int i = 0; i < sizeX; i++) {
				if (Level.world.blocks[(x + i) + (y + ya) * 14] != 0 && ya != 0) {
					if (!done&&!spec) {
						done = true;
						for (int o = 0; o < 3; o++) {
							Level.world.blocks[(x) + (y + o) * 14] = grid[o];
						}
						world.clear();
						world.Columns.add(new Column(world, key, level));
					}else if(!done&&spec){
						done = true;
						int col = Level.world.blocks[(x)+(y-1)*14];
						world.Spec(col);
						world.Columns.add(new Column(world, key, level));
					}
				}
			}
		}
		if (!done) {
			if (xa != 0 && ya != 0) {
				move(xa, 0);
				move(0, ya);
				return;
			}
			x += xa;
			y += ya;
		}
	}

	public boolean isRemoved() {
		return removed;
	}

	public void remove() {
		removed = true;
		if (control)
			world.Columns.add(new Column(world, key, level));
	}

	public void rotate(int[] grid) {
		int[] temp = new int[3];
		temp[0] = grid[1];
		temp[1] = grid[2];
		temp[2] = grid[0];
		for (int i = 0; i < 3; i++) {
			grid[i] = temp[i];
		}
	}

	public void render() {
		if (control) {
			for (int i = 0; i < 3; i++) {
				Level.world.grid[(x) + (y + i) * 14] = grid[i];
			}
		}
	}
}
