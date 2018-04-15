package practicum.getfitla_v3;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import android.content.Intent;

/**
 * An activity that displays a the list of Recipe Items
 */
public class Nutrition extends AppCompatActivity implements ItemClickListener{

    static final String EXERCISE_SHEET_URL = "https://spreadsheets.google.com/tq?key=1a6YXr5Tf_nREmb9byNWLLtpKZEfIUPnDq1JLsaMXWig";


    //Creating instance of recyclerview
    RecyclerView recyclerView;
    //Creating a new list that will take object of type NutritionItemFormat, or specifically items that have been formatted
    public List<NutritionItemFormat> nutritionList = new ArrayList<>();
    //Initializing my Adaptor
    private NutritionListAdapter mAdapter;

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            //rows = object.getJSONArray("c");
            //System.out.print(rows.length());
            String name;
            String shortdesc;
            Integer image;
            // rating,
            //   price,

            String prepTime;
            String servingSize;
            String calories;
            String equipment;
            String directions;
            String ingredients;

            for (int row_id = 0; row_id < rows.length(); ++row_id) {


                //System.out.println(rows.getJSONObject(row_id));
                JSONObject row = rows.getJSONObject(row_id);
                //System.out.println("This is the row id" + row);

                int counter = 0;

                //System.out.println(row);
                //SONArray columns = row.getJSONArray("c");

                //need to write a second for loop to check for
                //name for the Recipe
                //System.out.println("name: " + counter);
                try{     JSONObject Jname = (row.getJSONArray("c").getJSONObject(counter));
                          name = Jname.optString("v");
                         //System.out.println(name);
                         counter++;}
                         catch (JSONException ex)
                {                    counter++;

                    JSONObject Jname = (row.getJSONArray("c").getJSONObject(counter));
                    name = Jname.optString("v");
                    //System.out.println(name);
                }

                //System.out.println("preptime " + counter);
                //preptime
                try{JSONObject JprepTime = (row.getJSONArray("c").getJSONObject(counter));
                prepTime = JprepTime.optString("v");
                //System.out.println(prepTime);
                counter++;}
                catch (JSONException ex) {
                    counter++;
                    JSONObject JprepTime = (row.getJSONArray("c").getJSONObject(counter));
                    prepTime = JprepTime.optString("v");
                    //System.out.println(prepTime);
                    counter++;
                    }

                //System.out.println("Servingsize " + counter);
                //write condition where json is not null
                try {JSONObject Jservingsize = (row.getJSONArray("c").getJSONObject(counter));
                servingSize = Jservingsize.optString("v");
                //System.out.println(servingSize);
                counter++;}

                catch (JSONException ex) {
                    counter++;
                    JSONObject Jservingsize = (row.getJSONArray("c").getJSONObject(counter));
                   servingSize = Jservingsize.optString("v");
                    //System.out.println(servingSize);
                    counter++;
                }

                //System.out.println("Calories" + counter);
                try {JSONObject Jcalories = (row.getJSONArray("c").getJSONObject(counter));
                calories = Jcalories.optString("v");
                //System.out.println(calories);
                counter++;}

                catch (JSONException ex) {
                    counter++;
                    JSONObject Jcalories = (row.getJSONArray("c").getJSONObject(counter));
                    calories = Jcalories.optString("v");
                    //System.out.println(calories);
                    counter++;
                }


                try {JSONObject Jequipment = (row.getJSONArray("c").getJSONObject(counter));
                equipment = Jequipment.optString("v");
                //System.out.println(equipment);
                counter++;}
                catch(JSONException ex) {
                    counter++;
                    JSONObject Jequipment = (row.getJSONArray("c").getJSONObject(counter));
                    equipment = Jequipment.optString("v");
                    //System.out.println(equipment);
                    counter++;

                }


                //System.out.println("Here are the ingredients");
                try {JSONObject Jingredients = (row.getJSONArray("c").getJSONObject(counter));
                ingredients = Jingredients.optString("v");
                //System.out.println(ingredients);
                counter++;}
                catch(JSONException ex) {
                    counter++;
                    JSONObject Jingredients = (row.getJSONArray("c").getJSONObject(counter));
                    ingredients = Jingredients.optString("v");
                    //System.out.println(ingredients);
                    counter++;

                }


                try {JSONObject Jdirections = (row.getJSONArray("c").getJSONObject(counter));
                 directions = Jdirections.optString("v");
                //System.out.println(directions);
                counter++;}

                catch (JSONException ex) {
                    counter++;
                    JSONObject Jdirections = (row.getJSONArray("c").getJSONObject(counter));
                    directions = Jdirections.optString("v");
                    //System.out.println(directions);
                    counter++;

                }


                try {JSONObject Jshortdesc = (row.getJSONArray("c").getJSONObject(counter));
                shortdesc = Jshortdesc.optString("v");
                //System.out.println(shortdesc);
                }

                catch (JSONException ex) {
                    counter++;
                    JSONObject Jshortdesc = (row.getJSONArray("c").getJSONObject(counter));
                    shortdesc = Jshortdesc.optString("v");
                    //System.out.println(shortdesc);
                }



                //Integer image = Integer.parseInt((row.getJSONArray("c").getString(4)));
                image = R.drawable.building; // Temporary fix until we have images

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
                        directions,
                        ingredients

                );
                nutritionList.add(nutrition);
                //System.out.println(nutrition);
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
        }).execute(EXERCISE_SHEET_URL);

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
