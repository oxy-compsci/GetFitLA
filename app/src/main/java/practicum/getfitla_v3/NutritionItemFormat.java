package practicum.getfitla_v3;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.SoftReference;

//Formats Information to be easily called
public class NutritionItemFormat implements Parcelable {
    private int id;
    private String name;
    private String shortdesc;
    private String rating;
    private String price;
    private int image;
    private String prepTime;
    private String servingSize;
    private String calories;
    private String equipment;
    private String directions;
    private String ingredients;

    public NutritionItemFormat(int id, String name,
                               String shortdesc,
                               //String rating,
                              // String price,
                               int image, String preptime, String servingSize, String calories,
                              String equipment, String directions, String ingredients) {
        this.id = id;
        this.name = name;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.prepTime = preptime;
        this.servingSize = servingSize;
        this.calories = calories;
        this.equipment = equipment;
        this.directions = directions;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public String getPrepTime() {return prepTime;}

    public String getServingSize() {return servingSize;}

    public String getCalories() {return calories;}

    public String getEquipment() {return equipment;}

    public String getDirections() {return directions;}

    public String getIngredients() {return ingredients;}

    //begin parcel shit

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.shortdesc);
        dest.writeString(this.rating);
        dest.writeString(this.price);
        dest.writeInt(this.image);
        dest.writeString(this.prepTime);
        dest.writeString(this.servingSize);
        dest.writeString(this.calories);
        dest.writeString(this.equipment);
        dest.writeString(this.directions);
        dest.writeString(this.ingredients);
    }

    protected NutritionItemFormat(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.shortdesc = in.readString();
        this.rating = in.readString();
        this.price = in.readString();
        this.image = in.readInt();
        this.prepTime = in.readString();
        this.servingSize = in.readString();
        this.calories = in.readString();
        this.equipment = in.readString();
        this.directions = in.readString();
        this.ingredients = in.readString();
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
