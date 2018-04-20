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

    public ArrayList add(String value, int counter, JSONObject row) {
        ArrayList Values = new ArrayList<>();
        int cur;
        cur = counter;
        while (value == "") {
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
            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                JSONObject row = rows.getJSONObject(row_id);
                //counter for index
                ArrayList Temp;
                int id;
                String name = "";
                String shortdesc = "";
                String isboolean = "";
                String equipment = "";
                String instructions = "";
                String x = "";
                int image;
                int counter = 0;

                Temp = add(x, counter, row);
                name = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                shortdesc = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                isboolean = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                equipment = Temp.get(0).toString();
                counter = Integer.parseInt(Temp.get(1).toString());

                Temp = add(x, counter, row);
                instructions = Temp.get(0).toString();

                image = 0;
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
        intent.putExtra("ExerciseInfo", current);
        startActivity(intent);
    }
}
