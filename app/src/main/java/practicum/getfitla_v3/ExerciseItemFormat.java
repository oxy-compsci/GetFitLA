package practicum.getfitla_v3;

import android.os.Parcel;
import android.os.Parcelable;

//Formats Information to be easily called
public class ExerciseItemFormat implements Parcelable {

    private int id;
    private String name;
    private String shortdesc;
    private String isboolean; //change this name
    private String equipment;
    private String instructions;
    private int image;

    public ExerciseItemFormat(int id, String name,
                              String shortdesc, String isboolean, String equipment, String instructions, int image) {
        this.id = id;
        this.name = name;
        this.shortdesc = shortdesc;
        this.isboolean = isboolean;
        this.equipment = equipment;
        this.instructions = instructions;
        this.image = image;

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
    public String getIsboolean() {
        return isboolean;
    }
    public String getEquipment() {
        return equipment;
    }
    public String getInstructions() {
        return instructions;
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
        dest.writeString(this.name);
        dest.writeString(this.shortdesc);
        dest.writeString(this.isboolean);
        dest.writeString(this.equipment);
        dest.writeString(this.instructions);
        dest.writeInt(this.image);
    }

    protected ExerciseItemFormat(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.shortdesc = in.readString();
        this.isboolean = in.readString();
        this.equipment = in.readString();
        this.instructions = in.readString();
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
