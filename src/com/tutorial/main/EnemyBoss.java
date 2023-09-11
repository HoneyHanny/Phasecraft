package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

	private Handler handler;

	private int timer = 90;
	private int timer2 = 50;

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

		velX = 0;
		velY = 2;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 192, 192);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (timer <= 0)
			velY = 0;
		else
			timer--;

		if (timer <= 0)
			timer2--;
		if (timer2 <= 0) {
			if (velX == 0)
				velX = 2;

			// if(velX > 0) velX += 1;
			// if(velX < 0) velX -= 1;

			velX = Game.clamp(velX, -10, 10);

			if (new Random().nextInt(5) == 0)
				handler.addObject(new EnemyBossBullet(x + 96, y + 96, ID.EnemyBossBullet, handler));
		}

		// if (y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 192)
			velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, new Color(1f, 1f, 1f, .1f), 192, 192, 0.05f, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 192, 192);
	}

}
