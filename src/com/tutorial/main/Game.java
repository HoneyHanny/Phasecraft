package com.tutorial.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 * @author Hans Duran
 * 
 * Game I made for game development learning.
 * 
 */

public class Game extends Canvas implements Runnable {

	public static final String TITLE = "Phasecraft";
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = screenSize.width;
	public static final int HEIGHT = screenSize.height;
	
	public int difficulty;

	private int frames = 0;
	private int prevframes = 0;
	private int ticks = 0;

	private boolean running = false;
	public boolean fps = false;
	private Thread thread;

	public RockSprite rs;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Background[] background;
	private Menu menu;

	private Sound bgMusic;

	public static enum STATE {
		Menu, Game, Help, End, Difficulty, Pause, Settings, Win, PauseSettings
	}

	public STATE gameState = STATE.Menu;

	public Game() {
		bgMusic = new Sound("res/bgmusicMacha.wav");
		bgMusic.loop();

		/* shutdown hook */
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				bgMusic.getClip().close();
			}
		});

		rs = RockSprite.getInstance();
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud, bgMusic);
		addKeyListener(new KeyInput(this, handler, menu));
		addMouseListener(menu);
		addMouseMotionListener(menu);

		background = new Background[20];
		for (int i = 0; i < background.length; i++)
			background[i] = new Background();

		new Window(this, TITLE);

		spawner = new Spawn(handler, hud, this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000 && fps) {
				timer += 1000;
				prevframes = frames;
				frames = 0;
			}
		}

		stop();
	}

	private void tick() {
		if (gameState != Game.STATE.Pause && gameState != Game.STATE.PauseSettings) {
			tickBackground();
			handler.tick();
		}
		if (gameState == Game.STATE.Game) {

			hud.tick();
			spawner.tick();
			if (HUD.HEALTH <= 0) {

				HUD.HEALTH = 100;
				gameState = Game.STATE.End;
				handler.clearEnemiesAndBullets();
				handler.clearPlayer();
				handler.clearRock();
				setCursor(null);
				
			}
		} else if (gameState == Game.STATE.Win) {
			menu.tick();
		}
		ticks++;
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		renderBackground(g);
		
		if (gameState == Game.STATE.Game) {
			handler.render(g);
			hud.render(g);
		} else if (gameState != Game.STATE.Game) {
			menu.render(g);
		}

		// display fps
		if (fps) {
			g.setColor(Color.GREEN);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			if (ticks == 20) {
				ticks = 0;
				g.drawString(frames + "", Game.WIDTH - g.getFontMetrics().stringWidth(frames + "") - 5, 14);
			} else
				g.drawString(prevframes + "", Game.WIDTH - g.getFontMetrics().stringWidth(prevframes + "") - 5, 14);
		}


		g.dispose();
		bs.show();
	}

	private void tickBackground() {
		for (int i = 0; i < background.length; i++)
			background[i].tick();
	}

	private void renderBackground(Graphics g) {
		if (background == null)
			return;

		for (int i = 0; i < background.length; i++) {
			background[i].render(g);
		}
	}

	// Tools

	public static int changeScale(int left, int right, int value) {
		return value * left / right;
	}

	public static final float changeScale(float left, float right, float value) {
		return value * left / right;
	}

	public static final int clamp(int val, int min, int max) {
		return Math.max(min, Math.min(max, val));
	}

	public static final float clamp(float val, float min, float max) {
		if (val > max)
			return max;
		else if (val < min)
			return min;
		else
			return val;
	}

	public static final boolean isDivisibleBy(int divisible, int value) {
		int ans = value % divisible;
		if (ans == 0)
			return true;
		return false;
	}

	public static void main(String args[]) {
		new Game();
	}

}