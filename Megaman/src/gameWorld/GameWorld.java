package gameWorld;

import java.awt.Graphics2D;
import java.io.IOException;

import action.ActionKey;
import backGroundMap.BackGroundMap;
import camera.Camera;
import controller.ControllerGameWorld;
import loadData.InputData;
import physicalMap.PhysicalMap;

public abstract class GameWorld {

	public static final int NORMAL = 0;
	public static final int PAUSE = 1;
	public static final int ENDGAME = 2;
	public static final int HOIHP = 3;
	public static final int ATTACKBOSS = 4;
	public static final int PLAYTUTORIAL = 5;
	public static final int BOSSDEATH = 6;
	public static final int PLAYTUTORIALNOTRECTANGLE = 7;
	public static final int WINGAMESWITCHSTAGE = 8;
	public static final int WINALLGAME = 9;

	private int STAGE;

	private String name;

	private ActionKey action;
	private InputData inputData;
	private Camera camera;
	private BackGroundMap backGroundMap;

	private PhysicalMap physicalMap;

	private ControllerGameWorld controller;

	public GameWorld(ControllerGameWorld controller, String name) {
		this.name = name;
		this.controller = controller;
		action = new ActionKey(this);
	}

	public abstract void upload() throws IOException;

	public abstract void draw(Graphics2D g2);

	public ActionKey getAction() {
		return action;
	}

	public void setAction(ActionKey action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSTAGE() {
		return STAGE;
	}

	public void setSTAGE(int sTAGE) {
		STAGE = sTAGE;
	}

	public static int getNormal() {
		return NORMAL;
	}

	public static int getPause() {
		return PAUSE;
	}

	public static int getEndgame() {
		return ENDGAME;
	}

	public static int getHoihp() {
		return HOIHP;
	}

	public static int getAttackboss() {
		return ATTACKBOSS;
	}

	public static int getPlaytutorial() {
		return PLAYTUTORIAL;
	}

	public static int getBossdeath() {
		return BOSSDEATH;
	}

	public static int getPlaytutorialnotrectangle() {
		return PLAYTUTORIALNOTRECTANGLE;
	}

	public static int getWingameswitchstage() {
		return WINGAMESWITCHSTAGE;
	}

	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public ControllerGameWorld getController() {
		return controller;
	}

	public void setController(ControllerGameWorld controller) {
		this.controller = controller;
	}

	public static int getWinallgame() {
		return WINALLGAME;
	}

	public PhysicalMap getPhysicalMap() {
		return physicalMap;
	}

	public void setPhysicalMap(PhysicalMap physicalMap) {
		this.physicalMap = physicalMap;
	}

	public BackGroundMap getBackGroundMap() {
		return backGroundMap;
	}

	public void setBackGroundMap(BackGroundMap backGroundMap) {
		this.backGroundMap = backGroundMap;
	}

}
