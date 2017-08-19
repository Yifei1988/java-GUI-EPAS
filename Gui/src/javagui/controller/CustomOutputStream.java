package javagui.controller;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextField;

public class CustomOutputStream extends OutputStream{
	private TextField textArea;
	
	public CustomOutputStream(TextField textArea){
		this.textArea = textArea;
	}

	@Override
	public void write(int b) throws IOException {
		// redirects data to the text area
		textArea.appendText(String.valueOf((char)b));
	}
}
