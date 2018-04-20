package practicum.getfitla_v3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//For more information about the code, please check out the NutritionListAdapter as they are functionally identical
//FOR SOME REASON ISBOOLEAN IS SWITCHED WITH GET BOOLEN

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.itemViewHolder> {

    private Context mtcx;
    private List<ExerciseItemFormat> fullList;
    private ItemClickListener clickListener;


    public ExerciseListAdapter(Context mtcx, List<ExerciseItemFormat> exerciseList) {
        this.mtcx = mtcx;
        this.fullList = exerciseList;
    }

    @Override
    public itemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtcx);
        View view = inflater.inflate(R.layout.e_list_layout, null);
        //itemViewHolder holder = new itemViewHolder(view);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(itemViewHolder holder, int position) {
        ExerciseItemFormat exercise = fullList.get(position);


        holder.imageview.setImageDrawable(mtcx.getResources().getDrawable(exercise.getImage()));
        holder.textViewTitle.setText(exercise.getName());
        holder.textViewEquipment.setText(exercise.getEquipment());
        holder.textViewShortDesc.setText(exercise.getIsboolean());


    }

    @Override
    public int getItemCount() {
        return fullList == null ? 0 : fullList.size();

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;

    }


    class itemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageview;
        TextView textViewTitle, textViewShortDesc,  textViewEquipment;


        public itemViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewEquipment = itemView.findViewById(R.id.textViewEquipment);
            imageview = itemView.findViewById(R.id.imageView);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener == null) {
                System.out.println("I Failed!");
            } else {
                clickListener.onClick(view, getAdapterPosition());

            }
        }
    }
}
