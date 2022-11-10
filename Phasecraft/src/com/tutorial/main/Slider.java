package com.tutorial.main;

import java.awt.Graphics;

public class Slider {

	private int x, y;
	private int length;
	private int height;
	private int sliderX, sliderY;
	private int sliderLength;
	private int sliderHeight;

	public Slider(int x, int y, int length, int height, int sliderX, int sliderY, int sliderLength, int sliderHeight) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;
		this.sliderX = sliderX;
		this.sliderY = sliderY;
		this.sliderLength = sliderLength;
		this.sliderHeight = sliderHeight;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		// draw slide
		g.fillRect(x, y, length, height);
		// draw slider
		g.drawRect(sliderX, sliderY, sliderLength, sliderHeight);
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

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSliderX() {
		return this.sliderX;
	}

	public void setSliderX(int sliderX) {
		this.sliderX = sliderX;
	}

	public int getSliderY() {
		return this.sliderY;
	}

	public void setSliderY(int sliderY) {
		this.sliderY = sliderY;
	}

	public int getSliderLength() {
		return this.sliderLength;
	}

	public void setSliderLength(int sliderLength) {
		this.sliderLength = sliderLength;
	}

	public int getSliderHeigth() {
		return this.sliderHeight;
	}

	public void setSliderHeigth(int sliderHeigth) {
		this.sliderHeight = sliderHeigth;
	}
	
}
