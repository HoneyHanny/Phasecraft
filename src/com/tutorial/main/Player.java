package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	private Handler handler;
	private HUD hud;
	private Game game;

	private boolean hasTakenDamage = false;

	private int regenTime = 0;
	private int regenCooldown = 0;

	public Player(int x, int y, ID id, HUD hud, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 32);

		if (hud.getLevel() > 1) {

			if (hasTakenDamage) {

				regenCooldown--;

				if (regenCooldown < 0) {
					hasTakenDamage = false;
					regenCooldown = 600;
				}

			} else if (!hasTakenDamage) {

				if (regenTime < 0) {
					regenTime = 9;
					hasTakenDamage = true;
				} else {
					regenTime--;
					HUD.HEALTH++;
				}

			}
		}

		collision();

		// handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 16, 16, .04f, handler));
		// handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, .05f, handler));

		Trail trail = new Trail(x, y, ID.Trail, Color.WHITE, 32, 32, .17f, handler);
		trail.setPlayer(true);
		handler.addObject(trail);
		
		// tick++;
		// if(tick == 10 - 5) { color = Color.RED; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); }
		// else if(tick == 20 - 5) { color = Color.ORANGE; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); }
		// else if(tick == 30 - 5) { color = Color.YELLOW; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); }
		// else if(tick == 40 - 5) { color = Color.GREEN; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); }
		// else if(tick == 50 - 5) { color = Color.BLUE; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); }
		// else if(tick == 60 - 5) { color = Color.MAGENTA; handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler)); tick = 0; }
		// else handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, .05f, handler));
	}

	private void collision() {

		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.UnpredictableEnemy || tempObject.getId() == ID.TrackerEnemy ||
					tempObject.getId() == ID.PassingEnemy) {

				if(getBounds().intersects(tempObject.getBounds())) {

					regenTime = 7;
					regenCooldown = 700;
					hasTakenDamage = true;
					HUD.HEALTH -= 2;

				}

			} else if (tempObject.getId() == ID.EnemyBossBullet || tempObject.getId() == ID.EnemyBoss) {

				if (getBounds().intersects(tempObject.getBounds())) {

					regenTime = 4;
					regenCooldown = 800;
					hasTakenDamage = true;
					HUD.HEALTH -= 25;

				}

			} else if (tempObject.getId() == ID.RockSample) {

				if (getBounds().intersects(tempObject.getBounds())) {

					Random rand = new Random();

					RockSample rock = (RockSample) tempObject;

					hud.setRocks(hud.getRocks() + 1);
					int rockX = Game.WIDTH - rock.getWidth();
					int rockY = Game.HEIGHT - rock.getHeight();
					handler.addObject(new RockSample(rand.nextInt(rockX), rand.nextInt(rockY), ID.RockSample, game.rs));
					handler.removeObject(tempObject);
					
				}
				
			}
			
		}

	}

	@Override
	public void render(Graphics g) {
		// g.setColor(Color.RED);
		// g.fillRect(x, y, 32, 32);
	}
	
}
