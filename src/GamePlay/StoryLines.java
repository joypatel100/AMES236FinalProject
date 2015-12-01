package GamePlay;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Utility.UIUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StoryLines {

	private Map<Integer,Doors> myMap;
	private int myCurrentLevel;
	private List<ImageView> myShards;
	private List<ImageView> myCollected;
	private int myShardIndex;
	private Map<Image,ImageView> myMirrors;
	
	public StoryLines(Stage stage){
		initBasic();
		String pre = "Comic/Tier1/Christine-comic-first-tier/Panel ";
		String suf = ".jpg";
		ImageView endPage = new ImageView();
		this.myMap.put(1, new Doors());
		Story story = new Story();
		for(int i = 1; i <= 10; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(1).addStory(story);
		pre = "Comic/Tier1/Jennifer-comic-first-tier/Panel ";
		story = new Story();
		for(int i = 1; i <= 19; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(1).addStory(story);

		this.myMap.put(2, new Doors());
		pre = "Comic/Tier2/Amy-_GLB, YK_-Comic-Pages/BookScanCenter_";
		suf = ".png";
		story = new Story();
		for(int i = 1; i <= 3; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(2).addStory(story);
		pre = "Comic/Tier2/Jennifer-comic-second-tier/Jennifer-panel";
		suf = ".png";
		story = new Story();
		for(int i = 1; i <=9 ; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre+"0",i,suf),0,0,stage));
		}
		for(int i = 10; i < 18; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(2).addStory(story);
		pre = "Comic/Tier2/Yuxuan-comic-pages/BookScanCenter_";
		suf = ".png";
		story = new Story();
		for(int i = 1; i <= 3; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(2).addStory(story);

		this.myMap.put(3, new Doors());
		pre = "Comic/Tier3/Amy-comic-pg/Amy-pg";
		suf = ".png";
		story = new Story();
		for(int i = 1; i <= 8; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(3).addStory(story);
		pre = "Comic/Tier3/Sam-comic-pages/Sam-pg";
		suf = ".png";
		story = new Story();
		for(int i = 1; i <= 9; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre+"0",i,suf),0,0,stage));
		}
		for(int i = 10; i <= 11; i++){
			story.getPages().add(UIUtil.initImageView(combine(pre,i,suf),0,0,stage));
		}
		story.getPages().add(endPage);
		this.myMap.get(3).addStory(story);


	}

	public StoryLines(File path, Stage stage){
		initBasic();
		int mapI = 1;
		try(Scanner scanner = new Scanner(path)){
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				line = line.trim();
				System.out.println(line);
				if(line.matches("\\d+")){
					mapI = Integer.parseInt(line);
					myMap.put(mapI, new Doors());
					//System.out.println(mapI);
				}
				else if(!line.endsWith("DS_Store") && !line.equals("")){
					File dir = new File(line);
					Story story = new Story();
					System.out.print(line);
					Map<Integer,ImageView> map = new TreeMap<>();
					//System.out.println(line.substring(12));
					for(File file: dir.listFiles()){
						if(file.getAbsolutePath().endsWith("DS_Store")){
							continue;
						}
						ImageView iv = UIUtil.initImageView(UIUtil.getImage(file), 0, 0);
						iv.fitWidthProperty().bind(stage.widthProperty());
						iv.fitHeightProperty().bind(stage.heightProperty());
						map.put(getNum(file.getName()),iv);
						//story.getPages().add(iv);
						//System.out.println("here");
					}
					for(Integer key: map.keySet()){
						story.getPages().add(map.get(key));
					}
					myMap.get(mapI).addStory(story);
				}
			}
			scanner.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<Image,ImageView> getMirrors(){
		return this.myMirrors;
	}
	
	private void initBasic(){
		myShards = new ArrayList<>();
		myShards.add(UIUtil.initImageView("s_piece1_orange.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece1_red.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_blue.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_green.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece2_teal.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece3_pink.png", 0, 0));
		myShards.add(UIUtil.initImageView("s_piece3_purple.png", 0, 0));
		myMirrors = new HashMap<>();
		myMirrors.put(myShards.get(0).getImage(), UIUtil.initImageView("piece1_orange.png", 0, 0));
		myMirrors.put(myShards.get(1).getImage(), UIUtil.initImageView("piece1_red.png", 0, 0));
		myMirrors.put(myShards.get(2).getImage(), UIUtil.initImageView("piece2_blue.png", 0, 0));
		myMirrors.put(myShards.get(3).getImage(), UIUtil.initImageView("piece2_green.png", 0, 0));
		myMirrors.put(myShards.get(4).getImage(), UIUtil.initImageView("piece2_teal.png", 0, 0));
		myMirrors.put(myShards.get(5).getImage(), UIUtil.initImageView("piece3_pink.png", 0, 0));
		myMirrors.put(myShards.get(6).getImage(), UIUtil.initImageView("piece3_purple.png", 0, 0));
		
		this.myCurrentLevel = 1;
		this.myMap = new HashMap<>();
		this.myShardIndex = 0;
		this.myCollected = new ArrayList<>();
	}

	public List<ImageView> getCollected(){
		return this.myCollected;
	}
	
	private String combine(String pre, int mid, String suf){
		return pre+mid+suf;
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

