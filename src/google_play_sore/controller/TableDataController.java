/**
 * View Data on Table Format.Try to implement Pagination.
 */

package google_play_sore.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import get_data.GoogleApp;
import google_play_sore.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TableDataController {

	private final ObservableList<GoogleApp> dataList = FXCollections.observableArrayList();
	private final static int dataSize = 200;
	private final static int rowsPerPage = 20;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableColumn<GoogleApp, String> appname;

	@FXML
	private TableColumn<GoogleApp, String> c_rating;

	@FXML
	private TableColumn<GoogleApp, String> c_ver;
	@FXML
	private TableColumn<GoogleApp, String> and_ver;

	@FXML
	private TableColumn<GoogleApp, String> category;

	@FXML
	private TableColumn<GoogleApp, String> generes;

	@FXML
	private TableColumn<GoogleApp, String> installs;

	@FXML
	private TableColumn<GoogleApp, String> l_updated;

	@FXML
	private TableColumn<GoogleApp, String> price;

	@FXML
	private TableColumn<GoogleApp, String> rating;

	@FXML
	private TableColumn<GoogleApp, String> reviews;

	@FXML
	private TableColumn<GoogleApp, String> size;

	@FXML
	private TableView<GoogleApp> tableview;

	@FXML
	private TableColumn<GoogleApp, String> type;

	@FXML
	private Pagination pagination;
	private Main mainApp;
	int count = 0;
	int itemPerPage = 5;

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

	@FXML
	void initialize() {

		readCSV();
		appname.setCellValueFactory(new PropertyValueFactory<>("App"));
		appname.setPrefWidth(200);
		category.setCellValueFactory(new PropertyValueFactory<>("Category"));
		c_ver.setCellValueFactory(new PropertyValueFactory<>("Current_version"));
		and_ver.setCellValueFactory(new PropertyValueFactory<>("Android_Version"));
		rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
		size.setCellValueFactory(new PropertyValueFactory<>("Size"));
		reviews.setCellValueFactory(new PropertyValueFactory<>("Reviews"));
		l_updated.setCellValueFactory(new PropertyValueFactory<>("Last_Updated"));
		installs.setCellValueFactory(new PropertyValueFactory<>("Installs"));
		type.setCellValueFactory(new PropertyValueFactory<>("Type"));
		price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		c_rating.setCellValueFactory(new PropertyValueFactory<>("Content_Rating"));
		generes.setCellValueFactory(new PropertyValueFactory<>("Generes"));
		tableview.setItems(dataList);
		pagination.setPageFactory(this::createPage);

		tableview.setEditable(true);
		appname.setCellFactory(TextFieldTableCell.forTableColumn());

		tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableview.getSelectionModel().setCellSelectionEnabled(true);
	}

	// method to create page inside pagination view
	private Node createPage(int pageIndex) {
		int fromIndex = pageIndex * rowsPerPage;
		int toIndex = Math.min(fromIndex + rowsPerPage, dataList.size());
		tableview.setItems(FXCollections.observableArrayList(dataList.subList(fromIndex, toIndex)));
		return new FlowPane(tableview);
	}

	public void readCSV() {

		String CsvFile = "data/googleplaystore_dataset.csv";
		String FieldDelimiter = ",";

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(CsvFile));

			String line;
			while ((line = br.readLine()) != null) {
				String currentline = line.replace("{2}M", "");
				currentline = line.replace("{1.1}M", "");

				currentline = line.replace("+", "");
				currentline = line.replace("\"", "");
				currentline = line.replace("{3}+", "");
				currentline = line.replace("{3}+\"", "");
				String[] fields = currentline.split(FieldDelimiter, -1);

				GoogleApp record = new GoogleApp(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
						fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12]);
				dataList.add(record);
				// System.out.println(fields[1]);

			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DetailViewController.class.getName()).log(Level.SEVERE, null, ex);
		}
		// googleAppTable.getColumns().addAll(app_name_id);

	}

	@FXML
	public void clickedColumn(MouseEvent event) {
		
		TablePosition tablePosition = tableview.getSelectionModel().getSelectedCells().get(0);
		int row = tablePosition.getRow();
		GoogleApp item = tableview.getItems().get(row);
		TableColumn tableColumn = tablePosition.getTableColumn();
		String data = (String) tableColumn.getCellObservableValue(item).getValue();
		System.out.println(data);
		
	}

}
