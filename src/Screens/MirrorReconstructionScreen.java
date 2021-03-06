package Screens;

import java.util.Map;

import GamePlay.StoryLines;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;

public class MirrorReconstructionScreen extends MirrorAnimationScreen{

	private int countComplete;

	public MirrorReconstructionScreen(StoryGame game, StoryLines storylines) {
		super(game, storylines);
		// TODO Auto-generated constructor stub
	}

	@Override 
	protected void init(){
		countComplete = 0; 
		
		if(this.myStoryLine.getCollected().size() >= 3){
			Map<Image,ImageView> mirrors = this.myStoryLine.getMirrors();
			this.mirrorP1_o = mirrors.get(this.myStoryLine.getCollected().get(0).getImage());
			this.mirrorP2_b = mirrors.get(this.myStoryLine.getCollected().get(1).getImage());
			this.mirrorP3_p = mirrors.get(this.myStoryLine.getCollected().get(2).getImage());
		}
		//this.myStoryGame.addToRoot(emptyMirror);	
	}

	@Override 
	protected void startAnimation(){
		this.mirrorP1_o.setX(-1000);
		this.mirrorP1_o.setY(-1000);

		this.mirrorP3_p.setX(StoryGame.WIDTH);
		this.mirrorP3_p.setY(0);

		this.mirrorP2_b.setX(StoryGame.WIDTH/2);
		this.mirrorP2_b.setY(StoryGame.HEIGHT);

		this.myStoryGame.clearRoot();
		this.myStoryGame.addToRoot(myBackground);
		
		this.myStoryGame.addToRoot(mirrorP1_o);
		this.myStoryGame.addToRoot(mirrorP2_b);
		this.myStoryGame.addToRoot(mirrorP3_p);

		LineTo lt = new LineTo(StoryGame.WIDTH/2,StoryGame.HEIGHT/2);

		MoveTo mt1 = new MoveTo(0,0);
		Path path1 = genPath(mt1,lt);
		play(path1,mirrorP1_o);

		MoveTo mt2 = new MoveTo(StoryGame.WIDTH,0);
		Path path2 = genPath(mt2,lt);
		play(path2,mirrorP3_p);

		MoveTo mt3 = new MoveTo(StoryGame.WIDTH/2,StoryGame.HEIGHT);
		Path path3 = genPath(mt3,lt);
		play(path3,mirrorP2_b);

		//this.myStoryGame.addToRoot(myMirror);

		Text complete = new Text("Press Enter");
		complete.setTranslateX(StoryGame.WIDTH/2);
		complete.setTranslateY(StoryGame.HEIGHT/2);
		this.myStoryGame.addToRoot(complete);
	}

	@Override
	protected void completeAnimation(){
		countComplete++;
		if(countComplete == 3){
			//this.myStoryGame.addToRoot(myMirror);
		}
	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub
		switch(code){
		case ENTER:
			this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
			break;
		default:
			break;
		}
	}


}
