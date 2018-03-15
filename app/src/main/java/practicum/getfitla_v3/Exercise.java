package practicum.getfitla_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Work on 3/13/18.
 */

public class Exercise extends AppCompatActivity {

    RecyclerView recyclerView;
    //creating instances of adaptor to link to the recyclerview
    List<Exercise_Detail> exerciseList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true); //sets a fixed size for the recycler view size, not the elements in the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //setting the orientation of the recyclerview (by default it is vertical

        exerciseList = new ArrayList<>(); //initializing the array list
        //each item in the arraylist will be an object. Each object is created in the detail file and add()ed here
        //this will become the google sheets feed soon
        exerciseList.add(
                new Exercise_Detail(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        exerciseList.add(
                new Exercise_Detail(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        exerciseList.add(
                new Exercise_Detail(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        Exercise_List_Adaptor adaptor = new Exercise_List_Adaptor(this, exerciseList);
        recyclerView.setAdapter(adaptor);

    }
}
