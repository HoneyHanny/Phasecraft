package com.tutorial.main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyInput implements KeyListener {

	private Handler handler;
	private Game game;
	private Menu menu;

	private int[] velocity = new int[4];
	// private boolean[] keyDown = new boolean[4];

	private int speed = 5;

	public KeyInput(Game game, Handler handler, Menu menu) {
		this.handler = handler;
		this.game = game;
		this.menu = menu;

		// for (int i = 0; i < keyDown.length; i++) keyDown[i] = false;
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (game.gameState == Game.STATE.Game) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if(tempObject.getId() == ID.Player) {
					if(key == KeyEvent.VK_W) velocity[0] = -speed;
					if(key == KeyEvent.VK_S) velocity[1] = speed; 
					if(key == KeyEvent.VK_D) velocity[2] = speed; 
					if(key == KeyEvent.VK_A) velocity[3] = -speed;

					tempObject.setVelY(velocity[0] + velocity[1]);
					tempObject.setVelX(velocity[2] + velocity[3]);
				}
				
			}
		} else if (game.gameState == Game.STATE.Win) {
			if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_SPACE)
				game.gameState = Game.STATE.Menu;
		}

		// escape back button
		if (key == KeyEvent.VK_ESCAPE) {
			if (game.gameState == Game.STATE.Help || game.gameState == Game.STATE.Settings
					|| game.gameState == Game.STATE.Difficulty) {
				game.gameState = Game.STATE.Menu;
			} else if (game.gameState == Game.STATE.PauseSettings) {
				game.gameState = Game.STATE.Pause;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			if(game.gameState == Game.STATE.Game) {
				game.gameState = Game.STATE.Pause;
				game.setCursor(null);
			}
			else if(game.gameState == Game.STATE.Pause) {
				game.gameState = Game.STATE.Game;
				menu.hideCursor();
			}
		}

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) velocity[0] = 0;
				if (key == KeyEvent.VK_S) velocity[1] = 0;
				if (key == KeyEvent.VK_D) velocity[2] = 0;
				if (key == KeyEvent.VK_A) velocity[3] = 0;

				tempObject.setVelY(velocity[0] + velocity[1]);
				tempObject.setVelX(velocity[2] + velocity[3]);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();

		// show fps		
		if (key == '\\') {
			if (game.fps) game.fps = false;
			else game.fps = true;
		}

	}
	
}
