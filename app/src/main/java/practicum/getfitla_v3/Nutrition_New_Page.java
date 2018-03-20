package practicum.getfitla_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Work on 3/17/18.
 */

public class Nutrition_New_Page extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_new_page);
    }

    public void onClick_Maps(View view) {
        Intent intent = new Intent(this, Maps.class);
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
