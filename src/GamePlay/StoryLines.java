package GamePlay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Utility.UIUtil;
import javafx.scene.image.ImageView;

public class StoryLines {

	private Map<Integer,Doors> myMap;
	private int myCurrentLevel;

	public StoryLines(String path){
		this.myCurrentLevel = 1;
		this.myMap = new HashMap<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		br.lines().forEach(line -> {
			if(line.matches("\\d+")){
				myMap.put(Integer.parseInt(line), new Doors());
			}
			else{
				File dir = new File(line);
				for(String fileName: dir.list()){
					try {
						BufferedReader reader = new BufferedReader(new FileReader(fileName));
						Story story = new Story();
						reader.lines().forEach(imagePath -> {
							ImageView iv = UIUtil.initImageView(UIUtil.getImage(imagePath),0,0);
							story.getPages().add(iv);
						});
						myMap.get(Integer.parseInt(line)).addStory(story);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public Doors getCurrentDoors(){
		return this.myMap.get(myCurrentLevel);
	}
	
	public void next(){
		this.myCurrentLevel = Math.min(myCurrentLevel+1, Collections.max(myMap.keySet())); 
	}
	
	public boolean isOver(){
		return this.myCurrentLevel == Collections.max(myMap.keySet());
	}

}
