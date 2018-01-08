package net.in.sat;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import net.in.sat.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rohitlondhe on 09/01/18.
 */

public class ProductListFragment extends BaseListFragment {

    public static final String PRODUCT_LIST = "product_list";
    private ProductAdapter productAdapter;
    private ArrayList<Product> nodes;


    public ProductListFragment() {
    }


    public static ProductListFragment getInstance(List<Product> products) {

        ProductListFragment productListFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(PRODUCT_LIST, (ArrayList<? extends Parcelable>) products);
        productListFragment.setArguments(bundle);

        return productListFragment;
    }


    @Override
    public void setupListView(RecyclerView mRecyclerView) {

        nodes = getArguments().getParcelableArrayList(PRODUCT_LIST);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productAdapter = new ProductAdapter(getContext(), nodes);
        mRecyclerView.setAdapter(productAdapter);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    private Comparator<Product> viewCountComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getView_count() - p1.getView_count();
        }
    };

    private Comparator<Product> orderCountComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getOrdered_count() - p1.getOrdered_count();
        }
    };

    private Comparator<Product> shareCountComparator = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p2.getShared_count() - p1.getShared_count();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_sort_view:
                Collections.sort(nodes, viewCountComparator);
                productAdapter.setmDataSet(nodes);
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_order:
                Collections.sort(nodes, orderCountComparator);
                productAdapter.setmDataSet(nodes);
                productAdapter.notifyDataSetChanged();
                return true;

            case R.id.action_sort_share:
                Collections.sort(nodes, shareCountComparator);
                productAdapter.setmDataSet(nodes);
                productAdapter.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
