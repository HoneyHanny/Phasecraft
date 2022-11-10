package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

	private Handler handler;
	private Player player;
	private Color color;

	public SmartEnemy(int x, int y, ID id, Color color, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		this.color = color;

		for (GameObject go : handler.object)
			if (go.getId() == ID.Player) {
				this.player = (Player) go;
				break;
			}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt(Math.pow(x - (player.getX()), 2) + Math.pow(y - (player.getY()), 2));

		velX = Math.round((-1 / distance) * diffX * 3);
		velY = Math.round((-1 / distance) * diffY * 3);

		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.RED);
		// g.fillRect(x, y, 16, 16);
	}

}
