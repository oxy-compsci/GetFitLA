package practicum.getfitla_v3;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * An activity that displays a the list of Recipe Items
 */
public class Nutrition extends AppCompatActivity implements ItemClickListener {
    static final String NutritionSheetUrl = "https://spreadsheets.google.com/tq?key=1a6YXr5Tf_nREmb9byNWLLtpKZEfIUPnDq1JLsaMXWig";
    //Creating instance of recyclerview
    RecyclerView recyclerView;
    //Creating a new list that will take object of type NutritionItemFormat, or specifically items that have been formatted
    public ArrayList<NutritionItemFormat> filteredList = new ArrayList<>();
    public ArrayList<NutritionItemFormat> nutritionList = new ArrayList<>();
    private NutritionListAdapter mAdapter;
    public String getFromRow(JSONObject row, int counter) {
        try {
            //check if valid
            return row.getJSONArray("c").getJSONObject(counter).optString("v");
        } catch (JSONException ex) {
            //exception for null values
            return "";
        }
    }
    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            //declare strings outside to be accessible when being added
            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                JSONObject row = rows.getJSONObject(row_id);
                //initialize secondary counter to control for the index
                //added try/catch exceptions because of null values in Json files
                //just null values used to check if a valid value was found
                String ingredients = "";
                String rating  = "";
                String price = "";
                String x = "";
                //temporary holding arraylist of answers
              
                NutritionItemFormat nutrition = new NutritionItemFormat(
                        row_id,
                        getFromRow(row, 0), // name
                        getFromRow(row, 7), // shortdesc
                        getFromRow(row, 9), //image
                        getFromRow(row, 1), // prepTime
                        getFromRow(row, 2), // servingSize
                        getFromRow(row, 3), // calories
                        getFromRow(row, 4), // equipment
                        getFromRow(row, 6), // process
                        getFromRow(row, 5), // ingredients
                        rating,
                        price,
                        getFromRow(row, 8) // category
                );
                nutritionList.add(nutrition);
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
        final Nutrition thisNutrition = this;
        //This essentially begins the whole database processing fiasco.
        new DownloadWebpageTask(new AsyncCallback() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                recyclerView = findViewById(R.id.reyclerView);
                //sets a fixed size for the recycler view size, not the elements in the recycler view
                recyclerView.setHasFixedSize(true);
                //setting the orientation of the recyclerview (by default it is vertical
                recyclerView.setLayoutManager(new LinearLayoutManager(thisNutrition));
                //each item in the arraylist will be an object. Each object is created in the detail file and added here
                NutritionListAdapter adaptor = new NutritionListAdapter(thisNutrition, filteredList);
                recyclerView.setAdapter(adaptor);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(thisNutrition));
                mAdapter = new NutritionListAdapter(thisNutrition, filteredList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setClickListener(thisNutrition);
                setupSorter();
            }
        }).execute(NutritionSheetUrl);
    }
    @Override
    public void onClick(View view, int position) {
        NutritionItemFormat current = nutritionList.get(position);
        Intent intent = new Intent(this, NutritionDetailActivity.class);
        intent.putExtra("RecipeInfo", current);
        startActivity(intent);
    }
    public void onClickNutrition(View view, int itemtoadd){
        String filterValue="";
        // adding items to the filter list & updating the list to be full in case you unfilter the item.
        if(itemtoadd == 0);

        else if(itemtoadd == 1) {
            filterValue = "B";
        }
        else if(itemtoadd == 2) {
            filterValue = "L";
        }
        else if(itemtoadd == 3) {
            filterValue = "D";
        }
        filteredList.clear();
        filteredList.addAll(Filter.filterThis(nutritionList, filterValue));
        mAdapter.notifyDataSetChanged();
    }
    private void setupSorter(){
        final Spinner nutritionSpinner = findViewById(R.id.nutritionSpinner);
        ArrayAdapter<CharSequence> nutritionSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.nutritionCategories, android.R.layout.simple_spinner_item);
        nutritionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nutritionSpinner.setAdapter(nutritionSpinnerAdapter);
        nutritionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filteredList.clear();
                filteredList.addAll(nutritionList);
                if (position > 0) { // not "no filter"
                    onClickNutrition(view, position);
                }
                else {
                    mAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> AdapterView){
            }
        });
    }
}