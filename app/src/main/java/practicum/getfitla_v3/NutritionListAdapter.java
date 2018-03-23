package practicum.getfitla_v3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
/**
 * This is the class that handles
*Following link(s) were used to implement the Adapter
* https://www.simplifiedcoding.net/android-recyclerview-cardview-tutorial/#RecyclerView-Item-Layout-using-CardView
* http://www.codexpedia.com/android/defining-item-click-listener-for-recyclerview-in-android/
*/
public class NutritionListAdapter extends RecyclerView.Adapter<NutritionListAdapter.itemViewHolder>{

    private Context mtcx;
    private List<NutritionItemFormat> fullList;
    private ItemClickListener clickListener;

    public NutritionListAdapter(Context mtcx, List<NutritionItemFormat> exerciseList) {
        this.mtcx = mtcx;
        this.fullList = exerciseList;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtcx);
        View view = inflater.inflate(R.layout.list_layout, null);
        // itemViewHolder holder = new itemViewHolder(view);
        //creates a viewholder by returning an instance of the viewholder class
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {
        NutritionItemFormat nutrition = fullList.get(position);
        //binds data to our viewholder

        holder.textViewTitle.setText(nutrition.getTitle());
        holder.textViewShortDesc.setText(nutrition.getShortdesc());
        holder.textViewRating.setText(String.valueOf(nutrition.getRating()));
        holder.textViewPrice.setText(String.valueOf(nutrition.getPrice()));
        holder.imageview.setImageDrawable(mtcx.getResources().getDrawable(nutrition.getImage()));
    }

    //Matches the position with the size of list
    @Override
    public int getItemCount() {
        //returns list size
        return fullList == null ? 0 : fullList.size();
    }

    //Adaptor takes viewholder located within List_adaptor
    public void setClickListener(ItemClickListener itemClickListener) {
        //Passing instance of clicklistener to a short name
        this.clickListener = itemClickListener;

    }

    public class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //This is the actual class that contains all the relevant information in a given entry
        ImageView imageview;
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;


        public itemViewHolder(View itemView) {
            super(itemView);
            //Code meant for information of each entry
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageview = itemView.findViewById(R.id.imageView);

            //Code that is specifically for the OnClickListener
            itemView.setTag(itemView);
            //sets each instance of OnClickListener to the item
            itemView.setOnClickListener(this);
        }

        //the actual onclick listener
        @Override
        public void onClick(View view) {
            //If the onClick detects a click but it is not connected to the Arraylist, it will return a null
           if (clickListener == null) {
               //Debug code to indicate failure
               System.out.println("I Failed!");
            //successful click
           } else {
               clickListener.onClick(view, getAdapterPosition());
           }



        }

    }
}
