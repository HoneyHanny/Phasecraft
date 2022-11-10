package com.tutorial.main;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window {

	JFrame frame;

	// public Window(int width, int height, String title, Game game) {
	public Window(Game game, String title) {
		frame = new JFrame();

		frame.setTitle(title);
		// Dimension 3840 x 2160


		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		
		// frame.setIconImage(icon);

		frame.add(game);
		// frame.pack();
		frame.setUndecorated(true);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
