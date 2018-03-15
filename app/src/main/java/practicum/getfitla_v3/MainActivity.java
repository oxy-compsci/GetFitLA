package practicum.getfitla_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
