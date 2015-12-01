package Screens;

import java.io.File;

import GamePlay.StoryLines;
import Utility.UIUtil;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class LoadingScreen extends AScreen{
	
	private File myFile;
	private boolean hasLoaded;

	public LoadingScreen(StoryGame game, File file) {
		super(game);
		// TODO Auto-generated constructor stub
		ImageView background = UIUtil.initImageView(UIUtil.getImage("identity.jpg"), 0, 0);
		background.fitWidthProperty().bind(this.myStoryGame.getStage().widthProperty());
		background.fitHeightProperty().bind(this.myStoryGame.getStage().heightProperty());
		this.myStoryGame.addToRoot(background);
		BorderPane borderPane = new BorderPane();
		Text continueTF = UIUtil.initText("Loading", StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		borderPane.setCenter(continueTF);
		this.myStoryGame.addToRoot(borderPane);
		this.myFile = file;
		this.hasLoaded = false;
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		if(!hasLoaded){
			hasLoaded = true;
			StoryLines sl = null;
			try{
				sl = new StoryLines(myFile.getPath(),this.myStoryGame.getStage());
			}
			catch(Exception e){
				this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
			}
			if(sl!=null){
				this.myStoryGame.setScreen(new MapScreen(this.myStoryGame, sl));
			}
		}
	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub
		/*switch(code){
		case ENTER:
			this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
			break;
		default:
			break;
		}
		*/
	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub

	}
}
