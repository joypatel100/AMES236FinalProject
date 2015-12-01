package Screens;

import java.util.List;
import java.util.Map;

import GamePlay.Doors;
import GamePlay.GameCharacter;
import GamePlay.Story;
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
		ImageView background = UIUtil.initImageView(UIUtil.getImage("background.png"), 0, 0);
		background.fitWidthProperty().bind(this.myStoryGame.getStage().widthProperty());
		background.fitHeightProperty().bind(this.myStoryGame.getStage().heightProperty());
		this.myStoryGame.addToRoot(background);
		
		this.myStoryLines = sl;
		this.myDoors = this.myStoryLines.getCurrentDoors();

		List<ImageView> left = UIUtil.initIV(LEFT);
		List<ImageView> right = UIUtil.initIV(RIGHT);
		List<ImageView> front = UIUtil.initIV(FRONT);
		List<ImageView> back = UIUtil.initIV(BACK);
		myCharacter = new GameCharacter(.2*((double) StoryGame.WIDTH),.75*((double) StoryGame.HEIGHT),5);
		myCharacter.setLeft(left);
		myCharacter.setRight(right);
		myCharacter.setFront(front);
		myCharacter.setBack(back);
		this.myCharacter.init();
		this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
		this.myDoors.constructDoors(StoryGame.WIDTH, StoryGame.HEIGHT, 
				myStoryLines).stream().forEach(img -> {this.myStoryGame.addToRoot(img);});

	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		if(this.myCharacter != null){
			myCharacter.removeCharacter(this.myStoryGame.getRoot());
			this.myCharacter.update();
			if(this.myCharacter.getX() < .1*((double) StoryGame.WIDTH)){
				this.myCharacter.setX(.1*((double) StoryGame.WIDTH));
				this.myCharacter.stop();
			}
			else if(this.myCharacter.getX() > .9*((double) StoryGame.WIDTH)){
				this.myCharacter.setX(.9*((double) StoryGame.WIDTH));
				this.myCharacter.stop();
			}
			if(this.myCharacter.getY() < .1*((double) StoryGame.HEIGHT)){
				this.myCharacter.setY(.1*((double) StoryGame.HEIGHT));
				this.myCharacter.stop();
			}
			else if(this.myCharacter.getY() > .9*((double) StoryGame.HEIGHT)){
				this.myCharacter.setY(.9*((double) StoryGame.HEIGHT));
				this.myCharacter.stop();
			}
			this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
			doorCollision();
		}
	}

	private void doorCollision(){
		Map<ImageView,Story> map = myDoors.getDoorMap();
		for(ImageView iv: map.keySet()){
			if(iv.getBoundsInParent().intersects(myCharacter.getCurrentImage().getBoundsInParent())){
				//this.myCharacter = null;
				this.myStoryGame.setScreen(new StoryScreen(this.myStoryGame,map.get(iv),myStoryLines));
				break;
			}
		}
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
		case Q:
			this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
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
