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

    // Open Map when Map button clicked
   // fun openMap(view: View) {
        // reminder - look into getting api key restrictions
    //}
    /** Intent for exercise activities
     * starts the exercise activity*/
    fun exerciseMethod(view: View){
        val exerciseIntent = Intent(this,ExerciseActivity::class.java)
        startActivity(exerciseIntent)
    }

}
