package Screens;

import GamePlay.StoryLines;
import Utility.UIUtil;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MirrorAnimationScreen extends AScreen {
	protected PathTransition myAnimation;
	protected double centerX, centerY;
	protected ImageView myMirror;
	protected boolean finished;
	protected ImageView emptyMirror, mirrorP1_o, mirrorP2_b, mirrorP3_p;
	private StoryLines myStoryLine;
	
	public MirrorAnimationScreen(StoryGame game, StoryLines sl) {
		super(game);
		ImageView background = UIUtil.initImageView(UIUtil.getImage("galaxy.png"), 0, 0);
		background.fitWidthProperty().bind(this.myStoryGame.getStage().widthProperty());
		background.fitHeightProperty().bind(this.myStoryGame.getStage().heightProperty());
		this.myStoryGame.addToRoot(background);
		Image image = UIUtil.getImage("mirror_orig.png");
		centerX = StoryGame.WIDTH/2-image.getWidth()/2;
		centerY = StoryGame.HEIGHT/2-image.getHeight()/2;
		myMirror = UIUtil.initImageView(image,centerX,centerY);
		emptyMirror = UIUtil.initImageView(UIUtil.getImage("mirror_empty.png"), 
				centerX,centerY);
		mirrorP1_o = UIUtil.initImageView(UIUtil.getImage("piece1_plain.png"), 
				centerX, centerY);
		mirrorP2_b = UIUtil.initImageView(UIUtil.getImage("piece2_plain.png"), 
				centerX, centerY);
		mirrorP3_p = UIUtil.initImageView(UIUtil.getImage("piece3_plain.png"), 
				centerX, centerY);
		finished = false;
		this.myStoryLine = sl;
		init();
	}
	
	protected void init(){
		this.myStoryGame.addToRoot(myMirror);
	}
	
	protected void startAnimation(){
		Text complete = new Text("Press Enter");
		complete.setTranslateX(StoryGame.WIDTH/2);
		complete.setTranslateY(StoryGame.HEIGHT/10);
		this.myStoryGame.addToRoot(complete);
		
		this.myStoryGame.addToRoot(emptyMirror);
		this.myStoryGame.addToRoot(mirrorP1_o);
		this.myStoryGame.addToRoot(mirrorP2_b);
		this.myStoryGame.addToRoot(mirrorP3_p);
		
		MoveTo mt = new MoveTo(StoryGame.WIDTH/2, StoryGame.HEIGHT/2);
		Path path1 = genPath(mt,new LineTo(0,0));
		play(path1,mirrorP1_o);
		
		Path path2 = genPath(mt,new LineTo(StoryGame.WIDTH,0));
		play(path2,mirrorP3_p);
		
		Path path3 = genPath(mt,new LineTo(StoryGame.WIDTH/2,StoryGame.HEIGHT));
		play(path3,mirrorP2_b);
		
		this.myStoryGame.removeFromRoot(emptyMirror);
		this.myStoryGame.removeFromRoot(myMirror);
	}

	protected Path genPath(MoveTo mt, LineTo lt){
		Path path = new Path();
		path.getElements().addAll(mt, lt);
		path.setFill(null);
		path.setStroke(Color.TRANSPARENT);
		path.setStrokeWidth(2);
		return path;
	}
	
	protected void play(Path path, ImageView iv){
		myAnimation = new PathTransition(Duration.millis(1000), path, iv);
        myAnimation.setCycleCount(1);
		myAnimation.setAutoReverse(false);
		myAnimation.setDelay(new Duration(500));
		myAnimation.play();
		myAnimation.setOnFinished(e ->completeAnimation());
	}
	
	protected void completeAnimation(){
		
	}
	
	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub
		if(!finished){
			finished = true;
			startAnimation();
		}
		
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
			this.myStoryGame.setScreen(new MapScreen(this.myStoryGame, this.myStoryLine));
			break;
		default:
			break;
		}
	}

}
