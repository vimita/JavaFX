/**
 * This class is the Main class which execute the application. and in this file all FXML file is redirect from one to another.
 */

package google_play_sore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import get_data.GoogleApp;
import google_play_sore.controller.DetailViewController;
import google_play_sore.controller.Overview_Controller;
import google_play_sore.controller.TableDataController;
import google_play_sore.controller.WelcomePageController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private FlowPane rootFlowPane;
	/**
	 * The data as an observable list of Apps.
	 */
	private ObservableList<GoogleApp> Data = FXCollections.observableArrayList();
	private AnchorPane rootanchorpane;

	/**
	 * Constructor which read the csv data file.
	 */
	public Main() {
		// Add data
		String CsvFile = "data/googleplaystore_dataset.csv";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(CsvFile));

			String line;
			while ((line = br.readLine()) != null) {
				
				String currentline = line.replace("+", "");
				currentline = line.replace("{1}\"", "");
				
				String[] fields = currentline.split(FieldDelimiter, -1);

				GoogleApp record = new GoogleApp(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
						fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12]);
				Data.add(record);

			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return data
	 */
	public ObservableList<GoogleApp> getPersonData() {
		return Data;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("GoogleApp Analysis");

		// Set the application icon.
		this.primaryStage.getIcons().add(new Image("file:resources/images/logo.jpg"));

		initRootLayout();

		showAppOverview();
	}

	/**
	 * Initializes the root layout and tries to load the last opened welcome page
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ui/FXML_WelcomePage.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			WelcomePageController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.setWidth(1340);
			primaryStage.setHeight(800);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Shows the app overview inside the root layout.
	 */
	public void showAppOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ui/FXML_Detail.fxml"));
			AnchorPane appOverview = (AnchorPane) loader.load();
			//appOverview.setMaxWidth(1340);
			//appOverview.setMaxHeight(800);
			// appOverview.setMaxSize(200, 200);
			// Set person overview into the center of root layout.
			rootLayout.setCenter(appOverview);

			// Give the controller access to the main app.
			DetailViewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to show overview (Give the short information of file. and open
	 * a link which redirect the data set that I used to analyzed ).
	 */
	public void showInfo() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ui/FXML_InfoPage.fxml"));
			rootFlowPane = (FlowPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Overview");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(rootFlowPane);
			dialogStage.setScene(scene);

			// Set the persons into the controller.
			Overview_Controller controller = loader.getController();
			controller.setMainApp(this);

			// Set the dialog icon.
			dialogStage.getIcons().add(new Image("file:resources/images/logo.png"));
			dialogStage.setWidth(1340);
			dialogStage.setHeight(800);
			dialogStage.setResizable(false);

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to show all Data in table format .
	 */
	public void showDataSet() {
		try {
			// Load the fxml file and create a new stage for the popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ui/FXML_TableView.fxml"));
			rootanchorpane = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("DataSet");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(rootanchorpane);
			dialogStage.setScene(scene);
			dialogStage.setWidth(1340);
			dialogStage.setHeight(800);
			dialogStage.setResizable(false);
			// Set the persons into the controller.
			TableDataController controller = loader.getController();
			controller.setMainApp(this);
			// Set the dialog icon.
			dialogStage.getIcons().add(new Image("file:resources/images/logo.png"));

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}