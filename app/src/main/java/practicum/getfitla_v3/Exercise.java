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

import java.util.ArrayList;
import java.util.List;


public class Exercise extends AppCompatActivity implements ItemClickListener {

    static final String EXERCISE_SHEET_URL = "https://spreadsheets.google.com/tq?key=1jFTMl8k53itUpU2NAXjAIBbdEChwcVJ3N-b4mQYi4qc";

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
                    name = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                    System.out.println(name);
                } catch (JSONException ex) {
                    counter++;
                    name = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                }

                try {
                    shortdesc = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    System.out.println(shortdesc);
                    counter++;
                } catch (JSONException ex) {
                    counter++;
                    shortdesc = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    System.out.println(shortdesc);
                    counter++;
                }


                try {
                    isboolean = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                    System.out.println(isboolean);
                } catch (JSONException ex) {
                    counter++;
                    isboolean = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                }

                try {
                    equipment = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                } catch (JSONException ex) {
                    counter++;
                    equipment = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                }

                try {
                    instructions = row.getJSONArray("c").getJSONObject(counter).optString("v");
                    counter++;
                    System.out.println(instructions);
                } catch (JSONException ex) {
                    counter++;
                    instructions = row.getJSONArray("c").getJSONObject(counter).optString("v");

                    System.out.println(instructions);
                    counter++;
                }
                //Integer image = Integer.parseInt((row.getJSONArray("c").getString(4)));
                image = R.drawable.activities; // Temporary fix until we have images

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
