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




public class Exercise extends AppCompatActivity implements ItemClickListener {

    static final String EXERCISE_SHEET_URL = "https://spreadsheets.google.com/tq?key=10ZQ7w7r1U6LM4VPAVaMQ3nOPIBHZ7qXcUFgA66wwjys";

    //creating instances of adaptor to link to the recyclerview
    RecyclerView recyclerView;
    //object is ExerciseItemFormat
    public List<ExerciseItemFormat> exerciseList = new ArrayList<>();

    private ExerciseListAdapter mAdapter;

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");

            int id;
            String name;
            String shortdesc;
            String isboolean;
            String equipment;
            String instructions;
            int image;

            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                //System.out.println(rows.getJSONObject(row_id));

                JSONObject row = rows.getJSONObject(row_id);
                //System.out.println(row);
                //SONArray columns = row.getJSONArray("c");

                //counter for index
                int counter = 0;

                try {
                JSONObject Jname = (row.getJSONArray("c").getJSONObject(counter));
                name = Jname.optString("v");
                counter++;
                System.out.println(name); }
                catch (JSONException ex) {
                    counter++;
                    JSONObject Jname = (row.getJSONArray("c").getJSONObject(counter));
                    name = Jname.optString("v");
                    counter++;
                }

                try {
                    JSONObject Jshortdesc = (row.getJSONArray("c").getJSONObject(counter));
                shortdesc = Jshortdesc.optString("v");
                System.out.println(shortdesc);
                counter++;}
                catch(JSONException ex) {
                    counter++;
                    JSONObject Jshortdesc = (row.getJSONArray("c").getJSONObject(counter));
                    shortdesc = Jshortdesc.optString("v");
                    System.out.println(shortdesc);
                    counter++;
                }


                try {
                    JSONObject Jisboolean = (row.getJSONArray("c").getJSONObject(counter));
                    isboolean = Jisboolean.optString("v");
                    counter++;
                    System.out.println(isboolean); }
                catch (JSONException ex) {
                    counter++;
                    JSONObject Jisboolean = (row.getJSONArray("c").getJSONObject(counter));
                    isboolean = Jisboolean.optString("v");
                    counter++;
                }

                try{JSONObject Jequipment = (row.getJSONArray("c").getJSONObject(counter));
                equipment = Jequipment.optString("v");
                System.out.println(equipment);
                counter++;}
                catch (JSONException ex) {
                    counter++;
                    JSONObject Jequipment = (row.getJSONArray("c").getJSONObject(counter));
                    equipment = Jequipment.optString("v");
                    System.out.println(equipment);
                    counter++;}

                try{
                JSONObject Jinstructions = (row.getJSONArray("c").getJSONObject(counter));
                instructions = Jinstructions.optString("v");
                counter++;
                System.out.println(instructions);}

                catch (JSONException ex) {
                    counter++;
                    JSONObject Jinstructions = (row.getJSONArray("c").getJSONObject(counter));
                    instructions = Jinstructions.optString("v");
                    System.out.println(instructions);
                    counter++;
                }
                //Integer image = Integer.parseInt((row.getJSONArray("c").getString(4)));
                image = R.drawable.building; // Temporary fix until we have images

                //creates a new instance of exercise for each exercise type
                ExerciseItemFormat exercise = new ExerciseItemFormat(
                        row_id,
                        name,
                        isboolean,
                        shortdesc,
                        equipment,
                        instructions,
                        image);
                exerciseList.add(exercise);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        final Exercise this_exercise = this;
        new DownloadWebpageTask(new AsyncCallback() {  //This essentially begins the whole database processing fiasco.
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
                recyclerView = findViewById(R.id.reyclerView);
                recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(this_exercise)); //setting the orientation of the recyclerview (by default it is vertical

                //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
                //this will become the google sheets feed soon
                ExerciseListAdapter adaptor = new ExerciseListAdapter(this_exercise, exerciseList);
                recyclerView.setAdapter(adaptor);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this_exercise));
                mAdapter = new ExerciseListAdapter(this_exercise, exerciseList);
                recyclerView.setAdapter(mAdapter);

                mAdapter.setClickListener(this_exercise);

            }
        }).execute(EXERCISE_SHEET_URL);
    }




    @Override
    public void onClick(View view, int position) {

        ExerciseItemFormat current = exerciseList.get(position);
        Intent intent = new Intent(this, ExerciseDetailActivity.class);
      //  ArrayList<ExerciseItemFormat> passedExercise = exerciseList;

        intent.putExtra("ExerciseInfo", current);

        startActivity(intent);
    }
}
