package Screens;

import java.util.ArrayList;
import java.util.List;

import GamePlay.GameCharacter;
import GamePlay.GameCharacter.State;
import GamePlay.Story;
import GamePlay.StoryLines;
import Utility.UIUtil;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class StoryScreen extends AScreen{

	private Story myStory;
	private ImageView myCurrent;
	private GameCharacter myCharacter;
	private StoryLines myStoryLines;

	public StoryScreen(StoryGame game, Story story, StoryLines storylines) {
		super(game);
		// TODO Auto-generated constructor stub
		this.myStory = story;
		this.myStoryLines = storylines;
		this.myCurrent = story.getPages().get(0);
		this.myStoryGame.addToRoot(myCurrent);
		List<ImageView> left = UIUtil.initIV(MapScreen.LEFT);
		List<ImageView> right = UIUtil.initIV(MapScreen.RIGHT);
		myCharacter = new GameCharacter(.1*((double) StoryGame.WIDTH),
				.75*((double) StoryGame.HEIGHT),7);
		myCharacter.setLeft(left);
		myCharacter.setRight(right);
		myCharacter.setBack(new ArrayList<>());
		myCharacter.setFront(new ArrayList<>());
		this.myCharacter.initRight();
		this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		myCharacter.removeCharacter(this.myStoryGame.getRoot());
		this.myCharacter.update();
		this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
		int i = this.myStoryGame.removeFromRoot(myCurrent);
		boolean updated = false;
		if(this.myCharacter.getX() > .9*((double) StoryGame.WIDTH) && this.myCharacter.getState()==State.RIGHT){
			this.myCurrent = this.myStory.next();
			if(this.myStory.atEnd()){
				this.myStoryLines.next();
				if(this.myStoryLines.isOver()){
					this.myStoryGame.setScreen(new MirrorReconstructionScreen(this.myStoryGame));
				}
				else{
					this.myStoryGame.setScreen(new MapScreen(this.myStoryGame,myStoryLines));
				}
				this.myCurrent = null;
			}
			else{
				this.myCharacter.setX(.1*((double) StoryGame.WIDTH));
				this.myCharacter.initRight();
			}
			updated = true;
		}
		else if(this.myCharacter.getX() < .1*((double) StoryGame.WIDTH)){
			if(this.myStory.isBeginning()){
				this.myCharacter.stop();
				this.myCharacter.setX(.1*((double) StoryGame.WIDTH));
			}
			else if(this.myCharacter.getState()==State.LEFT){
				this.myCharacter.setX(.9*((double) StoryGame.WIDTH));
				this.myCharacter.initLeft();
			}
			this.myCurrent = this.myStory.previous();
			updated = true;
		}
		if(this.myCurrent != null){
			if(updated){
				myCharacter.removeCharacter(this.myStoryGame.getRoot());
				this.myStoryGame.addToRoot(this.myCharacter.getCurrentImage());
			}
			this.myStoryGame.getRoot().getChildren().add(i, myCurrent);
		}
	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		switch(code){
		case RIGHT:
			this.myCharacter.stop();
			break;
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
