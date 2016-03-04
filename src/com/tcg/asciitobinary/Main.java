package com.tcg.asciitobinary;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage window;
	private Scene scene;
	
	private VBox layout; 
	
	private Label title;
	private TextArea input;
	private Button go;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		window = arg0;
		window.setTitle("Ascii to Binary");
		
		title = new Label("Enter text to get binary");
		
		input = new TextArea();
		input.setWrapText(true);
		
		go = new Button("Go");
		go.setOnAction(e -> {
			new Result(stringToBinary(input.getText()));
		});
		
		layout = new VBox(10);
		
		layout.setPadding(new Insets(5, 10, 20, 10));
		
		layout.getChildren().addAll(title, input, go);
		
		scene = new Scene(layout);
		window.setScene(scene);
		window.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		window.show();
		
	}

	public static String charToBinaryString(char c) {
		return String.format("%08d", Integer.parseInt(Integer.toBinaryString((int) c)));
	}
	
	public static String stringToBinary(String s) {
		String res = "";
		for(int i = 0; i < s.length(); i++) {
			res += charToBinaryString(s.charAt(i));
			if((i + 1) % 2 == 0) {
				res += "\n";
			} else if(i < s.length() - 1) {
				res += " ";
			}
		}
		return res;
	}

}
