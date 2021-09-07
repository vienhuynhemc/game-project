package gameWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import controller.ControllerGameWorld;
import loadData.InputDataLoading;
import loading.Loading;
import loading.Notification;
import sound.ManagerSound;
import view.GameFrame;

public class GameWorldLoading extends GameWorld {

	private Loading loading;

	private Notification notification;

	public GameWorldLoading(ControllerGameWorld controller, String name) throws IOException {
		super(controller, name);

		setInputData(new InputDataLoading(this));

		loading = new Loading(400, 200, this);

		notification = new Notification(400, 300, this);

	}

	@Override
	public void upload() throws IOException {
		if (getController().getModel().getMegamanX() == null) {
			getController().getModel().setMegamanX(new GameWorldMegamanX(getController(), "Megaman X"));
			getController().getModel().getArrayStage()[1] = getController().getModel().getMegamanX();
		}

		if (getController().getModel().getMapbleStory() == null) {
			getController().getModel().setMapbleStory(new GameWorldMapleStory(getController(), "Maple Story"));
			getController().getModel().getArrayStage()[2] = getController().getModel().getMapbleStory();
		}

		if (getController().getModel().getMapbleStory() != null && getController().getModel().getMegamanX() != null
				&& notification.getListNotification().size() == 0) {
			getController().getModel().setCurrentStage(1);
			ManagerSound.getInstance().getListSound().get("nhacNenMegamanX").start();
		}

		notification.upload();

	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);

		notification.draw(g2);
		loading.draw(g2);
	}

	public Loading getLoading() {
		return loading;
	}

	public void setLoading(Loading loading) {
		this.loading = loading;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
