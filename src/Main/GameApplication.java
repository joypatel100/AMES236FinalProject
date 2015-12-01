package Main;
import Screens.StoryGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameApplication extends Application{
	
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StoryGame game = new StoryGame();
		stage.setTitle("Interactive Story");
		Scene scene = game.init(stage);
		stage.setScene(scene);
		stage.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> game.update(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}

}
