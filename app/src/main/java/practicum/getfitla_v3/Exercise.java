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
//Link advising how to format the image url for downloads
//https://www.lmeservices.com/share-google-drive-files-via-url/

public class Exercise extends AppCompatActivity implements ItemClickListener {
    static final String EXERCISE_SHEET_URL = "https://spreadsheets.google.com/tq?key=1jFTMl8k53itUpU2NAXjAIBbdEChwcVJ3N-b4mQYi4qc";
    //creating instances of adaptor to link to the recyclerview
    RecyclerView recyclerView;
    //object is ExerciseItemFormat
    public List<ExerciseItemFormat> exerciseList = new ArrayList<>();
    private ExerciseListAdapter mAdapter;

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
            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                JSONObject row = rows.getJSONObject(row_id);
                //creates a new instance of exercise for each exercise type
                ExerciseItemFormat exercise = new ExerciseItemFormat(
                        row_id,
                        getFromRow(row, 0),
                        getFromRow(row, 1),
                        getFromRow(row, 2),
                        getFromRow(row, 3),
                        getFromRow(row, 4),
                        getFromRow(row, 5)
                );
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
