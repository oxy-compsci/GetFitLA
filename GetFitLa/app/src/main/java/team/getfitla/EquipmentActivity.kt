package team.getfitla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EquipmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipment_exercise)
    }

    /** building intents for group and individual exercise activities */
    fun groupActivities (view: View){
        val groupIntent = Intent(this, GroupEquipmentActivity::class.java)
        startActivity(groupIntent)
    }
    fun individualActivities (view: View){
        val individualIntent = Intent(this, IndividualEquipmentActivity::class.java)
        startActivity(individualIntent)
        }
}
