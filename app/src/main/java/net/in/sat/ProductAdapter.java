package net.in.sat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.in.sat.model.Node;
import net.in.sat.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RowViewHolder> {


    private ArrayList<Product> mDataSet;
    private LayoutInflater layoutInflater;

    public ProductAdapter(Context context, ArrayList<Product> input) {
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
        return R.layout.row_product;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public ArrayList<Product> getmDataSet() {
        return mDataSet;
    }

    public void setmDataSet(ArrayList<Product> mDataSet) {
        this.mDataSet = mDataSet;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {

        private TextView name,order_count,view_count,share_count;

        public RowViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            order_count=itemView.findViewById(R.id.order_count);
            view_count=itemView.findViewById(R.id.view_count);
            share_count=itemView.findViewById(R.id.share_count);
        }

        public void setView(Product item) {
            name.setText(item.getName());

            order_count.setText(String.valueOf(item.getOrdered_count()));
            view_count.setText(String.valueOf(item.getView_count()));
            share_count.setText(String.valueOf(item.getShared_count()));

        }

    }


}
