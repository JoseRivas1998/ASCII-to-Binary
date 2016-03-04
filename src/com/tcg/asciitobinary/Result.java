package com.tcg.asciitobinary;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Result {

	private TextArea textArea;
	private Text textHolder = new Text();
	
	public Result(String res) {
		
		Stage window = new Stage();
		window.setTitle("Result");
		
		textArea = new TextArea();
		textArea.setText(res);
		textHolder.textProperty().bind(textArea.textProperty());
		float boundsH = (float)textHolder.getLayoutBounds().getHeight() * 1.25f;
		textArea.setPrefHeight(clamp(boundsH, 0, (float)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100));
		
		textArea.setWrapText(true);
		
		StackPane layout = new StackPane();
		layout.getChildren().addAll(textArea);
		layout.setPrefSize(textHolder.getLayoutBounds().getWidth() * 1.5, textArea.getPrefHeight());
		
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.show();
	}
	

	public static float clamp(float n, float min, float max) {
		float r = n;
		if(r < min) {
			r = min;
		}
		if(r > max) {
			r = max;
		}
		return r;
	}
	
}
