package GamePlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utility.UIUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	
	public List<ImageView> constructDoors(double width, double height, StoryLines storylines){
		myMap = new HashMap<>();
		Image doorImg = storylines.getShard(0).getImage();
		List<ImageView> doors = new ArrayList<>();
		for(int i = 0; i < myStories.size(); i++){
			int j = i - myStories.size()/2;
			double x = width/2 - doorImg.getWidth()*j - 30*j;
			double y = height*.55;
			ImageView iv = UIUtil.initImageView(storylines.getShard().getImage(), x, y);
			doors.add(iv);
			this.myMap.put(iv, myStories.get(i));
		}
		return doors;
	}
	
	public Map<ImageView, Story> getDoorMap(){
		return this.myMap;
	}
	
}
