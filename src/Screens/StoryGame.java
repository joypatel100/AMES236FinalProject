package Screens;
import javafx.collections.ObservableList;
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
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
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
	
	public void removeFromRoot(Node node){
		this.myRoot.getChildren().remove(node);
	}
	
	public void clearMyRoot(){
		this.myRoot.getChildren().clear();
	}
	
	public void setScreen(AScreen screen){
		this.myScreen = screen;
	}
	

}
