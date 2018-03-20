package practicum.getfitla_v3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


import java.util.List;



/**
 * Created by Work on 3/13/18.
 */
//For more info check out --> https://www.simplifiedcoding.net/android-recyclerview-cardview-tutorial/#RecyclerView-Item-Layout-using-CardView
public class Nutrition_List_Adaptor extends RecyclerView.Adapter<Nutrition_List_Adaptor.itemViewHolder>{

    private Context mtcx;
    private List<Nutrition_Detail> fullList;
    private ItemClickListener clickListener;

    public Nutrition_List_Adaptor(Context mtcx, List<Nutrition_Detail> exerciseList) {
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
        Nutrition_Detail nutrition = fullList.get(position);

        holder.textViewTitle.setText(nutrition.getTitle());
        holder.textViewShortDesc.setText(nutrition.getShortdesc());
        holder.textViewRating.setText(String.valueOf(nutrition.getRating()));
        holder.textViewPrice.setText(String.valueOf(nutrition.getPrice()));

        holder.imageview.setImageDrawable(mtcx.getResources().getDrawable(nutrition.getImage()));


        //binds data to our viewholder
        //matches the position with the size of list
    }

    @Override
    public int getItemCount() {
        return fullList.size();

        //returns list size
    }
    //Adaptor takes viewholder located within List_adaptor
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;

     //error is here
    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

            //extra bits here http://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
           if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }

    }
}
