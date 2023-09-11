package com.tutorial.main;

import java.awt.Color;

import java.util.Random;

public class Spawn {

	private Random r;
	private Handler handler;
	private HUD hud;
	private Game game;
	
	private boolean nextLevel = true;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		r = new Random();
	}

	public void tick() {
		// scoreKeep = hud.getScore();
		if(Game.isDivisibleBy(10, hud.getScore() + 1)) nextLevel = true;

		if(Game.isDivisibleBy(10, hud.getScore())) {
			if(nextLevel) {
			
				hud.setLevel(hud.getLevel() + 1);
				
				switch (game.difficulty) {
					case 1:
						normal();
						break;
					case 2:
						hard();
						break;
				}
				nextLevel = false;
			}
		}
	}

	private void normal() {
		if (hud.getLevel() == 2) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 4) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 5) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
		} else if (hud.getLevel() == 7) {
			handler.addObject(new SmartEnemy(Game.WIDTH + 16, Game.HEIGHT + 16, ID.TrackerEnemy, Color.YELLOW, handler));
		} else if (hud.getLevel() == 8) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 9) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 10) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 11) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 12) {
			handler.clearEnemies();
			handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
		} else if (hud.getLevel() == 13) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 14) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 15) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 16) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 17) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 18) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 19) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 20) {
			handler.clearEnemies();
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 21) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 22) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 23) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 24) {
			handler.addObject(new SmartEnemy(Game.WIDTH + 16, Game.HEIGHT + 16, ID.TrackerEnemy, Color.YELLOW, handler));
		} else if (hud.getLevel() == 25) {
			handler.addObject(new TrackerEnemy(Game.WIDTH + 20, Game.HEIGHT + 20, ID.TrackerEnemy, 2, 2, Color.ORANGE, handler));
		} else if (hud.getLevel() == 26) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 26) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() < 27 && hud.getLevel() > 100) {
			if (Game.isDivisibleBy(10, hud.getScore())) {
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
						ID.UnpredictableEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
			}
		} else if (hud.getLevel() == 100) {
			handler.clearEnemies();
		} else if (hud.getLevel() == 101) {
			
		}else if (hud.getLevel() < 100 && hud.getLevel() > 500) {
			if (Game.isDivisibleBy(10, hud.getScore())) {
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
						ID.UnpredictableEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
			}
			if (Game.isDivisibleBy(50, hud.getLevel())) {
				handler.clearBoss();
			} else if (Game.isDivisibleBy(100, hud.getLevel())) {
				handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
			}
		} else if (hud.getLevel() == 250) {
			Menu.levetate = 0;
			game.gameState = Game.STATE.Win;
			HUD.HEALTH = 100;
			handler.clearEnemiesAndBullets();
			handler.clearPlayer();
			handler.clearPlayer();
			game.setCursor(null);
		}
	}

	private void hard() {
		if (hud.getLevel() == 2) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 4) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 5) {
			handler.addObject(
					new FastEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.FastEnemy, handler));
		} else if (hud.getLevel() == 7) {
			handler.addObject(
					new SmartEnemy(Game.WIDTH + 16, Game.HEIGHT + 16, ID.TrackerEnemy, Color.YELLOW, handler));
		} else if (hud.getLevel() == 8) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 9) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 10) {
			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 11) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 12) {
			handler.clearEnemies();
			handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
		} else if (hud.getLevel() == 13) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 14) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 15) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 16) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 17) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() == 18) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 19) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 20) {
			handler.clearEnemies();
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 21) {
			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 22) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 23) {
			handler.addObject(
					new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
		} else if (hud.getLevel() == 24) {
			handler.addObject(
					new SmartEnemy(Game.WIDTH + 16, Game.HEIGHT + 16, ID.TrackerEnemy, Color.YELLOW, handler));
		} else if (hud.getLevel() == 25) {
			handler.addObject(
					new TrackerEnemy(Game.WIDTH + 20, Game.HEIGHT + 20, ID.TrackerEnemy, 2, 2, Color.ORANGE, handler));
		} else if (hud.getLevel() == 26) {
			handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
					ID.UnpredictableEnemy, handler));
		} else if (hud.getLevel() == 26) {
			handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
		} else if (hud.getLevel() < 27 && hud.getLevel() > 100) {
			if (Game.isDivisibleBy(10, hud.getScore())) {
				handler.addObject(
						new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
						ID.UnpredictableEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy,
						handler));
			}
		} else if (hud.getLevel() == 100) {
			handler.clearEnemies();
			
		} else if (hud.getLevel() < 100 && hud.getLevel() > 500) {
			if (Game.isDivisibleBy(10, hud.getScore())) {
				handler.addObject(
						new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new UnpredictableEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16),
						ID.UnpredictableEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy,
						handler));
			}
			if (Game.isDivisibleBy(50, hud.getLevel())) {
				handler.clearBoss();
			} else if (Game.isDivisibleBy(100, hud.getLevel())) {
				handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
			}
		} else if (hud.getLevel() == 500) {
			Menu.levetate = 0;
			game.gameState = Game.STATE.Win;
		}
	}

}

