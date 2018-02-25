package team.getfitla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

class NutritionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val users = ArrayList<User>()
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))


        val adapter = Nutrition_adaptor(users)
        recyclerView.adapter = adapter
    }
    }

