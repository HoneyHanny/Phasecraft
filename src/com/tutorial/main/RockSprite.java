package com.tutorial.main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// this is a singleton class
public class RockSprite {

	private static RockSprite instance = null;

	private BufferedImage rock1;
	private BufferedImage rock2;

	private RockSprite() {
		try {
			rock1 = ImageIO.read(new File("res/rock_sample.png"));
			rock2 = ImageIO.read(new File("res/asteroid.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static RockSprite getInstance() {
		if (instance == null) {
			instance = new RockSprite();
		}
		return instance;
	}

	public BufferedImage grabRockImage(boolean clean) {
		if (clean) {
			return rock1;
		} else {
			return rock2;
		}
	}

	public BufferedImage getRock1() {
		return rock1;
	}

	public BufferedImage getRock2() {
		return rock2;
	}
	
}