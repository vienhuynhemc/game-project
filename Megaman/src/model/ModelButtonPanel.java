package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import controller.ControllerButtonPanel;
import gameDisplay.ButtonItem;
import gameDisplay.ButtonStart;
import sound.ManagerSound;

public class ModelButtonPanel {

	private ButtonStart buttonStart;

	private ButtonItem btTutorial;
	private ButtonItem btExit;
	private ButtonItem btCredits;

	private ControllerButtonPanel controller;

	public ModelButtonPanel(ControllerButtonPanel controller) throws IOException {
		this.controller = controller;

		buttonStart = new ButtonStart(400, 200, 200, 90);

		BufferedImage tutorial = ImageIO.read(new File("data/tutorial.png"));
		BufferedImage exit = ImageIO.read(new File("data/exit.png"));
		BufferedImage credits = ImageIO.read(new File("data/credits.png"));

		btTutorial = new ButtonItem(455, 300, 85, 25, tutorial);
		btCredits = new ButtonItem(455, 360, 85, 25, credits);
		btExit = new ButtonItem(455, 420, 85, 25, exit);
	}

	public void draw(Graphics g) {
		buttonStart.draw(g);
		btTutorial.draw(g);
		btExit.draw(g);
		btCredits.draw(g);
	}

	public void mousePress(MouseEvent e) {
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 10, 10);
		if (rect.intersects(buttonStart.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuClick").start();
			ManagerSound.getInstance().getListSound().get("menuClick").setFramePosition(0);
			buttonStart.setPress(true);
			controller.getButtonPanel().repaint();
		} else if (rect.intersects(btCredits.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuClick").start();
			ManagerSound.getInstance().getListSound().get("menuClick").setFramePosition(0);
		} else if (rect.intersects(btTutorial.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuClick").start();
			ManagerSound.getInstance().getListSound().get("menuClick").setFramePosition(0);
		} else if (rect.intersects(btExit.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuClick").start();
			ManagerSound.getInstance().getListSound().get("menuClick").setFramePosition(0);
		}
	}

	public void mouseReleased(MouseEvent e) {
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 10, 10);
		if (rect.intersects(buttonStart.getRectangleXuLyVaCham())) {
			try {
				ManagerSound.getInstance().getListSound().get("menuPress").start();
				ManagerSound.getInstance().getListSound().get("menuPress").setFramePosition(0);
				controller.getButtonPanel().getPanel().getGame().switchPanel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (rect.intersects(btCredits.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuPress").start();
			ManagerSound.getInstance().getListSound().get("menuPress").setFramePosition(0);
			JOptionPane.showMessageDialog(null, "Memaman - MapleStory\nVer1.0\nxx-08-2020");
			controller.getButtonPanel().getPanel().getLableB().repaint();
		} else if (rect.intersects(btTutorial.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuPress").start();
			ManagerSound.getInstance().getListSound().get("menuPress").setFramePosition(0);
			JOptionPane.showMessageDialog(null,
					"Phím tắt:\nA - Tấn công\nGiữ nút A: Tính tụ năng lượng\nSpace: Nhảy\nDi chuyển bằng 4 nút UP DOWN LEFT RIGHT");

			controller.getButtonPanel().getPanel().getLableB().repaint();
		} else if (rect.intersects(btExit.getRectangleXuLyVaCham())) {
			ManagerSound.getInstance().getListSound().get("menuPress").start();
			ManagerSound.getInstance().getListSound().get("menuPress").setFramePosition(0);
			System.exit(0);
		}
		buttonStart.setPress(false);
		controller.getButtonPanel().repaint();
	}

	public void mouseDragged(MouseEvent e) {
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 10, 10);
		if (!rect.intersects(buttonStart.getRectangleXuLyVaCham())) {
			buttonStart.setIn(false);
			controller.getButtonPanel().repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 10, 10);
		if (rect.intersects(buttonStart.getRectangleXuLyVaCham())) {
			if (!buttonStart.isIn()) {
				ManagerSound.getInstance().getListSound().get("menuEnter").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("menuEnter");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			buttonStart.setIn(true);
			controller.getButtonPanel().repaint();
		} else {
			buttonStart.setIn(false);
			controller.getButtonPanel().repaint();
		}

		if (rect.intersects(btTutorial.getRectangleXuLyVaCham())) {
			if (btTutorial.getWidth() == 85) {
				ManagerSound.getInstance().getListSound().get("menuEnter").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("menuEnter");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			btTutorial.setWidth(170);
			btTutorial.setHeight(50);
			btTutorial.setX(415);
			controller.getButtonPanel().repaint();
		} else {
			if (btTutorial.getWidth() == 170) {
				btTutorial.setWidth(85);
				btTutorial.setHeight(25);
				btTutorial.setX(455);
				controller.getButtonPanel().getPanel().getLableB().repaint();
			}
		}

		if (rect.intersects(btCredits.getRectangleXuLyVaCham())) {
			if (btCredits.getWidth() == 85) {
				ManagerSound.getInstance().getListSound().get("menuEnter").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("menuEnter");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			btCredits.setWidth(170);
			btCredits.setHeight(50);
			btCredits.setX(415);
			btCredits.setY(350);
			controller.getButtonPanel().repaint();
		} else {
			if (btCredits.getWidth() == 170) {
				btCredits.setWidth(85);
				btCredits.setHeight(25);
				btCredits.setX(455);
				btCredits.setY(360);
				controller.getButtonPanel().getPanel().getLableB().repaint();
			}
		}

		if (rect.intersects(btExit.getRectangleXuLyVaCham())) {
			if (btExit.getWidth() == 85) {
				ManagerSound.getInstance().getListSound().get("menuEnter").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("menuEnter");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
			btExit.setWidth(170);
			btExit.setHeight(50);
			btExit.setX(415);
			btExit.setY(410);
			controller.getButtonPanel().repaint();
		} else {
			if (btExit.getWidth() == 170) {
				btExit.setWidth(85);
				btExit.setHeight(25);
				btExit.setX(455);
				btExit.setY(420);
				controller.getButtonPanel().getPanel().getLableB().repaint();
			}
		}
	}

	public ButtonStart getButtonStart() {
		return buttonStart;
	}

}
