package intf.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Window;

public class AlertDisplay {

	public static Optional<ButtonType> confirmPreview(Window parent){

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Website Preview");
		alert.setHeaderText(null);
		alert.setContentText("Do you want to display a preview of yout post on the website ?");

		ButtonType buttonTypeSkip = new ButtonType("Skip", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(ButtonType.OK, buttonTypeSkip);

		alert.initModality(Modality.WINDOW_MODAL);
		alert.initOwner(parent);

		return alert.showAndWait();
	}

	public static Optional<ButtonType> previewDisplay(Window parent){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Preview Display");
		alert.setHeaderText(null);
		alert.setContentText("Displaying preview...");

		ButtonType buttonTypeFinish = new ButtonType("Finish");

		alert.getButtonTypes().setAll(buttonTypeFinish);

		alert.initModality(Modality.WINDOW_MODAL);
		alert.initOwner(parent);

		return alert.showAndWait();
	}

	public static Optional<ButtonType> confirmGitPost(Window parent){

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Git Post confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Do you confirm that you want to send this post to the remote repository ?");

		alert.initModality(Modality.WINDOW_MODAL);
		alert.initOwner(parent);

		return alert.showAndWait();
	}
	
	public static Optional<ButtonType> postSentToGit(Window parent){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Post Sent To Git");
		alert.setHeaderText(null);
		alert.setContentText("Your post was sent to the remote repository.");

		alert.initModality(Modality.WINDOW_MODAL);
		alert.initOwner(parent);

		return alert.showAndWait();
	}
	
	public static Optional<String> enterImageUrl(Window parent) {
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Enter Image URL");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter the image's URL :");

		return dialog.showAndWait();
	}

}
