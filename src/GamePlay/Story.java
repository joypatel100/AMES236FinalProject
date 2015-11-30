package GamePlay;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public class Story {
	
	private List<ImageView> myPages;
	private int myIndex;
	
	public Story(){
		this.myPages = new ArrayList<>();
		this.myIndex = 0;
	}
	
	public ImageView next(){
		this.myIndex = Math.min(this.myIndex+1, this.myPages.size()-1);
		return this.myPages.get(myIndex);
	}
	
	public ImageView previous(){
		this.myIndex = Math.max(this.myIndex-1, 0);
		return this.myPages.get(myIndex);
	}
	
	public List<ImageView> getPages(){
		return this.myPages;
	}
	
	public boolean atEnd(){
		return this.myIndex == this.myPages.size()-1;
	}
	
	public boolean isBeginning(){
		return this.myIndex == 0;
	}

}
