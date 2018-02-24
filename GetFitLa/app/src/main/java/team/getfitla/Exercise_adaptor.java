package team.getfitla;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Work on 2/23/18.
 */

public class Exercise_adaptor extends RecyclerView.Adapter<Exercise_adaptor.ViewHolder>{

    private List<Exercise_List_item> listItems;
    private Context context;


    public Exercise_adaptor(List<Exercise_List_item> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise_List_item listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead()); //this may be listItem instead
        holder.textViewDesc.setText(listItem.getDescription()); //this may be listItem instead

    }

    @Override
    public int getItemCount() {

        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDesc;

        public ViewHolder(View itemView) {

            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);

        }
    }

}
