package GamePlay;

import java.util.ArrayList;
import java.util.List;

public class Doors {
	
	private List<Story> myStories;
	
	public Doors(){
		this.myStories = new ArrayList<>();
	}
	
	public void addStory(Story story){
		this.myStories.add(story);
	}
	
	public List<Story> getStories(){
		return this.myStories;
	}
	
}
