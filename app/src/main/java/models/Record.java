package models;

import android.graphics.Bitmap;
import java.io.Serializable;

/**
 * Created by Matic-ProBook on 20. 09. 2017.
 */

public class Record extends BaseModel implements Serializable {
    private User author;
    private String imagePath;
    private Bitmap image;
    private double gpsLatitude;
    private double gpsLongitude;
    private String description;
    private boolean done;

	/* public methods */

	public Record (User author, String imagePath, double gpsLatitude, double gpsLongitude, String description) {
        this.author = author;
        this.imagePath = imagePath;
        this.gpsLatitude = gpsLatitude;
        this.gpsLongitude = gpsLongitude;
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public double getGpsLatitude() {
        return gpsLatitude;
    }
    public void setGpsLatitude(double gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }
    public double getGpsLongitude() {
        return gpsLongitude;
    }
    public void setGpsLongitude(double gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean getDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
}