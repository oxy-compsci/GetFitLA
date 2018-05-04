package practicum.getfitla_v3;
import java.util.ArrayList;
public class Filter {
    /*Searches the initial array for items that match the filtervalue. If they do, those items are added
    to the filter array. The filter array is then returned and the arrayList that is used to display items
    in the recyclerview
    */
    public static ArrayList<NutritionItemFormat> filterThis(ArrayList<NutritionItemFormat> InitialArray, String filterValue) {
        ArrayList<NutritionItemFormat> filteredArray = new ArrayList<>();
        for (int i = 0; i < InitialArray.size(); i++) {
            if(InitialArray.get(i).category.equals(filterValue)) {
                filteredArray.add(InitialArray.get(i));
            }
        }
        return filteredArray;
    }
    public static ArrayList<ExerciseItemFormat> filterThisExercise (ArrayList<ExerciseItemFormat> InitialArray, String filterValue){
        ArrayList<ExerciseItemFormat> filteredArray = new ArrayList<>();
        for(int i = 0; i<InitialArray.size(); i++){
            if(InitialArray.get(i).getIsboolean().equalsIgnoreCase(filterValue)){
                filteredArray.add(InitialArray.get(i));
            }
        }
        return filteredArray;

    }
}