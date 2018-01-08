package net.in.sat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.in.sat.model.Node;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RowViewHolder> {


    private List<Node> mDataSet;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context, List<Node> input) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mDataSet = input;
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(viewType, parent, false);
        return new RowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {
        holder.setView(mDataSet.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public class RowViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public RowViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void setView(Node item) {
            textView.setText(item.getCategory().getName());
        }

    }


}
