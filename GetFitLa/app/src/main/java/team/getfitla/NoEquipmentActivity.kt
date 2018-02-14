package team.getfitla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NoEquipmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noequipment_exercise)
    }
    fun groupNoEquipActivities (view: View){
        val groupNoEquipIntent = Intent(this, GroupNoEquipmentActivity::class.java)
        startActivity(groupNoEquipIntent)
    }
    fun individualNoEquipActivities (view: View){
        val individualNoEquipIntent = Intent(this, IndividualNoEquipmentActivity::class.java)
        startActivity(individualNoEquipIntent)
    }
}
