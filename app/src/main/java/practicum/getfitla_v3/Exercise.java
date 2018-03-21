package practicum.getfitla_v3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

/**
 * Created by Work on 3/13/18.
 */

public class Exercise extends AppCompatActivity {

    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");
                int id = r;
                String title = columns.getJSONObject(0).getString("v");
                String shortdesc = columns.getJSONObject(1).getString("v");
                double rating = columns.getJSONObject(2).getDouble("v");
                double price = columns.getJSONObject(3).getDouble("v");
                int image = columns.getJSONObject(4).getInt("v");
                Exercise_Detail exercise = new Exercise_Detail(
                        id,
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

    RecyclerView recyclerView;
    //creating instances of adaptor to link to the recyclerview
    List<Exercise_Detail> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://docs.google.com/spreadsheets/d/1jFTMl8k53itUpU2NAXjAIBbdEChwcVJ3N-b4mQYi4qc/edit#gid=0");


        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //setting the orientation of the recyclerview (by default it is vertical

        exerciseList = new ArrayList<>(); //initializing the array list
        //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
        //this will become the google sheets feed soon
        Exercise_List_Adaptor adaptor = new Exercise_List_Adaptor(this, exerciseList);
        recyclerView.setAdapter(adaptor);
    }


}
