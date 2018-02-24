package team.getfitla

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Work on 2/24/18.
 */
class Nutrition_adaptor(val userList: ArrayList<User>) : RecyclerView.Adapter<Nutrition_adaptor.ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder?, p1: Int){
        val user: User = userList[p1]
        p0?.textViewName?.text = user.name
        p0?.textViewAddress?.text = user.address
    }
    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.list_layout, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textViewHead)
        val textViewAddress = itemView.findViewById<TextView>(R.id.textViewDesc)
    }
}