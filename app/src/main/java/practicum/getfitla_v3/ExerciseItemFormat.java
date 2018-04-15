package practicum.getfitla_v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

//Formats Information to be easily called
public class ExerciseItemFormat implements Parcelable {
    private int id;
    private String title;
    private String shortdesc;
    private String rating;
    private String price;
    private int image;

    public ExerciseItemFormat(int id, String title, String shortdesc, String rating, String price, int image) {
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

    public int getImage() {
        return image;
    }

    //begin parcel shit

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.shortdesc);
        dest.writeString(this.rating);
        dest.writeString(this.price);
        dest.writeInt(this.image);
    }

    protected ExerciseItemFormat(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.shortdesc = in.readString();
        this.rating = in.readString();
        this.price = in.readString();
        this.image = in.readInt();
    }

    public static final Parcelable.Creator<ExerciseItemFormat> CREATOR = new Parcelable.Creator<ExerciseItemFormat>() {
        @Override
        public ExerciseItemFormat createFromParcel(Parcel source) {
            return new ExerciseItemFormat(source);
        }

        @Override
        public ExerciseItemFormat[] newArray(int size) {
            return new ExerciseItemFormat[size];
        }
    };
}
