package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

	private Handler handler;

	private Random r = new Random();

	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = r.nextInt(10) - 5;
		if (r.nextInt(2) == 1)
			velY = 10;
		else
			velY = -10;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		// if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		// if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

		if (y >= Game.HEIGHT || y < -16)
			handler.removeObject(this);

		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.RED);
		// g.fillRect(x, y, 16, 16);
	}

}
