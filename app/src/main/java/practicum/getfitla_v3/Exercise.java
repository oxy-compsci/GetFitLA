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

public class Exercise extends AppCompatActivity implements ItemClickListener{

    RecyclerView recyclerView;
    List<ExerciseItemFormat> exerciseList;
    private ExerciseListAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exerciseList = new ArrayList<>();
        exerciseList.add(
                new ExerciseItemFormat(
                        1,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        exerciseList.add(
                new ExerciseItemFormat(
                        1,
                        "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                        "14 inch, Gray, 1.659 kg",
                        4.3,
                        60000,
                        R.drawable.building));

        exerciseList.add(
                new ExerciseItemFormat(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.building));

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
