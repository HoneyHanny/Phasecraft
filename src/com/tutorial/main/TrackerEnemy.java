package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class TrackerEnemy extends GameObject {

	private Handler handler;
	private Player player;
	private Color color;
	
	private int xSpeed, ySpeed;

	public TrackerEnemy(int x, int y, ID id, int xSpeed, int ySpeed, Color color, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		this.color = color;

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;

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
		
		// horizontal tracking
		if(player.getX() + 8 < x) velX = -xSpeed;
		else if(player.getX() + 8 > x) velX = xSpeed;
		else if(player.getX() + 8 == x) velX = 0;
		
		// vertical tracking
		if(player.getY() + 8 < y) velY = -ySpeed;
		else if(player.getY() + 8 > y) velY = ySpeed;
		else if(player.getY() + 8 == y) velY = 0;

		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler));
		// handler.addObject(new Trail(x, y, ID.Trail, Color.BLUE, 16, 16, 0.02f, handler));
		// handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.05f, handler));
	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.RED);
		// g.fillRect(x, y, 16, 16);
	}

}
