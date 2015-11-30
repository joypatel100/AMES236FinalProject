package Screens;

import java.util.ArrayList;
import java.util.List;

import GamePlay.Doors;
import GamePlay.GameCharacter;
import GamePlay.StoryLines;
import Utility.UIUtil;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class MapScreen extends AScreen{
	
	public static final String[] 
			LEFT = {"Left Stationary.png", "Left Walk Left.png", "Left Walk Right.png"},
			RIGHT = {"Right Stationary.png", "Right Walk Left.png", "Right Walk Right.png"}, 
			FRONT = {"Front Stationary.png", "Front Walk Left.png", "Front Walk Right.png"}, 
			BACK = {"Back Stationary.png", "Back Walk Left.png", "Back Walk Right.png"};
	
	private GameCharacter myCharacter;
	private StoryLines myStoryLines;
	private Doors myDoors;
	
	public MapScreen(StoryGame game, StoryLines sl){
		super(game);
		this.myStoryLines = sl;
		this.myDoors = this.myStoryLines.getCurrentDoors();
		
		List<ImageView> left = initIV(LEFT);
		List<ImageView> right = initIV(RIGHT);
		List<ImageView> front = initIV(FRONT);
		List<ImageView> back = initIV(BACK);
		myCharacter = new GameCharacter(StoryGame.WIDTH/2,StoryGame.HEIGHT/2,5);
		myCharacter.setLeft(left);
		myCharacter.setRight(right);
		myCharacter.setFront(front);
		myCharacter.setBack(back);
		this.myCharacter.init();
		this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
	}
	
	private List<ImageView> initIV(String[] paths){
		List<ImageView> result = new ArrayList<>();
		for(String path: paths){
			result.add(UIUtil.initImageView(UIUtil.getImage(path), 0, 0));
		}
		return result;
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		myCharacter.removeCharacter(this.myStoryGame.getRoot());
		this.myCharacter.update();
		this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		switch(code){
		case UP:
		case DOWN:
		case RIGHT:
		case LEFT:
			this.myCharacter.stop();
			break;
		default:
			break;
		}
	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub
		switch(code){
		case UP:
			this.myCharacter.setMovingBack();
			break;
		case DOWN:
			this.myCharacter.setMovingFront();
			break;
		case RIGHT:
			this.myCharacter.setMovingRight();
			break;
		case LEFT:
			this.myCharacter.setMovingLeft();
			break;
		default:
			break;
		}
		
		
	}

}