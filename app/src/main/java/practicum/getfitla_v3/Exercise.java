package practicum.getfitla_v3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.content.Context;


public class Exercise extends AppCompatActivity implements ItemClickListener{

    List<ExerciseItemFormat> exerciseList = new ArrayList<>(3);
    private ExerciseListAdapter mAdapter;
    private Context mContext;
    private void processJson(JSONObject object) {
        try {
            JSONArray rows = object.getJSONArray("rows");
            //rows = object.getJSONArray("c");

            for (int r = 0; r < rows.length(); ++r) {
                //System.out.println(rows.getJSONObject(r));


                JSONObject row = rows.getJSONObject(r);
                //System.out.println(row);
                //SONArray columns = row.getJSONArray("c");

                int id = r;
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
                Integer image = 1; // Temporary fix until we have images

                ExerciseItemFormat exercise = new ExerciseItemFormat(
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        new DownloadWebpageTask(new AsyncResult() {  //This essentially begins the whole database processing fiasco.
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1jFTMl8k53itUpU2NAXjAIBbdEChwcVJ3N-b4mQYi4qc");
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //setting the orientation of the recyclerview (by default it is vertical

        exerciseList = new ArrayList<>(); //initializing the array list
        //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
        //this will become the google sheets feed soon
        ExerciseListAdapter adaptor = new ExerciseListAdapter(this, exerciseList);
        recyclerView.setAdapter(adaptor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ExerciseListAdapter(mContext, exerciseList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, NutritionDetailActivity.class);
        startActivity(intent);
    }
}
