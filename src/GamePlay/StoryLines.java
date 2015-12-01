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
import javafx.stage.Stage;

public class StoryLines {

	private Map<Integer,Doors> myMap;
	private int myCurrentLevel;

	public StoryLines(String path, Stage stage){
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
			line = line.trim();
			int mapI = 1;
			if(line.matches("\\d+")){
				mapI = Integer.parseInt(line);
				myMap.put(mapI, new Doors());
			}
			else if(!line.endsWith("DS_Store")){
				System.out.println(line);
				File dir = new File(line);
				Story story = new Story();
				for(File file: dir.listFiles()){
					if(file.getAbsolutePath().endsWith("DS_Store")){
						continue;
					}
					System.out.println(file.getAbsolutePath());
					ImageView iv = UIUtil.initImageView(UIUtil.getImage(file), 0, 0);
					iv.fitWidthProperty().bind(stage.widthProperty());
					iv.fitHeightProperty().bind(stage.heightProperty());
					story.getPages().add(iv);
				}
				myMap.get(mapI).addStory(story);
			}
		});
	}

	public Doors getCurrentDoors(){
		return this.myMap.get(myCurrentLevel);
	}

	public void next(){
		this.myCurrentLevel += 1; 
	}

	public boolean isOver(){
		return this.myCurrentLevel == Collections.max(myMap.keySet())+1;
	}

}
