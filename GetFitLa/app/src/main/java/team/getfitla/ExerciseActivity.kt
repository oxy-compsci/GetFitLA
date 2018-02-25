package team.getfitla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Adapter
import android.widget.LinearLayout

class ExerciseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val users = ArrayList<User>()
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))
        users.add(User("William Chen", "Shanghai Chiba"))
        users.add(User("Will Ch", "Beijing, China"))





        val adapter = Exercise_adaptor_new(users)
        recyclerView.adapter = adapter
    }

}

