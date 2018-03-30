package practicum.getfitla_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
//Please ignore this page for now, this is a dummy page
public class NutritionDetailActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_nutrition_detail);
    }

    public void onClick_Maps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    public void onClick_Exercise(View view) {
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);

    }

    public void onClick_Nutrition(View view) {
        Intent intent = new Intent(this, Nutrition.class);
        startActivity(intent);

    }
}
