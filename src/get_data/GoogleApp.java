/***
 * GoogleApp.java class reperesents a data model. which store the values and train data.
 */

package get_data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class GoogleApp {
	private SimpleStringProperty App, Category, Size, Installs, Type, Content_Rating, Generes, Last_Updated,
			Current_version, Android_Version, Price, Rating, Reviews;
	
	/**
	 * This is the Parameterized constructor which includes the columns of dataset
	 * in arguments.
	 *
	 */
	public GoogleApp(String app, String category, String rating, String reviews, String size, String installs,
			String type, String price, String content_Rating, String generes, String last_Updated,
			String current_version, String android_Version) {
		super();
		this.App = new SimpleStringProperty(app);
		this.Category = new SimpleStringProperty(category);
		this.Rating = new SimpleStringProperty(rating);
		this.Reviews = new SimpleStringProperty(reviews);
		this.Size = new SimpleStringProperty(size);
		this.Installs = new SimpleStringProperty(installs);
		this.Type = new SimpleStringProperty(type);
		this.Price = new SimpleStringProperty(price);
		this.Content_Rating = new SimpleStringProperty(content_Rating);
		this.Generes = new SimpleStringProperty(generes);
		this.Last_Updated = new SimpleStringProperty(last_Updated);
		this.Current_version = new SimpleStringProperty(current_version);
		this.Android_Version = new SimpleStringProperty(android_Version);

	}

	
	public GoogleApp() {
		// TODO Auto-generated constructor stub
	}

	public GoogleApp(String installs, String rating, String reviews) {
		this.Installs = new SimpleStringProperty(installs);
		this.Rating = new SimpleStringProperty(rating);
		this.Reviews = new SimpleStringProperty(reviews);

	}

	public GoogleApp(String app) {
		// TODO Auto-generated constructor stub
		this.App = new SimpleStringProperty(app);

	}

	public String getApp() {
		return App.get();
	}

	public String getCategory() {
		return Category.get();
	}

	public String getSize() {
		return Size.get();
	}

	public String getInstalls() {
		return Installs.get();
	}

	public String getType() {
		return Type.get();
	}

	public String getContent_Rating() {
		return Content_Rating.get();
	}

	public String getGeneres() {
		return Generes.get();
	}

	public String getLast_Updated() {
		return Last_Updated.get();
	}

	public String getCurrent_version() {
		return Current_version.get();
	}

	public String getAndroid_Version() {
		return Android_Version.get();
	}

	public String getPrice() {
		return Price.get();
	}

	public String getRating() {
		return Rating.get();
	}

	public String getReviews() {
		return Reviews.get();
	}

	public void setApp(SimpleStringProperty app) {
		App = app;
	}

	public void setCategory(SimpleStringProperty category) {
		Category = category;
	}

	public void setSize(SimpleStringProperty size) {
		Size = size;
	}

	public void setInstalls(SimpleStringProperty installs) {
		Installs = installs;
	}

	public void setType(SimpleStringProperty type) {
		Type = type;
	}

	public void setContent_Rating(SimpleStringProperty content_Rating) {
		Content_Rating = content_Rating;
	}

	public void setGeneres(SimpleStringProperty generes) {
		Generes = generes;
	}

	public void setLast_Updated(SimpleStringProperty last_Updated) {
		Last_Updated = last_Updated;
	}

	public void setCurrent_version(SimpleStringProperty current_version) {
		Current_version = current_version;
	}

	public void setAndroid_Version(SimpleStringProperty android_Version) {
		Android_Version = android_Version;
	}

	public void setPrice(SimpleStringProperty price) {
		Price = price;
	}

	public void setRating(SimpleStringProperty rating) {
		Rating = rating;
	}

	public void setReviews(SimpleStringProperty reviews) {
		Reviews = reviews;
	}

	/*public static int findMax(ArrayList<GoogleApp> list){
        return Collections.max();
    }
   
    public static int findMin(ArrayList<Integer> numbers){
        return Collections.min(numbers);
    }*/
	

}
