package practicum.getfitla_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * An activity that displays a the list of Recipe Items
 */
public class Nutrition extends AppCompatActivity implements ItemClickListener {

    static final String NUTRITION_SHEET_URL = ("https://spreadsheets.google.com/tq?key=1a6YXr5Tf_nREmb9byNWLLtpKZEfIUPnDq1JLsaMXWig");

    //Creating instance of recyclerview
    RecyclerView recyclerView;
    //Creating a new list that will take object of type NutritionItemFormat, or specifically items that have been formatted
    List<NutritionItemFormat> nutritionList = new ArrayList<>();
    private NutritionListAdapter nAdapter;

    /** This method just parses the string that the JSON object becomes. Then it creates a new recipe based off of
     * these and stores that recipe in a list so that it may be added to the viewholder in the adapter.
     */


    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            for (int r = 0; r < rows.length(); ++r) {

                JSONObject row = rows.getJSONObject(r);

                int id = r;
                JSONObject Jtitle = (row.getJSONArray("c").getJSONObject(0));
                String title = Jtitle.optString("v");
                //System.out.println(title);

                JSONObject Jpreptime = (row.getJSONArray("c").getJSONObject(1));
                String preptime = Jpreptime.optString("v");
                //System.out.println(preptime);

                JSONObject Jservingsize = (row.getJSONArray("c").getJSONObject(2));
                String servingsize = Jservingsize.optString("v");
                //System.out.println(servingsize);

                JSONObject Jcalories = (row.getJSONArray("c").getJSONObject(3));
                String calories = Jcalories.optString("v");
                //System.out.println(calories);

                JSONObject Jequipment = (row.getJSONArray("c").getJSONObject(4));
                String equipment = Jequipment.optString("v");
                //System.out.println(equipment);

                JSONObject Jingredients = (row.getJSONArray("c").getJSONObject(5));
                String ingredients = Jingredients.optString("v");
                //System.out.println(ingredients);

                JSONObject Jprocess = (row.getJSONArray("c").getJSONObject(6));
                String process = Jprocess.optString("v");
               // System.out.println(process);

                JSONObject Jnotes = (row.getJSONArray("c").getJSONObject(7));
                String notes = Jnotes.optString("v");
               // System.out.println(notes);

                Integer image = R.drawable.building; // Temporary fix until we have images

                NutritionItemFormat recipe = new NutritionItemFormat(
                        id,
                        title,
                        preptime,
                        servingsize,
                        calories,
                        equipment,
                        ingredients,
                        process,
                        notes,
                        image);
                nutritionList.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Take the layout provided in the xml
        setContentView(R.layout.nutrition);
        final Nutrition this_recipe = this;
        // Downloading the nutrition spreadsheet & passing it as a JSON object to a JSON parser
        new DownloadWebpageTask(new AsyncCallback() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                recyclerView = findViewById(R.id.reyclerView);
                recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(this_recipe)); //setting the orientation of the recyclerview (by default it is vertical

                //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
                //this will become the google sheets feed soon
                NutritionListAdapter adapter = new NutritionListAdapter(this_recipe, nutritionList);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this_recipe));
                nAdapter = new NutritionListAdapter(this_recipe, nutritionList);
                recyclerView.setAdapter(nAdapter);
                nAdapter.setClickListener(this_recipe);
            }
        }).execute(NUTRITION_SHEET_URL);
    }

    //Begin launching new activity
    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, NutritionDetailActivity.class);
        startActivity(intent);
    }



}
