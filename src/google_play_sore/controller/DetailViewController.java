/**
 * This class reperesnts the controller of Detailview and handle operation such as, search ,filter, sort and delete.
 */

package google_play_sore.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import get_data.GoogleApp;
import google_play_sore.Main;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DetailViewController {

	private ObservableList<GoogleApp> dataList = FXCollections.observableArrayList();
	ArrayList<GoogleApp> list = new ArrayList<GoogleApp>();

	@FXML
	private Label lbl_total_app_install;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<GoogleApp, String> app_name_id;

	@FXML
	private TableView<GoogleApp> googleAppTable;

	@FXML
	private Label lbl_and_version;

	@FXML
	private Label lbl_c_rate;

	@FXML
	private Label lbl_c_version;

	@FXML
	private Label lbl_category;

	@FXML
	private Label lbl_generes;

	@FXML
	private Label lbl_install;

	@FXML
	private Label lbl_last_update;

	@FXML
	private Label lbl_rate;
	@FXML
	private Label lbl_review;

	@FXML
	private Label lbl_size;

	@FXML
	private Label lbl_type;
	@FXML
	private Label lbl_price;
	@FXML
	private TextField txt_search;

	@FXML
	private Label lbl_max_installs;

	@FXML
	private Label lbl_max_rate;

	@FXML
	private Label lbl_min_review;

	@FXML
	private ComboBox<String> cmb_filter;
	// Reference to the main application.
	private Main mainApp;
	private double rate;
	private double min;
	private double max;
	private double average_rate;

	@FXML
	private void initialize() {
		readCSV();
		app_name_id.setCellValueFactory(new PropertyValueFactory<>("App"));
		googleAppTable.setItems(dataList);

		// Clear app details.
		showDetails(null);

		// Listen for selection changes and show the app details when changed.
		googleAppTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDetails(newValue));

		/**
		 * Max Installs
		 */
		//handleMathOP();
		// System.err.println("Rate " + rate);
		// System.err.println(GoogleApp.findMax(list));
	}

	public void readCSV() {

		// String CsvFile = "data/googleplaystore.csv";
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
				dataList.add(record);
				list.add(record);

			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		googleAppTable.setItems(dataList);
	}

	/**
	 * Fills all text fields to show details about the app. If the specified app
	 * name is null, all text fields are cleared.
	 * 
	 * @param gdetail the app or null
	 */
	private void showDetails(GoogleApp gdetail) {
		if (gdetail != null) {
			// Fill the labels with info from the person object.

			lbl_category.setText("Category : " + gdetail.getCategory());
			lbl_rate.setText("Rating : " + gdetail.getRating());
			lbl_review.setText("Reviews : " + gdetail.getReviews());
			lbl_size.setText("Size : " + gdetail.getSize());
			lbl_install.setText("Installs : " + gdetail.getInstalls());
			lbl_type.setText("Type : " + gdetail.getType());
			lbl_price.setText("Price : " + gdetail.getPrice());
			lbl_c_rate.setText("Content Rating : " + gdetail.getContent_Rating());
			lbl_generes.setText("Generes : " + gdetail.getGeneres());
			lbl_last_update.setText("Last Updtaed : " + gdetail.getLast_Updated());
			lbl_c_version.setText("Content Version : " + gdetail.getCurrent_version());
			lbl_and_version.setText("Android Version : " + gdetail.getAndroid_Version());
		//	rate = Double.parseDouble(gdetail.getRating());
		//	System.err.println("Inside " + rate);
		//	rate += rate;

		} else {
			// gdetail is null, remove all the text.
			// lbl_category.setText("");
		}
		// average_rate = rate / 5;
		// System.err.println("Average Rate : " + average_rate);
		// lbl_max_rate.setText("Average rate of application : " + average_rate);
		
	}

	/**
	 * Returns the data as an observable list of app.
	 * 
	 * @return
	 */
	public ObservableList<GoogleApp> getPersonData() {
		return dataList;
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteRecord() {
		int selectedIndex = googleAppTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			googleAppTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No application name is Selected");
			alert.setContentText("Please select a app name in the table.");

			alert.showAndWait();
		}
	}

	/***
	 * Handle Search operation
	 */
	@FXML
	private void handleSearch() {
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<GoogleApp> filteredData = new FilteredList<>(dataList, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(googledata -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first appname and category name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (googledata.getApp().toLowerCase().startsWith(lowerCaseFilter)) {
					return true; // Filter matches app name.
				} else if (googledata.getCategory().toLowerCase().startsWith(lowerCaseFilter)) {
					return true; // Filter matches category name.
				} else if (googledata.getInstalls().toLowerCase().startsWith(lowerCaseFilter)) {
					return true;
				}

				return false; // Does not match.
			});
		});

		// 3. Wrap the FilteredList in a SortedList.
		SortedList<GoogleApp> sortedData = new SortedList<>(filteredData);

		// 4. Bind the SortedList comparator to the TableView comparator.
		// Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(googleAppTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		googleAppTable.setItems(sortedData);

	}

	/***
	 * Handle Filter operation
	 */
	@FXML
	private void handleFilter() {
		Comparator<GoogleApp> googleComparator = Comparator.comparing(GoogleApp::getInstalls);
		if (cmb_filter.getValue().equalsIgnoreCase("DESC")) {
			dataList.sort(googleComparator.reversed());
		} else if (cmb_filter.getValue().equalsIgnoreCase("AESC")) {
			dataList.sort(googleComparator);
			// dataList = googleComparator.OrderBy(x=>x.ThePropertyYourWant).ToList();
		}

	}
	
	
}
