package GamePlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utility.UIUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Doors {
	
	private List<Story> myStories;
	private Map<ImageView, Story> myMap;
	
	public Doors(){
		this.myStories = new ArrayList<>();
	}
	
	public void addStory(Story story){
		this.myStories.add(story);
	}
	
	public List<Story> getStories(){
		return this.myStories;
	}
	
	public List<ImageView> constructDoors(Image doorImg, double width, double height){
		myMap = new HashMap<>();
		List<ImageView> doors = new ArrayList<>();
		for(int i = 0; i < myStories.size(); i++){
			int j = i - myStories.size()/2;
			double x = width/2 - doorImg.getWidth()*j - 30*j;
			double y = height*.25;
			ImageView iv = UIUtil.initImageView(doorImg, x, y);
			doors.add(iv);
			this.myMap.put(iv, myStories.get(i));
		}
		return doors;
	}
	
	public Map<ImageView, Story> getDoorMap(){
		return this.myMap;
	}
	
}
