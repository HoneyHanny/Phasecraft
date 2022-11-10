package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RockSample extends GameObject {

	private BufferedImage rock;

	private int hover;
	private int width = 50;
	private int height = 50;

	public RockSample(int x, int y, ID id, RockSprite rs) {
		super(x, y, id);
		int texture = new Random().nextInt(2);
		if (texture == 0) {
			rock = rs.getRock1();
		} else {
			rock = rs.getRock2();
		}
	}

	@Override
	public void tick() {
		hover = (int) (Math.cos((System.currentTimeMillis()) * Math.PI / 1000) * 5);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(rock, x, y + hover, width, height, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
}
