package practicum.getfitla_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

//Please ignore this page for now, this is a dummy page
public class NutritionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_nutrition_detail);
        //unpack the bundled information from previous activity
        Bundle data = getIntent().getExtras();
        NutritionItemFormat CurItem = (NutritionItemFormat) data.getParcelable("RecipeInfo");
        TextView title = (TextView) findViewById(R.id.recipe_title);
        title.setText(CurItem.getName());
        TextView preptime = (TextView) findViewById(R.id.recipe_preptime);
        preptime.setText(CurItem.getPrepTime());
        TextView servings = (TextView) findViewById(R.id.recipe_servings);
        servings.setText(CurItem.getServingSize());
        TextView calories = (TextView) findViewById(R.id.recipe_calories);
        calories.setText(CurItem.getCalories());
        TextView equipment = (TextView) findViewById(R.id.recipe_equipment);
        equipment.setText(CurItem.getEquipment());
        TextView ingredients = (TextView) findViewById(R.id.recipe_ingredients);
        ingredients.setText(CurItem.getIngredients());
        TextView directions = (TextView) findViewById(R.id.recipe_directions);
        directions.setText(CurItem.getProcess());
        ImageView image = (ImageView) findViewById(R.id.nutrition_image);
        Picasso.with(this).load(CurItem.getImage()).into(image);
    }
}
