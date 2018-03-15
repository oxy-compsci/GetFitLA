package practicum.getfitla_v3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



/**
 * Created by Work on 3/13/18.
 */
//For more info check out --> https://www.simplifiedcoding.net/android-recyclerview-cardview-tutorial/#RecyclerView-Item-Layout-using-CardView
public class Exercise_List_Adaptor extends RecyclerView.Adapter<Exercise_List_Adaptor.itemViewHolder>{

    private Context mtcx;
    private List<Exercise_Detail> fullList;

    public Exercise_List_Adaptor(Context mtcx, List<Exercise_Detail> exerciseList) {
        this.mtcx = mtcx;
        this.fullList = exerciseList;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtcx);
        View view = inflater.inflate(R.layout.list_layout, null);
        //itemViewHolder holder = new itemViewHolder(view);
        return new itemViewHolder(view);
        //creates a viewholder by returning an instance of the viewholder class
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {
        Exercise_Detail exercise = fullList.get(position);

        holder.textViewTitle.setText(exercise.getTitle());
        holder.textViewShortDesc.setText(exercise.getShortdesc());
        holder.textViewRating.setText(String.valueOf(exercise.getRating()));
        holder.textViewPrice.setText(String.valueOf(exercise.getPrice()));

        holder.imageview.setImageDrawable(mtcx.getResources().getDrawable(exercise.getImage()));


        //binds data to our viewholder
        //matches the position with the size of list
    }

    @Override
    public int getItemCount() {
        return fullList.size();

        //returns list size
    }
    //Adaptor takes viewholder located within List_adaptor

    class itemViewHolder extends RecyclerView.ViewHolder{
        //this is the actual class that contains all the relevant information in a given entry
        ImageView imageview;
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;


        public itemViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageview = itemView.findViewById(R.id.imageView);
        }
    }
}
