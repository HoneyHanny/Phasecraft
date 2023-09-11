package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PassingEnemy extends GameObject {

	private Handler handler;
	private Game game;

	public PassingEnemy(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);

		this.handler = handler;
		this.game = game;
		velX = -5;
		velY = 0;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (x < -20) {
			x = new Random().nextInt(Game.WIDTH) + Game.WIDTH;
			y = new Random().nextInt(Game.HEIGHT - 15);
			switch (game.difficulty) {
				case 0:
					velX = -(new Random().nextInt(5) + 2);
					break;
				case 1:
					velX = -(new Random().nextInt(10) + 5);
					break;
			}
			
		}

		handler.addObject(new Trail(x, y, ID.Trail, new Color(255, 50, 50), 16, 16, 0.02f, handler));
		handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 16, 16, 0.04f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(1, 0, 0, .5f));
		g.fillRect(x - 8, y - 8, 32, 32);
	}

}
