/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author vimi
 */

package google_play_sore.controller;

import java.io.File;

import google_play_sore.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;


public class WelcomePageController {

	// Reference to the main application
	private Main mainApp;

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("GoogleApp_Analysis");
		alert.setHeaderText("About");
		alert.setContentText(" JAVAFX assignment which include the anaysis of Google play store app and perform different operation with loading csv file data into table. Created by : Vimita Vaidya");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/**
	 * Opens the Dataset OverView.
	 */
	@FXML
	private void handleOverview() {
		mainApp.showInfo();
	}
}