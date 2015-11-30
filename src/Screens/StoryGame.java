package Screens;
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StoryGame {
	
	private Scene myScene;
	private Group myRoot;
	private Stage myStage;
	private AScreen myScreen;
	
	public static final int WIDTH;
	public static final int HEIGHT;
	static{
		int width, height;
		try{
			Dimension sz = Toolkit.getDefaultToolkit().getScreenSize();
			width = (int) sz.getWidth();
			height = (int) sz.getHeight();
		}
		catch(Exception e){
			width = 800;
			height = 800;
		}
		WIDTH = width;
		HEIGHT = height;
	}
	
	public Scene init(Stage stage){
		this.myStage = stage;
		this.myRoot = new Group();
		this.myScene = new Scene(myRoot,WIDTH,HEIGHT,Color.WHITE);
		this.myScreen = new LoadingScreen(this);
		this.myScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		this.myScene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
		return myScene;
		
	}

	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		this.myScreen.update(elapsedTime);
	}
	
	private void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		this.myScreen.handleKeyReleased(code);
	}

	private void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub
		this.myScreen.handleKeyPressed(code);
	}
	
	public void addToRoot(Node node) {
		// TODO Auto-generated method stub
		this.myRoot.getChildren().add(node);
	}
	
	public int removeFromRoot(Node node){
		for(int i = 0; i < this.myRoot.getChildren().size(); i++){
			if(this.myRoot.getChildren().get(i).equals(node)){
				this.myRoot.getChildren().remove(node);
				return i;
			}
		}
		return -1;
	}
	
	public void clearRoot(){
		this.myRoot.getChildren().clear();
	}
	
	public void setScreen(AScreen screen){
		this.myScreen = screen;
	}
	
	public Stage getStage(){
		return this.myStage;
	}
	
	public Group getRoot(){
		return this.myRoot;
	}
	

}
