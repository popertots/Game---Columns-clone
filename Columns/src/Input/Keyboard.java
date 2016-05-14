package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[150];
	public boolean up, down, left, right, shift, ctrl, alt, space, enter;
	public boolean num1, num2, num3, num4, num5, num6, num7, num8, num9, num0;
	public boolean keyJ, keyK, keyL, keyI;
	public boolean keyNum1, keyNum2, keyNum3, keyNum4, keyNum5, keyNum6,
			keyNum7, keyNum8, keyNum9, keyNum0;

	public void update() {
		enter = keys[KeyEvent.VK_ENTER];
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
		ctrl = keys[KeyEvent.VK_CONTROL];
		alt = keys[KeyEvent.VK_ALT];
		num1 = keys[KeyEvent.VK_NUMPAD1];
		num2 = keys[KeyEvent.VK_NUMPAD2];
		num3 = keys[KeyEvent.VK_NUMPAD3];
		num4 = keys[KeyEvent.VK_NUMPAD4];
		num5 = keys[KeyEvent.VK_NUMPAD5];
		num6 = keys[KeyEvent.VK_NUMPAD6];
		num7 = keys[KeyEvent.VK_NUMPAD7];
		num8 = keys[KeyEvent.VK_NUMPAD8];
		num9 = keys[KeyEvent.VK_NUMPAD9];
		keyJ = keys[KeyEvent.VK_J];
		keyK = keys[KeyEvent.VK_K];
		keyL = keys[KeyEvent.VK_L];
		keyI = keys[KeyEvent.VK_I];
		space = keys[KeyEvent.VK_SPACE];
		keyNum1 = keys[KeyEvent.VK_1];
		keyNum2 = keys[KeyEvent.VK_2];
		keyNum3 = keys[KeyEvent.VK_3];
		keyNum4 = keys[KeyEvent.VK_4];
		keyNum5 = keys[KeyEvent.VK_5];
		keyNum6 = keys[KeyEvent.VK_6];
		keyNum7 = keys[KeyEvent.VK_7];
		keyNum8 = keys[KeyEvent.VK_8];
		keyNum9 = keys[KeyEvent.VK_9];
		keyNum0 = keys[KeyEvent.VK_0];

	}

	public void keyPressed(KeyEvent e) {
		try{
			keys[e.getKeyCode()] = true;
		}catch(java.lang.ArrayIndexOutOfBoundsException E){
			System.err.println("Invalid key pressed.");
		}
	}

	public void keyReleased(KeyEvent e) {
		try{
			keys[e.getKeyCode()] = false;
		}catch(java.lang.ArrayIndexOutOfBoundsException E){
			System.err.println("Invalid key released.");
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}