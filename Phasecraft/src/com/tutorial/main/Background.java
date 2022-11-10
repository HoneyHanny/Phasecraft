package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Background {

	private int x, y;

	public Background() {
		x = new Random().nextInt(Game.WIDTH * 2);
		y = new Random().nextInt(Game.HEIGHT);

	}

	public void tick() {
		x += -1;

		if (x < 0) x = new Random().nextInt(Game.WIDTH * 2) + Game.WIDTH;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 2, 2);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
