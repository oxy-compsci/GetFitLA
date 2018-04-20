package practicum.getfitla_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
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
    public List<NutritionItemFormat> nutritionList = new ArrayList<>();
    //Initializing my Adaptor
    private NutritionListAdapter mAdapter;
    public ArrayList add(String value, int counter, JSONObject row) {
        ArrayList Values = new ArrayList<>();
        int cur;
        cur = counter;
        while (value == "") {
            System.out.println("New Loop" + cur);

            try {
                value = row.getJSONArray("c").getJSONObject(cur).optString("v");
                cur++;
            } catch (JSONException ex) {
                cur++;
            }
        }
        Values.add(value);
        Values.add(cur);
        return Values;
    }
    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            //declare strings outside to be accessible when being added
            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                JSONObject row = rows.getJSONObject(row_id);
                //initialize secondary counter to control for the index
                int counter = 0;
                //added try/catch exceptions because of null values in Json files
                ArrayList Temp;
                String name = "";
                String shortdesc = "";
                int image;
                // rating,
                //   price,
                String prepTime = "";
                String servingSize = "";
                String calories = "";
                String equipment = "";
                String process = "";
                String ingredients = "";
                String x = "";

                Temp = add(x, counter,row);
                name = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                prepTime = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                servingSize = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                calories = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                equipment = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                ingredients = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                process = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                shortdesc = Temp.get(0).toString();

                //Integer image = Integer.parseInt((row.getJSONArray("c").getString(4)));
                image = R.drawable.nutrition; // Temporary fix until we have images

                NutritionItemFormat nutrition = new NutritionItemFormat(
                        row_id,
                        name,
                        shortdesc,
                        image,
                        // rating,
                        //   price,

                        prepTime,
                        servingSize,
                        calories,
                        equipment,
                        process,
                        ingredients

                );
                nutritionList.add(nutrition);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

//    public static void Add(String x){
//
//
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Take the layout provided in the xml
        setContentView(R.layout.nutrition);
        final Nutrition ThisNutrition = this;

        new DownloadWebpageTask(new AsyncCallback() {  //This essentially begins the whole database processing fiasco.
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                recyclerView = findViewById(R.id.reyclerView);
                recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(ThisNutrition)); //setting the orientation of the recyclerview (by default it is vertical

                //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
                //this will become the google sheets feed soon
                NutritionListAdapter adaptor = new NutritionListAdapter(ThisNutrition, nutritionList);
                recyclerView.setAdapter(adaptor);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ThisNutrition));
                mAdapter = new NutritionListAdapter(ThisNutrition, nutritionList);
                recyclerView.setAdapter(mAdapter);

                mAdapter.setClickListener(ThisNutrition);
                System.out.println("Here are the objects");
                System.out.println(nutritionList);

            }
        }).execute(NutritionSheetUrl);

    }

    //Begin launching new activity
    @Override
    public void onClick(View view, int position) {
        NutritionItemFormat current = nutritionList.get(position);

        Intent intent = new Intent(this, NutritionDetailActivity.class);
        intent.putExtra("RecipeInfo", current);

        startActivity(intent);
    }


}
