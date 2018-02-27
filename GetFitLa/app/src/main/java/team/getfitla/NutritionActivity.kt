package team.getfitla

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_nutrition.*

var nutrDescription: String = "Input_Description_of_Recipe"

class NutritionActivity : AppCompatActivity() {

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

    private var nutrNotes = ArrayList<Note>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition)

        nutrNotes.add(Note(0, "Breakfast Quinoa", nutrDescription))


        var notesAdapter = NotesAdapter(this, nutrNotes)
        nNotes.adapter = notesAdapter
        nNotes.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            //Toast.makeText(this, "Click on " + listNotes[position].title, Toast.LENGTH_SHORT).show()
            val nutrIntent = Intent(this, NutritionHardCode::class.java)
            startActivity(nutrIntent)
        }

    }
    inner class NotesAdapter : BaseAdapter {

        private var notesList = ArrayList<NutritionActivity.Note>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<NutritionActivity.Note>) : super() {
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

