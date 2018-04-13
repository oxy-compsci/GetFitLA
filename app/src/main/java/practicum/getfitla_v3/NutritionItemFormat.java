package practicum.getfitla_v3;

//Class that formats the data into variables that are more easily called
public class NutritionItemFormat {
    private int id;
    private String title;
    private String preptime;
    private String servingsize;
    private String calories;
    private String equipment;
    private String ingredients;
    private String procedure;
    private String notes;
    private int image;

    public NutritionItemFormat(int id, String title, String preptime, String servingsize, String calories, String equipment, String ingredients, String procedure, String notes, int image) {
        this.id = id;
        this.title = title;
        this.preptime = preptime;
        this.servingsize = servingsize;
        this.calories = calories;
        this.equipment = equipment;
        this.ingredients = ingredients;
        this.procedure = procedure;
        this.notes = notes;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrepTime() {
        return preptime;
    }

    public String getServingSize() {
        return servingsize;
    }

    public String getCalories() {
        return calories;
    }

    public String getEquipment(){
        return equipment;
    }

    public String getIngredients(){
        return ingredients;
    }

    public String getProcedure(){
        return procedure;
    }

    public String getNotes(){
        return notes;
    }

    public int getImage() {
        return image;
    }
}
