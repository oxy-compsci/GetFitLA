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
 * Created by Work on 3/13/18.
 */

public class Nutrition extends AppCompatActivity implements ItemClickListener{

    RecyclerView recyclerView;
    //creating instances of adaptor to link to the recyclerview
    List<Nutrition_Detail> nutritionList;
    private Nutrition_List_Adaptor mAdapter; //new adaptor instance
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition);
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //setting the orientation of the recyclerview (by default it is vertical
        nutritionList = new ArrayList<>(); //initializing the array list

        nutritionList.add(
                new Nutrition_Detail(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        nutritionList.add(
                new Nutrition_Detail(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        nutritionList.add(
                new Nutrition_Detail(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        mAdapter = new Nutrition_List_Adaptor(mContext, nutritionList);
        Nutrition_List_Adaptor adaptor = new Nutrition_List_Adaptor(mContext, nutritionList);
        recyclerView.setAdapter(adaptor);
        mAdapter.setClickListener(this); //bind the listener
    }

    @Override
    public void onClick(View view, int position) {
        Nutrition_Detail nutrition = nutritionList.get(position);
        Intent intent = new Intent(mContext, Nutrition_New_Page.class);
        System.out.println("YOYOYOYOYOYOYOYOYOYOYOYOYYadsfjaldsfa");
        startActivity(intent);

    }

    //http://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/


}
