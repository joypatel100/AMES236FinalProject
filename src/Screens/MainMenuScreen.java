package Screens;

import java.io.File;

import GamePlay.StoryLines;
import Utility.UIUtil;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
		FileChooser chooser = new FileChooser();
		chooser.setTitle("File to Load");
		chooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		File file = chooser.showOpenDialog(this.myStoryGame.getStage());
		this.myStoryGame.setScreen(new MapScreen(this.myStoryGame, new StoryLines(file.getPath(),this.myStoryGame.getStage())));
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
