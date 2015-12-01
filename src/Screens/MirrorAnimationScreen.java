package Screens;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.animation.PathTransition;
import javafx.animation.ParallelTransition;
import Utility.UIUtil;
import javafx.scene.image.ImageView;

public class MirrorAnimationScreen extends AScreen {
	private PathTransition myAnimation;
	public MirrorAnimationScreen(StoryGame game) {
		super(game);
		ImageView mirror = UIUtil.initImageView(UIUtil.getImage("/Mirror/mirror_orig.png"), StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		this.myStoryGame.addToRoot(mirror);
		// TODO Auto-generated constructor stub
	}
	
	private void startAnimation(){
		ImageView emptyMirror = UIUtil.initImageView(UIUtil.getImage("/Mirror/mirror_empty.png"), StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		ImageView mirrorP1_o = UIUtil.initImageView(UIUtil.getImage("/Mirror/piece1_orange.png"), StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		ImageView mirrorP2_b = UIUtil.initImageView(UIUtil.getImage("/Mirror/piece2_blue.png"), StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		ImageView mirrorP3_p = UIUtil.initImageView(UIUtil.getImage("/Mirror/piece3_purple.png"), StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		
		this.myStoryGame.addToRoot(emptyMirror);
		this.myStoryGame.addToRoot(mirrorP1_o);
		this.myStoryGame.addToRoot(mirrorP2_b);
		this.myStoryGame.addToRoot(mirrorP3_p);
		
		Path path1 = new Path();
		path1.getElements().addAll(new MoveTo(StoryGame.WIDTH/2, StoryGame.HEIGHT/2), new LineTo(0,0));
		path1.setFill(null);
		path1.setStroke(Color.TRANSPARENT);
		path1.setStrokeWidth(2);
		myAnimation = new PathTransition(Duration.millis(1000), path1, mirrorP1_o);
        myAnimation.setCycleCount(1);
		myAnimation.setAutoReverse(false);
		myAnimation.setDelay(new Duration(500));
		myAnimation.play();
		
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
		switch(code){
		case ENTER:
			this.startAnimation();
			break;
		default:
			break;
		}
	}

}
