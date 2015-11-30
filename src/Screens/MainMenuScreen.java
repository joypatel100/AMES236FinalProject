package Screens;

import Utility.UIUtil;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class MainMenuScreen extends AScreen{

	public MainMenuScreen(StoryGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		initCreateButton();
		initLoadButton();
	}
	
	private void initCreateButton(){
		Button create = UIUtil.initButton("Create Story", .4*StoryGame.WIDTH, .4*StoryGame.HEIGHT);
		create.setOnAction(e -> createHandler());
		this.myStoryGame.addToRoot(create);
	}
	
	private void createHandler(){
		this.myStoryGame.setScreen(new CreateStoryScreen(this.myStoryGame));
	}
	
	private void initLoadButton(){
		Button load = UIUtil.initButton("Load Story", .4*StoryGame.WIDTH, .6*StoryGame.HEIGHT);
		load.setOnAction(e -> loadHandler());
		this.myStoryGame.addToRoot(load);
	}
	
	private void loadHandler(){
		
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub
		
	}

}
