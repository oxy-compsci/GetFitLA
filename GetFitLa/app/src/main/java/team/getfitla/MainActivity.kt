package team.getfitla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Intent for exercise activities
     * starts the exercise activity*/
    fun exerciseMethod(view: View){
        val exerciseIntent = Intent(this,ExerciseActivity::class.java)
        startActivity(exerciseIntent)
    }

    // Intent for maps activity
    fun mapsMethod(view: View) {
        val mapsIntent = Intent(this,MapsActivity::class.java)
        startActivity(mapsIntent)
    }


    //Intent for that nutrition boi
    fun nutritionMethod(view: View) {
        val nutritionIntent = Intent(this, NutritionActivity::class.java)
        startActivity(nutritionIntent)
    }

}
