package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public static void display(String error, String error_message){
		Stage window = new Stage();
		window.setTitle(error);
		window.initModality(Modality.APPLICATION_MODAL);
		
		Label message = new Label(error_message);
		
		Button exit = new Button("Okay");
		exit.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(message, exit);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 500, 200);
		
		window.setScene(scene1);
		window.show();
	}
}
