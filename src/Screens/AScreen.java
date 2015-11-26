package Screens;
import javafx.scene.input.KeyCode;

public abstract class AScreen {
	
	protected StoryGame myStoryGame;
	
	public AScreen(StoryGame game){
		this.myStoryGame = game;
		this.myStoryGame.clearMyRoot();
	}
	
	public abstract void update(double elapsedTime);
	
	public abstract void handleKeyReleased(KeyCode code);
	
	public abstract void handleKeyPressed(KeyCode code);

}
