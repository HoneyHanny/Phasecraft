package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class HUD {

	private BufferedImage image;

	public static int HEALTH = 100;

	private int ticks = 0;

	// private int redValue = 0;
	// private int greenValue = 255;

	private int rocks = 0;
	private int score = 0;
	private int level = 1;

	public HUD() {

		String path = "res/HUD.png";
		
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		// greenValue = HEALTH * 2;
		// redValue = 255 - greenValue;
		// redValue = Game.clamp(redValue, 0, 255);
		// greenValue = Game.clamp(greenValue, 0, 255);

		ticks++;
		if(ticks == 60) {
			ticks = 0;
			score++;
		}
		
	}

	public void render(Graphics g) {
		g.setFont(new Font("Aquire", Font.PLAIN, 16));

		int baseWidth = 200;
		int baseHeight = 32;
		int baseX = Game.WIDTH - 315;
		int baseY = 50;
		int healthX = baseX + 78;
		int healthY = baseY + 23;
		
		// healthbar
		g.setColor(Color.GRAY);
		g.fillRect(baseX, baseY, baseWidth, baseHeight);
		g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
		g.fillRect(baseX, baseY, HEALTH * 2, baseHeight);
		g.setColor(Color.WHITE);
		g.drawRect(baseX, baseY, baseWidth, baseHeight);
		g.drawString(HEALTH + " HP", healthX, healthY);

		baseX = 150;
		baseY = 50;
		
		// g.setColor(Color.RED);
		g.drawString("Score " + score, baseX, baseY);
		g.drawString("Level " + level, baseX, baseY + 20);
		g.drawString("Rocks " + rocks, baseX, baseY + 40);

		g.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT + 5, null);
	}

	public void setRocks(int rocks) {
		this.rocks = rocks;
	}

	public int getRocks() {
		return rocks;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
