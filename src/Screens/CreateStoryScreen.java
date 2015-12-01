package Screens;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utility.UIUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CreateStoryScreen extends AScreen{

	private HBox myHBox;
	private ComboBox<String> myLevels;
	private ListView<String> myListView;
	private Map<String,String> myMap;
	private Text myText;
	private Button myDone;

	public CreateStoryScreen(StoryGame game) {
		super(game);
		// TODO Auto-generated constructor stub
		this.myHBox = new HBox();
		this.myHBox.setTranslateX(StoryGame.WIDTH/10);
		this.myHBox.setTranslateY(StoryGame.HEIGHT/10);
		this.myStoryGame.addToRoot(myHBox);
		initNumLevelsComboBox();
		initDone();
		initBackToMenu();
	}
	
	private void initDone(){
		this.myDone = UIUtil.initButton("Done", StoryGame.WIDTH*.8, StoryGame.HEIGHT*.8);
		this.myDone.setOnAction(e -> handleDone());
		this.myStoryGame.addToRoot(myDone);
	}
	
	private void initBackToMenu(){
		Button back = UIUtil.initButton("Main Menu", StoryGame.WIDTH*.8, StoryGame.HEIGHT*.9);
		back.setOnAction(e -> {this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));});
		this.myStoryGame.addToRoot(back);
	}
	
	private void handleDone(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose where to save.");
		chooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		File file = chooser.showSaveDialog(this.myStoryGame.getStage());
		//String location = file.getPath();
		StringBuilder sb = new StringBuilder();
		List<String> keys = new ArrayList<>(this.myMap.keySet());
		Collections.sort(keys);
		keys.stream().forEach(key -> {
			sb.append(this.myMap.get(key));
		});
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.myStoryGame.setScreen(new MainMenuScreen(this.myStoryGame));
	}

	private void initNumLevelsComboBox(){
		if(this.myHBox.getChildren().contains(myLevels)){
			this.myHBox.getChildren().remove(myLevels);
		}
		List<String> nums = new ArrayList<>();
		for(int i = 1; i  <= 10; i++){
			nums.add(Integer.toString(i));
		}
		ObservableList<String> lvls = FXCollections.observableArrayList(nums);
		myLevels = new ComboBox<>(lvls);
		Text title = new Text("Number of \nLevels");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(title,myLevels);
		myLevels.setOnAction(e -> initListView());
		this.myHBox.getChildren().add(vbox);
	}

	private void initListView(){
		myMap = new HashMap<>();
		if(this.myHBox.getChildren().contains(myListView)){
			this.myHBox.getChildren().remove(myListView);
		}
		String num = myLevels.getSelectionModel().getSelectedItem();
		List<String> nums = new ArrayList<>();
		for(int i = 1; i  <= Integer.parseInt(num); i++){
			nums.add(Integer.toString(i));
			myMap.put(Integer.toString(i), "");
		}
		ObservableList<String> level = FXCollections.observableArrayList(nums);
		myListView = new ListView<>(level);
		myListView.setOnMouseClicked(e -> handleListViewClick(e));
		this.myHBox.getChildren().add(myListView);
	}

	private void changeText(String text){
		if(this.myHBox.getChildren().contains(myText)){
			this.myHBox.getChildren().remove(myText);
		}
		myText = new Text(text);
		myText.setTranslateX(10);
		this.myHBox.getChildren().add(myText);
	}

	private void handleListViewClick(MouseEvent e){
		String level = (String) myListView.getSelectionModel().getSelectedItem();
		if (e.getClickCount() == 2){
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Choose files.");
			File dir = chooser.showDialog(this.myStoryGame.getStage());
			if(dir==null){
				return;
			}
			this.myMap.put(level, "\n" + level);
			for(File file: dir.listFiles()){
				this.myMap.put(level, this.myMap.get(level) + "\n" + file.getAbsolutePath());
			}
		}
		String text = myMap.get(level);
		if(text.indexOf("/")!=-1){
			String[] spl = text.split("\n");
			text = "";
			for(String s: spl){
				String[] sp = s.split("/");
				text += sp[sp.length-1] + "\n";
			}
		}
		changeText(text);
	}

	@Override
	public void update(double elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyReleased(KeyCode code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyPressed(KeyCode code) {
		// TODO Auto-generated method stub

	}

}
