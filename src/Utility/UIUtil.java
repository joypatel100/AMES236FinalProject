package Utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UIUtil {

	public static Image getImage(String imageName){
		try{
			return new Image(UIUtil.class.getClassLoader().getResourceAsStream(imageName));
		}
		catch(Exception e){
			return new Image(imageName);
		}
	}
	
	public static Image getImage(File file){
		try {
			return new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setPosition(Node node, double x, double y){
		node.setTranslateX(x);
		node.setTranslateY(y);
	}

	// Initialize ImageView
	public static ImageView initImageView(Image image, double x, double y){
		ImageView result = new ImageView(image);
		setPosition(result,x,y);
		return result;
	}
	
	// Initialize Buttons
	public static Button initButton(String name, Image image, double x, double y){
		Button result = (image!=null) ? new Button(name,new ImageView(image)) : new Button(name);
		setPosition(result,x,y);
		return result;
	}
	
	public static Button initButton(String name, double x, double y){
		return initButton(name,null,x,y);
	}
	
	public static Button initButton(Image image, double x, double y){
		return initButton("", image, x, y);
	}
	
	// Initialize TextField
	public static TextField initTextField(String text, double x, double y){
		TextField result = new TextField(text);
		setPosition(result,x,y);
		return result;
	}
	
	// Initialize Text
	public static Text initText(String text, double x, double y){
		Text result = new Text(text);
		setPosition(result,x,y);
		return result;
	}

	public static List<ImageView> initIV(String[] paths){
		List<ImageView> result = new ArrayList<>();
		for(String path: paths){
			result.add(UIUtil.initImageView(UIUtil.getImage(path), 0, 0));
		}
		return result;
	}
	
}
