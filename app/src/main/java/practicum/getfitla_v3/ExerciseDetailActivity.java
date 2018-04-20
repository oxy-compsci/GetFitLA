package practicum.getfitla_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//Please ignore this page for now, this is a dummy page
public class ExerciseDetailActivity extends AppCompatActivity{
    private int passedid;
    private String passedtitle;
    private String passedshortdesc;
    private String passedisboolean;
    private String passedequipment;
    private String passedinstructions;
    private int passedimage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_exercise_detail);


        Bundle data = getIntent().getExtras();
        //extra.putSerializable("Exercise_List", (exerciseList));
        //ExerciseItemFormat ExercistItem = (ExerciseItemFormat) data.getParcelable("ExerciseInfo");
        //Bundle data = getIntent().getExtras();
        ExerciseItemFormat CurItem = (ExerciseItemFormat) data.getParcelable("ExerciseInfo");
        passedid = CurItem.getId();
        passedtitle = CurItem.getName();
        passedshortdesc = CurItem.getShortdesc();
        passedisboolean = CurItem.getIsboolean();
        passedequipment = CurItem.getEquipment();
        passedinstructions = CurItem.getInstructions();
        passedimage = CurItem.getImage();
        System.out.println(passedshortdesc);

        TextView title = (TextView) findViewById(R.id.exercise_title);
        title.setText(passedtitle);
        TextView guide = (TextView) findViewById(R.id.exercise_guide);
        guide.setText(passedinstructions);







    }


}
