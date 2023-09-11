package com.tutorial.main;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;

import java.util.Random;

public class Menu implements MouseListener, MouseMotionListener {

	public static int levetate = 0;	
	public float level = 196;
	private int mx, my;
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private Slider slider;
	private Sound bgMusic;
	private Color colorTheme = Color.WHITE;

	public Menu(Game game, Handler handler, HUD hud, Sound bgMusic) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.bgMusic = bgMusic;

		int sliderLength = 200;
		int sliderHeight = 5;
		int sliderX = Game.WIDTH / 2 - sliderLength / 2;
		int sliderY = Game.HEIGHT / 2 - sliderHeight / 2;
		int length = 5;
		int height = 20;
		int x = sliderX + sliderLength - length;
		int y = (sliderY + sliderHeight / 2) - (height / 2);

		slider = new Slider(x, y, length, height, sliderX, sliderY, sliderLength, sliderHeight);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

		if (game.gameState == Game.STATE.Menu) {
			// menu state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {
				// play button
				game.gameState = Game.STATE.Difficulty;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 450, 200, 64)) {
				// help button
				game.gameState = Game.STATE.Help;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 550, 200, 64)) {
				// settings button
				game.gameState = Game.STATE.Settings;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 650, 200, 64)) {
				// quit button
				System.exit(1);
			}
		} else if (game.gameState == Game.STATE.Help) {
			// help state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 650, 200, 64)) {
				// back button
				game.gameState = Game.STATE.Menu;
				return;
			}
		} else if (game.gameState == Game.STATE.End) {
			// end state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 300, 650, 200, 64)) {
				// retry button
				game.gameState = Game.STATE.Difficulty;
				HUD.HEALTH = 100;
				hud.setRocks(0);
				hud.setScore(0);
				hud.setLevel(1);
				handler.clearEnemies();
				handler.clearPlayer();
				handler.clearRock();
			} else if (mouseOver(mx, my, Game.WIDTH / 2 + 100, 650, 200, 64)) {
				// main menu button
				game.gameState = Game.STATE.Menu;
				HUD.HEALTH = 100;
				hud.setRocks(0);
				hud.setScore(0);
				hud.setLevel(1);
				handler.clearEnemies();
				handler.clearPlayer();
				handler.clearRock();
			}
		} else if (game.gameState == Game.STATE.Pause) {
			// pause state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {
				// resume button
				game.gameState = Game.STATE.Game;
				hideCursor();
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 450, 200, 64)) {
				// return to main menu button
				game.gameState = Game.STATE.Menu;
				HUD.HEALTH = 100;
				hud.setRocks(0);
				hud.setScore(0);
				hud.setLevel(1);
				handler.clearEnemies();
				handler.clearPlayer();
				handler.clearRock();
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 550, 200, 64)) {
				// settings button
				game.gameState = Game.STATE.PauseSettings;
			} else if (mouseOver(mx ,my, Game.WIDTH / 2 - 200 / 2, 650, 200, 64)) {
				// quit button
				System.exit(0);
			}
		} else if (game.gameState == Game.STATE.Difficulty) { // difficulty state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 350, 200, 64)) {
				// normal button
				game.difficulty = 0;
				game.gameState = Game.STATE.Game;
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new Player(Game.WIDTH / 2 - 32 / 2, Game.WIDTH / 2 - 32 / 2, ID.Player, hud, handler, game));
				handler.addObject(new TrackerEnemy(Game.WIDTH + 20, Game.HEIGHT + 20, ID.TrackerEnemy, 2, 2, Color.ORANGE, handler));
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new RockSample(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.RockSample, game.rs));
				hideCursor();
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 450, 200, 64)) {
				// hard button
				game.difficulty = 1;
				game.gameState = Game.STATE.Game;
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new Player(Game.WIDTH / 2 - 32 / 2, Game.WIDTH / 2 - 32 / 2, ID.Player, hud, handler, game));
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new TrackerEnemy(Game.WIDTH + 20, Game.HEIGHT + 20, ID.TrackerEnemy, 2, 2, Color.ORANGE, handler));
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.BasicEnemy, handler));
				handler.addObject(new PassingEnemy(Game.WIDTH + 20, r.nextInt(Game.HEIGHT - 16), ID.PassingEnemy, handler, game));
				handler.addObject(new RockSample(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.RockSample, game.rs));
				hideCursor();
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 550, 200, 64)) {
				// back button
				game.gameState = Game.STATE.Menu;
			}
		} else if (game.gameState == Game.STATE.Settings) {
			// settings state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 650, 200, 64)) {
				// back button
				game.gameState = Game.STATE.Menu;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 25 / 2, 525, 25, 25)) { // mute control
				if (bgMusic.isMuted()) {
					bgMusic.setMuted(false);
					bgMusic.setVolume(level);
				}
				else {
					bgMusic.setMuted(true);
					bgMusic.setVolume(0f);
				}
				/* Color themes mouseClicked */
			} else if (mouseOver(mx, my, 100, 100, 200, 70)) {
				colorTheme = Color.WHITE; 
			} else if (mouseOver(mx, my, 100, 200, 200, 70)) {
				colorTheme = Color.RED;
			} else if (mouseOver(mx, my, 100, 300, 200, 70)) {
				colorTheme = Color.GREEN;
			} else if (mouseOver(mx, my, 100, 400, 200, 70)) {
				colorTheme = Color.YELLOW;
			} else if (mouseOver(mx, my, 100, 500, 200, 70)) {
				colorTheme = Color.ORANGE;
			} else if (mouseOver(mx, my, 100, 600, 200, 70)) {
				colorTheme = Color.BLUE;
			} else if (mouseOver(mx, my, 100, 700, 200, 70)) {
				colorTheme = Color.GRAY;
			} else if (mouseOver(mx, my, 350, 100, 200, 70)) {
				colorTheme = Color.LIGHT_GRAY;
			}

		} else if (game.gameState == Game.STATE.PauseSettings) {
			// pause settings state
			if (mouseOver(mx, my, Game.WIDTH / 2 - 200 / 2, 650, 200, 64)) {
				// back button
				game.gameState = Game.STATE.Pause;
			} else if (mouseOver(mx, my, Game.WIDTH / 2 - 25 / 2, 525, 25, 25)) { // mute control
				if (bgMusic.isMuted()) {
					bgMusic.setMuted(false);
					bgMusic.setVolume(level);
				} else {
					bgMusic.setMuted(true);
					bgMusic.setVolume(0f);
				}
			} else if (mouseOver(mx, my, 100, 100, 200, 70)) {
				colorTheme = Color.WHITE;
			} else if (mouseOver(mx, my, 100, 200, 200, 70)) {
				colorTheme = Color.RED;
			} else if (mouseOver(mx, my, 100, 300, 200, 70)) {
				colorTheme = Color.GREEN;
			} else if (mouseOver(mx, my, 100, 400, 200, 70)) {
				colorTheme = Color.YELLOW;
			} else if (mouseOver(mx, my, 100, 500, 200, 70)) {
				colorTheme = Color.ORANGE;
			} else if (mouseOver(mx, my, 100, 600, 200, 70)) {
				colorTheme = Color.BLUE;
			} else if (mouseOver(mx, my, 100, 700, 200, 70)) {
				colorTheme = Color.GRAY;
			} else if (mouseOver(mx, my, 350, 100, 200, 70)) {
				colorTheme = Color.LIGHT_GRAY;
			}
		}

	} // end mousePressed()

	public void tick() {
		if (game.gameState == Game.STATE.Win) {
			hud.setScore(0);
			hud.setLevel(1);
			handler.clearEnemies();
			handler.clearPlayer();
			handler.clearRock();
			if (levetate < 500)
				levetate++;
		}
	}

	public void render(Graphics g) {

		g.setColor(colorTheme);

		Font fnt = new Font("Aquire", Font.PLAIN, 50);
		Font fntSmall = new Font("Aquire", Font.PLAIN, 30);

		if (game.gameState == Game.STATE.Menu) {

			g.setFont(fnt); // title text
			g.drawString(Game.TITLE, Game.WIDTH / 2 - g.getFontMetrics().stringWidth(Game.TITLE) / 2, 250);

			g.setFont(fntSmall);

			// play button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
			g.drawString("PLAY", (200 / 2 - g.getFontMetrics().stringWidth("PLAY") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 350);

			// help button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 450, 200, 64);
			g.drawString("HELP", (200 / 2 - g.getFontMetrics().stringWidth("HELP") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 450);

			// settings button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 550, 200, 64);
			g.drawString("SETTINGS", (200 / 2 - g.getFontMetrics().stringWidth("SETTINGS") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 550);

			// quit button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 650, 200, 64);
			g.drawString("QUIT", (200 / 2 - g.getFontMetrics().stringWidth("QUIT") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

			// developer
			g.setFont(new Font("JetBrains Mono Light", Font.PLAIN, 18));

			g.drawString("Developed By: Hans Duran", 10, Game.HEIGHT - 10);
			
			g.drawString("Special Thanks To: RealTutsGML", Game.WIDTH - 350, Game.HEIGHT - 10);

		} else if (game.gameState == Game.STATE.Help) {

			// help text
			g.setFont(fnt);
			g.drawString("HELP", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("HELP") / 2, 250);

			// back button
			g.setFont(fntSmall);
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 650, 200, 64);
			g.drawString(
				"BACK", (200 / 2 - g.getFontMetrics().stringWidth("BACK") / 2) + Game.WIDTH / 2 - 200 / 2,
				(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

			g.setFont(new Font("JetBrains Mono Thin", Font.PLAIN, 20));

			// rocks
			g.drawString(
				"Objective: Survive and collect rock samples floating in space.",
				Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Objective: Survive and collect rock samples floating in space.") / 2,
				(Game.HEIGHT / 2 + g.getFontMetrics().getHeight() / 2));

			// WASD
			g.drawString(
				"Pilot: Use WASD keys to move The Phasecraft and dodge comets.",
				Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Yoke: Use WASD keys to move The Phasecraft and dodge comets.") / 2,
				(Game.HEIGHT / 2 + g.getFontMetrics().getHeight() / 2) - 70);

			// regen tip
			g.drawString(
				"Engineers: After a few seconds of not taking damage, Engineers will automatically repair it.",
				Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Engineers: After a few seconds of not taking damage, Engineers will automatically repair it.") / 2,
				(Game.HEIGHT / 2 + g.getFontMetrics().getHeight() / 2) + 70);

		} else if (game.gameState == Game.STATE.End) {

			Font font = new Font("JetBrains Mono", Font.PLAIN, 20);

			g.setFont(fnt); // game over text
			g.drawString("GAME OVER", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("GAME OVER") / 2, 250);

			g.setFont(font); // lose score text
			g.drawString("You lost with a score of: " + hud.getScore(),
					Game.WIDTH / 2 - g.getFontMetrics().stringWidth("You lost with a score of: " + hud.getScore()) / 2,
					Game.HEIGHT / 2 + g.getFontMetrics().getHeight() / 2);

			g.drawString("Level: " + hud.getLevel(),
					Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Level: " + hud.getLevel()) / 2,
					(Game.HEIGHT / 2 + g.getFontMetrics().getHeight() / 2) + 50);

			// try again button (left)
			g.setFont(fntSmall);
			g.drawRect(Game.WIDTH / 2 - 300, 650, 200, 64);
			g.drawString("TRY AGAIN",
					(200 / 2 - g.getFontMetrics().stringWidth("TRY AGAIN") / 2) + Game.WIDTH / 2 - 300,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

			// return to main menu button (right)
			g.drawRect(Game.WIDTH / 2 + 100, 650, 200, 64);
			g.drawString("MAIN MENU",
					(200 / 2 - g.getFontMetrics().stringWidth("MAIN MENU") / 2) + Game.WIDTH / 2 + 100,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

		} else if (game.gameState == Game.STATE.Pause) {

			g.setColor(Color.BLACK);

			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(colorTheme);
			g.setFont(fnt); // pause text
			g.drawString("Pause", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Pause") / 2, 150);

			g.setFont(fntSmall);
			// resume button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
			g.drawString("RESUME", (200 / 2 - g.getFontMetrics().stringWidth("RESUME") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 350);

			// main menu button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 450, 200, 64);
			g.drawString("MAIN MENU", (200 / 2 - g.getFontMetrics().stringWidth("MAIN MENU") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 450);

			// settings button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 550, 200, 64);
			g.drawString("SETTINGS", (200 / 2 - g.getFontMetrics().stringWidth("SETTINGS") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 550);

			// quit button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 650, 200, 64);
			g.drawString("QUIT", (200 / 2 - g.getFontMetrics().stringWidth("QUIT") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

		} else if (game.gameState == Game.STATE.Difficulty) {

			g.setFont(fnt); // select text
			g.drawString("Select Difficulty", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Select Difficulty") / 2, 150);

			g.setFont(fntSmall);
			// menu button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 350, 200, 64);
			g.drawString("Normal", (200 / 2 - g.getFontMetrics().stringWidth("Normal") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 350);

			// hard button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 450, 200, 64);
			g.drawString("Hard",
					(200 / 2 - g.getFontMetrics().stringWidth("Hard") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 450);

			// back button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 550, 200, 64);
			g.drawString("Back", (200 / 2 - g.getFontMetrics().stringWidth("Back") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 550);

		} else if (game.gameState == Game.STATE.Settings || game.gameState == Game.STATE.PauseSettings) {

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			g.setColor(colorTheme);
			g.setFont(fnt);
			g.drawString("Settings", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Settings") / 2, 150); // settings text

			g.setFont(fntSmall);

			slider.render(g);
			g.drawString("Volume", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Volume") / 2, 500); // volume text

			// mute switch
			if (bgMusic.isMuted())
				g.drawRect(Game.WIDTH / 2 - 25 / 2, 525, 25, 25);
			else
				g.fillRect(Game.WIDTH / 2 - 25 / 2, 525, 25, 25);
			
			// back button
			g.drawRect(Game.WIDTH / 2 - 200 / 2, 650, 200, 64);
			g.drawString("Back", (200 / 2 - g.getFontMetrics().stringWidth("Back") / 2) + Game.WIDTH / 2 - 200 / 2,
					(64 / 2 + g.getFontMetrics().getHeight() / 2) + 650);

			/* Color Themes render */

			// White
			g.drawRect(100, 100, 200, 70);
			g.drawString("White", (200 - g.getFontMetrics().stringWidth("White") / 2), 147);

			// Red
			g.drawRect(100, 200, 200, 70);
			g.drawString("Red", (200 - g.getFontMetrics().stringWidth("Red") / 2), 247);

			// Green
			g.drawRect(100, 300, 200, 70);
			g.drawString("Green", (200 - g.getFontMetrics().stringWidth("Green") / 2), 347);

			// Yellow
			g.drawRect(100, 400, 200, 70);
			g.drawString("Yellow", (200 - g.getFontMetrics().stringWidth("Yellow") / 2), 447);
			
			// Orange
			g.drawRect(100, 500, 200, 70);
			g.drawString("Orange", (200 - g.getFontMetrics().stringWidth("Orange") / 2), 547);

			// Blue
			g.drawRect(100, 600, 200, 70);
			g.drawString("Blue", (200 - g.getFontMetrics().stringWidth("Blue") / 2), 647);

			// Gray
			g.drawRect(100, 700, 200, 70);
			g.drawString("Gray", (200 - g.getFontMetrics().stringWidth("Gray") / 2), 747);

			// Gray
			g.drawRect(350, 100, 200, 70);
			g.drawString("Light Gray", (200 - g.getFontMetrics().stringWidth("Light Gray") / 2) + 250, 147);
			
		} else if (game.gameState == Game.STATE.Win) {

			Font font = new Font("JetBrains Mono", Font.PLAIN, 20);
			g.setFont(fnt);
			g.drawString("The End", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("The End") / 2,
						Game.HEIGHT / 2 - g.getFontMetrics().getHeight()); // The end text

			g.setFont(font);

			g.drawString("Developed by Hans Duran",
						Game.WIDTH / 2 - g.getFontMetrics().stringWidth("Developed by Hans Duran") / 2, (Game.HEIGHT + 100) - levetate);

			// game.alternateWin(g);
			
		}
	} // end render()

	public void hideCursor() {
		// disable mouse cursor while in game
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0),
				"invisibleCursor");
		game.setCursor(transparentCursor);
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if ((mx > x && mx < x + width) && (my > y && my < y + height))
			return true;
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		if(game.gameState == Game.STATE.Settings || game.gameState == Game.STATE.PauseSettings) {
			if (mouseOver(x, my, slider.getX() - 200, slider.getY(), slider.getLength() + 400, slider.getHeight())) {
				slider.setX(Game.clamp(x - slider.getLength() / 2, slider.getSliderX(),
						((slider.getSliderX() + slider.getSliderLength()) - slider.getLength()) + 1));
				level = Game.changeScale(1.5f, 196f, (float)slider.getX() - (float)slider.getSliderX());
				// int test = slider.getSliderLength();
				// int test = slider.getX() - slider.getSliderX();
				level = Game.clamp(level, 0f, 1f);
				if (!bgMusic.isMuted())
					bgMusic.setVolume(level);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}