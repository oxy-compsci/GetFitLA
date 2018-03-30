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

    static final String EXERCISE_SHEET_URL = "https://spreadsheets.google.com/tq?key=1jFTMl8k53itUpU2NAXjAIBbdEChwcVJ3N-b4mQYi4qc";

    List<ExerciseItemFormat> exerciseList = new ArrayList<>();
    RecyclerView recyclerView;
    //creating instances of adaptor to link to the recyclerview
    private ExerciseListAdapter mAdapter;

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            //rows = object.getJSONArray("c");

            for (int row_id = 0; row_id < rows.length(); ++row_id) {
                //System.out.println(rows.getJSONObject(row_id));


                JSONObject row = rows.getJSONObject(row_id);
                //System.out.println(row);
                //SONArray columns = row.getJSONArray("c");

                JSONObject Jtitle = (row.getJSONArray("c").getJSONObject(0));
                String title = Jtitle.optString("v");
                System.out.println(title);

                JSONObject Jshortdesc = (row.getJSONArray("c").getJSONObject(1));
                String shortdesc = Jshortdesc.optString("v");
                System.out.println(shortdesc);

                JSONObject Jrating = (row.getJSONArray("c").getJSONObject(2));
                String rating = Jrating.optString("v");
                System.out.println(rating);

                JSONObject Jprice = (row.getJSONArray("c").getJSONObject(3));
                String price = Jprice.optString("v");
                System.out.println(price);
                //Integer image = Integer.parseInt((row.getJSONArray("c").getString(4)));
                Integer image = R.drawable.building; // Temporary fix until we have images

                ExerciseItemFormat exercise = new ExerciseItemFormat(
                        row_id,
                        title,
                        shortdesc,
                        rating,
                        price,
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
        Intent intent = new Intent(this, NutritionDetailActivity.class);
        startActivity(intent);
    }
}
