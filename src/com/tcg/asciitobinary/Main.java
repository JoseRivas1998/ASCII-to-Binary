package com.tcg.asciitobinary;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	enum From {
		ASCII, Binary
	}
	
	private Stage window;
	private Scene scene;
	
	private VBox layout; 
	private HBox bottomRow, fromBox;
	
	private Label title, fromLabel;
	private TextArea input;
	private Button go;
	
	private ChoiceBox<From> fromChoice;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		window = arg0;
		window.setTitle("ASCII/Binary Converter");
		
		title = new Label("Enter Text to Convert");
		
		input = new TextArea();
		input.setWrapText(true);
		
		go = new Button("Go");
		go.setOnAction(e -> {
			String s = "";
			switch(fromChoice.getValue()) {
			case ASCII:
				s = stringToBinary(input.getText());
				break;
			case Binary:
				s = binaryToAscii(input.getText());
				break;
			default: 
				s = "There was an error, please try again.";
			}
			new Result(s);
		});
		
		fromLabel = new Label("Convert From:");
		
		fromChoice = new ChoiceBox<>();
		fromChoice.getItems().addAll(From.values());
		fromChoice.setValue(From.ASCII);
		
		fromBox = new HBox(10);
		fromBox.getChildren().addAll(fromLabel, fromChoice);
		
		bottomRow = new HBox(10);
		bottomRow.getChildren().addAll(fromBox, go);
		bottomRow.setAlignment(Pos.CENTER);
		
		layout = new VBox(10);
		
		layout.setPadding(new Insets(5, 10, 20, 10));
		
		layout.getChildren().addAll(title, input, bottomRow);
		
		layout.setAlignment(Pos.CENTER);
		
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
	
	public static int binaryToInt(String binary) throws NumberFormatException {
		for(char c : binary.toCharArray()) {
			if(c != '0' && c != '1') {
				throw new NumberFormatException("The string given is not binary.");
			}
		}
		
		int res = 0;
		
		for(int i = 0; i < binary.length(); i++) {
			int pow = binary.length() - 1 - i;
			if(binary.charAt(i) == '1') {
				res += Math.pow(2, pow);
			}
		}
		
		return res;
	}
	
	public static String[] binaryToBytes(String binary) {
		String temp = binary.replace(" ", "").replace("\n", "");
		int arrLength = (int) Math.ceil(temp.length() / 8f);
		String[] res = new String[arrLength];
		int j = 0;
		for(int i = 0; i < temp.length(); i += 8) {
			String s = "";
			for(int k = 0; k < 8; k++) {
				if(i + k < temp.length()) {
					s += temp.charAt(i + k);
				}
			}
			res[j] = s;
			j++;
		}
		return res;
	}
	
	public static int[] bytesToInts(String[] bytes) {
		int[] res = new int[bytes.length];
		
		for(int i = 0; i < res.length; i++) {
			res[i] = binaryToInt(bytes[i]);
		}
		
		return res;
	}
	
	public static String binaryToAscii(String binary) {
		String[] bytes = binaryToBytes(binary);
		int[] ints = bytesToInts(bytes);
		String res = "";
		for(int i : ints) {
			res += (char) i;
		}
		return res;
	}

}
