package practicum.getfitla_v3;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Work on 3/14/18.
 */

public class Exercise_Detail {
    private int id;
    private String title;
    private String shortdesc;
    private String rating;
    private String price;
    private Integer image;

    public Exercise_Detail(int id, String title, String shortdesc, String rating, String price, Integer image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }

    public Integer getImage() {
        return image;
    }
}
