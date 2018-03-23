package practicum.getfitla_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.content.Context;


/**
 * An activity that displays a the list of Recipe Items
 */
public class Nutrition extends AppCompatActivity implements ItemClickListener{

    //Creating instance of recyclerview
    RecyclerView recyclerView;
    //Creating a new list that will take object of type NutritionItemFormat, or specifically items that have been formatted
    List<NutritionItemFormat> nutritionList;
    //Initializing my Adaptor
    private NutritionListAdapter mAdapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Take the layout provided in the xml
        setContentView(R.layout.nutrition);
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        //Sets a fixed size for the recycler view size, not the elements in the recycler view
        recyclerView.setHasFixedSize(true);
        //Calls the Layout Manager to apply the fixed size and default orientation of the recyclerview (by default it is vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Initializing the array list
        nutritionList = new ArrayList<>();
        //Begin adding entries
        nutritionList.add(
                new NutritionItemFormat(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        nutritionList.add(
                new NutritionItemFormat(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        nutritionList.add(
                new NutritionItemFormat(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        //Create a new instance of the Adapter for the ArrayList
        mAdapter = new NutritionListAdapter(mContext, nutritionList);
        //Binds the RecyclerView to the Adapter
        recyclerView.setAdapter(mAdapter);
        //Bind the custom onClickListener
        mAdapter.setClickListener(this);
    }
    //Begin launching new activity
    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, NutritionDetailActivity.class);
        startActivity(intent);
    }



}
