package GamePlay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Utility.UIUtil;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StoryLines {

	private Map<Integer,Doors> myMap;
	private int myCurrentLevel;
	private static List<ImageView> myShards;
	private int myShardIndex;
	
	static{
		myShards = new ArrayList<>();
		myShards.add(UIUtil.initImageView("s_piece1_orange.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece1_red.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_blue.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_green.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_teal.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece3_pink.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece3_purple.png", 0, 0));
	}

	public StoryLines(String path, Stage stage){
		this.myCurrentLevel = 1;
		this.myMap = new HashMap<>();
		this.myShardIndex = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int mapI = 1;
		String line = "";
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			line = null;
			e.printStackTrace();
		}
		while(line != null){
			line = line.trim();
			if(line.matches("\\d+")){
				mapI = Integer.parseInt(line);
				myMap.put(mapI, new Doors());
				//System.out.println(mapI);
			}
			else if(!line.endsWith("DS_Store") && !line.equals("")){
				File dir = new File(line);
				Story story = new Story();
				Map<Integer,ImageView> map = new TreeMap<>();
				for(File file: dir.listFiles()){
					if(file.getAbsolutePath().endsWith("DS_Store")){
						continue;
					}
					ImageView iv = UIUtil.initImageView(UIUtil.getImage(file), 0, 0);
					iv.fitWidthProperty().bind(stage.widthProperty());
					iv.fitHeightProperty().bind(stage.heightProperty());
					map.put(getNum(file.getName()),iv);
					//story.getPages().add(iv);
				}
				for(Integer key: map.keySet()){
					story.getPages().add(map.get(key));
				}
				myMap.get(mapI).addStory(story);
			}
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				line = null;
				e.printStackTrace();
			}
		}
	}
	
	private int getNum(String string){
		int start = -1;
		int end = -1;
		for(int i = 0; i < string.length(); i++){
			char ch = string.charAt(i);
			if(Character.isDigit(ch) && start==-1){
				start = i;
			}
			if(!Character.isDigit(ch) && start!=-1){
				end = i;
				break;
			}
		}
		if(start==-1 || end==-1){
			return -1;
		}
		String num = string.substring(start, end).trim();
		if(num.matches("\\d+")){
			return Integer.parseInt(num);
		}
		return -1;
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
	
	public ImageView getShard(){
		ImageView result = this.myShards.get(this.myShardIndex);
		this.myShardIndex++;
		if(this.myShardIndex >= this.myShards.size()){
			this.myShardIndex = 0;
		}
		return result;
	}
	
	public ImageView getShard(int i){
		return this.myShards.get(i);
	}
}

