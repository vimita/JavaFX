/**
 * Called when user click on overview option. it gives a short description of dataset with their diffrener 13 fetures. and open a hyperlink to link the actual dataset on kaggle.com.
 *  I retrived this dataset from : - https://www.kaggle.com/lava18/google-play-store-apps#googleplaystore.csv.
 *  
 */

package google_play_sore.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import google_play_sore.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class Overview_Controller {
	// Reference to the main application
	private Main mainApp;
	@FXML
	private Hyperlink hlink_dataset;
	String open_link = "https://www.kaggle.com/lava18/google-play-store-apps#googleplaystore.csv";

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleDataset() {
		mainApp.showDataSet();
	}

	public void open_hyperlink(String link)
	{
		
		try {
			Desktop.getDesktop().browse(
					new URL(link).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void OpenLink() {
		open_hyperlink(open_link);
		
	}
}
