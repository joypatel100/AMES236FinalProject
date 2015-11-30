package GamePlay;

import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class GameCharacter {

	private double myX, myY, mySpeed;
	private List<ImageView> myFront, myBack, myRight, myLeft;
	private Integer myIndexFront, myIndexBack, myIndexRight, myIndexLeft;
	private ImageView myCurrent;

	private enum State{
		LEFT,RIGHT,BACK,FRONT,STOP;
	}
	
	private State myState;

	public GameCharacter(double x, double y, double speed){
		this.myX = x;
		this.myY = y;
		this.mySpeed = speed;
		this.myIndexFront = 0;
		this.myIndexBack = 0;
		this.myIndexRight = 0;
		this.myIndexLeft = 0;
		this.myState = State.STOP;
	}

	public void update(){
		switch(this.myState){
		case BACK:
			this.myCurrent = moveBack();
			break;
		case FRONT:
			this.myCurrent = moveFront();
			break;
		case LEFT:
			this.myCurrent = moveLeft();
			break;
		case RIGHT:
			this.myCurrent = moveRight();
			break;
		case STOP:
			break;
		default:
			break;
		}
		this.myCurrent.setX(myX);
		this.myCurrent.setY(myY);
	}

	public void init(){
		this.myCurrent = this.getFrontStation();
	}
	
	public ImageView getCurrentImage(){
		return this.myCurrent;
	}
	
	private ImageView move(List<ImageView> list, Integer index){
		ImageView result = list.get(index);
		index++;
		if(index >= list.size()){
			index = 0;
		}
		return result;
	}

	public void setMovingRight(){
		this.myState = State.RIGHT;
	}

	public void setMovingLeft(){
		this.myState = State.LEFT;
	}
	
	public void setMovingFront(){
		this.myState = State.FRONT;
	}
	
	public void setMovingBack(){
		this.myState = State.BACK;
	}
	
	public void stop(){
		switch(this.myState){
		case BACK:
			this.myCurrent = getBackStation();
			break;
		case FRONT:
			this.myCurrent = getFrontStation();
			break;
		case LEFT:
			this.myCurrent = getLeftStation();
			break;
		case RIGHT:
			this.myCurrent = getRightStation();
			break;
		case STOP:
			break;
		default:
			break;
		}
		this.myState = State.STOP;
	}

	private ImageView moveRight(){
		this.myX += mySpeed;
		return move(this.myRight,this.myIndexRight);
	}

	private ImageView moveLeft(){
		this.myX -= mySpeed;
		return move(this.myLeft,this.myIndexLeft);
	}

	private ImageView moveFront(){
		this.myY += mySpeed;
		return move(this.myFront,this.myIndexFront);
	}

	private ImageView moveBack(){
		this.myY -= mySpeed;
		return move(this.myBack,this.myIndexBack);
	}

	public void setFront(List<ImageView> myFront) {
		this.myFront = myFront;
	}

	public void setBack(List<ImageView> myBack) {
		this.myBack = myBack;
	}

	public void setRight(List<ImageView> myRight) {
		this.myRight = myRight;
	}

	public void setLeft(List<ImageView> myLeft) {
		this.myLeft = myLeft;
	}

	public double getX(){
		return this.myX;
	}

	public double getY(){
		return this.myY;
	}

	public ImageView getFrontStation(){
		return this.myFront.get(0);
	}

	public ImageView getBackStation(){
		return this.myBack.get(0);
	}

	public ImageView getLeftStation(){
		return this.myLeft.get(0);
	}

	public ImageView getRightStation(){
		return this.myRight.get(0);
	}
	
	public void removeCharacter(Group root){
		root.getChildren().removeAll(myBack);
		root.getChildren().removeAll(myFront);
		root.getChildren().removeAll(myLeft);
		root.getChildren().removeAll(myRight);
	}
}

