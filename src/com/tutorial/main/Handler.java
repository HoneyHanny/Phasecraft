package com.tutorial.main;

import java.awt.Graphics;

import java.util.LinkedList;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		int size = object.size();
		for (int i = 0; i < size; i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void clearEnemies() {
		int size = object.size();
		for (int i = 0; i < size; i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getId() != ID.Player && tempObject.getId() != ID.EnemyBossBullet && tempObject.getId() != ID.RockSample) {
				removeObject(tempObject);
				i--;
			}
		}
	}

	public void clearPlayer() {
		object.removeIf(x -> x.getId() == ID.Player);
	}

	public void clearEnemiesAndBullets() {
		object.removeIf(x -> x.getId() != ID.Player);
	}

	public void clearBoss() {
		object.removeIf(x -> x.getId() == ID.EnemyBoss);
	}

	public void clearRock() {
		object.removeIf(x -> x.getId() == ID.RockSample);
	}
	
}
