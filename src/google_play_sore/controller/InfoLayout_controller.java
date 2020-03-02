/**
 * Represents the information of dataset.and call when user wants to view dataset in table format.
 */

package google_play_sore.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import google_play_sore.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class InfoLayout_controller {

	@FXML
	private Button btn_view;
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

	@FXML
	private void handledataset() {
		mainApp.showDataSet();
	}
	
	

}
