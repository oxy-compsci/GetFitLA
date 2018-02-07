package team.getfitla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
    }
    fun equipmentMethod(view: View){
        val equipIntent = Intent(this, DisplayEquipmentActivity:: class.java)
        startActivity(equipIntent)
    }
    fun noEqupmentMethod(view: View){
        val noEquipIntent = Intent(this, DisplayNoEquipmentActivity::class.java)
        startActivity(noEquipIntent)
    }
}

