package Screens;

import Utility.UIUtil;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LoadingScreen extends AScreen{

	public LoadingScreen(StoryGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		BorderPane borderPane = new BorderPane();
		Text continueTF = UIUtil.initText("Press Enter to Continue", StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		borderPane.setCenter(continueTF);
		this.myStoryGame.addToRoot(borderPane);
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		switch(code){
		case ENTER:
			this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
			break;
		default:
			break;
		}
	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub

	}
}
