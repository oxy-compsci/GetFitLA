package practicum.getfitla_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

//Please ignore this page for now, this is a dummy page
public class NutritionDetailActivity extends AppCompatActivity{
    private int passedid;
    private String passedname;
    private String passedshortdesc;
    private String passedprice;
    private int passedimage;
    private String passedprepTime;
    private String passedservingSize;
    private String passedcalories;
    private String passedequipment;
    private String passeddirections;
    private String passedingredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_nutrition_detail);

        //unpack the bundled information from previous activity
        Bundle data = getIntent().getExtras();


        NutritionItemFormat CurItem = (NutritionItemFormat) data.getParcelable("RecipeInfo");
        passedid = CurItem.getId();
        passedname = CurItem.getName();
        passedshortdesc = CurItem.getShortdesc();
        passedprice = CurItem.getPrice();
        passedimage = CurItem.getImage();
        passedprepTime = CurItem.getPrepTime();
        passedservingSize = CurItem.getServingSize();
        passedcalories = CurItem.getCalories();
        passedequipment = CurItem.getEquipment();
        passeddirections = CurItem.getDirections();
        passedingredients = CurItem.getIngredients();
        passedimage = CurItem.getImage();
        System.out.println(passedshortdesc);

        TextView title = (TextView)findViewById(R.id.recipe_title);
        title.setText(passedname);
        TextView guide = (TextView)findViewById(R.id.recipe_directions);
        guide.setText(passeddirections);
    }


}
