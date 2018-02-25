package team.getfitla

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextPaint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*

var TempDescription: String = "Input_Description_of_Exercise"

class ExerciseActivity : AppCompatActivity() {
    class Note {

        var id: Int? = null
        var title: String? = null
        var content: String? = null

        constructor(id: Int, title: String, content: String) {
            this.id = id
            this.title = title
            this.content = content
        }
    }

    private var listNotes = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        // temporary description for showcase
        listNotes.add(Note(0,"Arrays are indexed at 0", TempDescription))
        listNotes.add(Note(1,"baseball",TempDescription))
        listNotes.add(Note(2,"Basketball", TempDescription))
        listNotes.add(Note(3,"Football", TempDescription))
        listNotes.add(Note(4,"Frisbee",TempDescription))
        listNotes.add(Note(5,"Hike", TempDescription))
        listNotes.add(Note(6,"Park Playground", TempDescription))
        listNotes.add(Note(7,"Soccer", TempDescription))
        listNotes.add(Note(8,"Tennis", TempDescription))
        listNotes.add(Note(9,"Chin-Ups", TempDescription))
        listNotes.add(Note(10,"Dips", TempDescription))
        listNotes.add(Note(11,"Stair Climb", TempDescription))
        listNotes.add(Note(12,"Jump Rope", TempDescription))
        listNotes.add(Note(13,"Bike Riding", TempDescription))
        listNotes.add(Note(14,"Hike", TempDescription))
        listNotes.add(Note(15,"Swimming", TempDescription))
        listNotes.add(Note(16,"Overhead Press", TempDescription))
        listNotes.add(Note(17,"Rows", TempDescription))
        listNotes.add(Note(18,"Dance Party", TempDescription))
        listNotes.add(Note(19,"Family Walk", TempDescription))
        listNotes.add(Note(20,"Obstacle Course Race", TempDescription))
        listNotes.add(Note(21,"Arm Circles", TempDescription))
        listNotes.add(Note(22,"Body Squats", TempDescription))
        listNotes.add(Note(23,"Bridges", TempDescription))
        listNotes.add(Note(24,"High Knees", TempDescription))
        listNotes.add(Note(25,"Jumping Jacks", TempDescription))
        listNotes.add(Note(26,"Leg Raises", TempDescription))
        listNotes.add(Note(27,"Lunges", TempDescription))
        listNotes.add(Note(28,"Planks", TempDescription))
        listNotes.add(Note(29,"Pushups", TempDescription))
        listNotes.add(Note(30,"Sit Ups", TempDescription))
        listNotes.add(Note(31,"Jogging", TempDescription))
        listNotes.add(Note(32,"Butt Kicks", TempDescription))
        listNotes.add(Note(33,"Calf Raises", TempDescription))
        listNotes.add(Note(34,"Donkey Kicks", TempDescription))
        listNotes.add(Note(35,"Fire Hydrants", TempDescription))
        listNotes.add(Note(36,"Front Kicks", TempDescription))
        listNotes.add(Note(37,"Heel Touches", TempDescription))
        listNotes.add(Note(38,"Power Skips", TempDescription))
        listNotes.add(Note(39,"Punches", TempDescription))
        listNotes.add(Note(40,"Step Ups", TempDescription))
        listNotes.add(Note(41,"Wall Sit", TempDescription))

        var notesAdapter = NotesAdapter(this, listNotes)
        lvNotes.adapter = notesAdapter
        lvNotes.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + listNotes[position].title, Toast.LENGTH_SHORT).show()
        }

    }
    inner class NotesAdapter : BaseAdapter {

        private var notesList = ArrayList<Note>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<Note>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.note, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            vh.tvTitle.text = notesList[position].title
            vh.tvContent.text = notesList[position].content

            return view
        }

        override fun getItem(position: Int): Any {
            return notesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notesList.size
        }
    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById<TextView>(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById<TextView>(R.id.tvContent) as TextView
        }

        //  if you target API 26, you should change to:
//        init {
//            this.tvTitle = view?.findViewById<TextView>(R.id.tvTitle) as TextView
//            this.tvContent = view?.findViewById<TextView>(R.id.tvContent) as TextView
//        }
    }
}