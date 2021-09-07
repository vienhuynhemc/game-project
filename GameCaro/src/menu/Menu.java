package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import caRo.GameWorld;

public class Menu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JMenu mnFile, mnEdit, mnHelp;
	public JMenuItem mnAbout, mnUndo, mnRedo, mnExit, mnMusic, mnEffor, mnNewMatch, mnReset;

	public JMenuItem mnStart;

	private GameWorld game;

	public Menu(GameWorld game) {
		this.game = game;

		taoMenuEdit();

		taoPhimTat();

		action();

		setMn();

		setBackGround();

	}

	public void taoMenuEdit() {
		add(mnFile = new JMenu("File"));
		add(mnEdit = new JMenu("Edit"));
		add(mnHelp = new JMenu("Help"));
		mnHelp.add(mnAbout = new JMenuItem("About"));
		mnAbout.setIcon(new ImageIcon("image/mnAbout.png"));
		mnFile.add(mnStart = new JMenuItem("Start"));
		mnStart.setIcon(new ImageIcon("image/mnStart.png"));
		mnFile.add(mnNewMatch = new JMenuItem("New Match"));
		mnNewMatch.setIcon(new ImageIcon("image/mnNew.png"));
		mnFile.add(mnReset = new JMenuItem("Reset"));
		mnFile.addSeparator();
		mnReset.setIcon(new ImageIcon("image/mnReset.png"));
		mnFile.add(mnExit = new JMenuItem("Exit"));
		mnExit.setIcon(new ImageIcon("image/mnExit.png"));
		mnEdit.add(mnUndo = new JMenuItem("Undo"));
		mnEdit.add(mnRedo = new JMenuItem("Redo"));
		mnUndo.setIcon(new ImageIcon("image/mnUndo.png"));
		mnRedo.setIcon(new ImageIcon("image/mnRedo.png"));
		mnUndo.setEnabled(false);
		mnRedo.setEnabled(false);
		mnEdit.addSeparator();
		mnEdit.add(mnMusic = new JMenuItem("On/Off Music"));
		mnEdit.add(mnEffor = new JMenuItem("On/Off Effor Sound"));
		mnMusic.setIcon(new ImageIcon("image/mnSound.png"));
		mnEffor.setIcon(new ImageIcon("image/mnEffor.png"));
	}

	public void taoPhimTat() {
		mnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		mnStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		mnNewMatch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		mnReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		mnUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		mnRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		mnMusic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		mnEffor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		mnAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
	}

	public void action() {
		mnAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPanel2().about();
			}
		});
		mnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.panel3.exit();
			}
		});
		mnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.panel3.startGame();
				if (game.panel3.statrr == true) {
					mnStart.setEnabled(false);
					mnReset.setEnabled(true);
					mnNewMatch.setEnabled(true);
				}
			}
		});
		mnReset.setEnabled(false);
		mnNewMatch.setEnabled(false);
		mnNewMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.panel3.newMatch();
			}
		});
		mnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.panel3.resetGame();
			}
		});
		mnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.panel3.statrr == true && game.panel3.undo >= 1 && game.panel3.choiMoi == false)
					game.panel3.actionUndo();
			}
		});
		mnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.panel3.statrr == true && game.panel3.redo >= 1 && game.panel3.choiMoi == false)
					game.panel3.actionRedo();
			}
		});
		mnMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getSound().nhacNenGame();
			}
		});
		mnEffor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.panel3.hieuUngAmTham();
			}
		});

	}

	public void setMn() {
		mnStart.setMnemonic('S');
		mnExit.setMnemonic('X');
		mnFile.setMnemonic('F');
		mnEdit.setMnemonic('E');
		mnHelp.setMnemonic('H');
		mnAbout.setMnemonic('A');
		mnUndo.setMnemonic('U');
		mnRedo.setMnemonic('R');
		mnMusic.setMnemonic('M');
		mnEffor.setMnemonic('F');
		mnNewMatch.setMnemonic('N');
		mnReset.setMnemonic('R');
	}

	public void setBackGround() {
		setBackground(new Color(255, 255, 255));
		mnStart.setBackground(Color.white);
		mnReset.setBackground(Color.white);
		mnExit.setBackground(Color.white);
		mnNewMatch.setBackground(Color.white);
		mnUndo.setBackground(Color.white);
		mnRedo.setBackground(Color.white);
		mnMusic.setBackground(Color.white);
		mnEffor.setBackground(Color.white);
		mnAbout.setBackground(Color.white);
	}

}
