package net.in.sat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.in.sat.model.Node;

import java.util.ArrayList;

/**
 * Created by rohitlondhe on 08/01/18.
 */

public class CategoryFragment extends BaseListFragment {


    public static final String NODE_LIST = "node_list";

    public CategoryFragment() {

    }

    public static  CategoryFragment getInstance(ArrayList<Node> categories){

        CategoryFragment categoryFragment=new CategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(NODE_LIST,categories);
        categoryFragment.setArguments(bundle);

        return categoryFragment;
    }


     public void setupListView(RecyclerView mRecyclerView) {

        final ArrayList<Node> nodes= getArguments().getParcelableArrayList(NODE_LIST);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new CategoryAdapter(getContext(),nodes));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Node node=nodes.get(position);
                Fragment fragment=null;
                if(node.getChild()==null || node.getChild().size()==0){
                    fragment=ProductListFragment.getInstance(node.getCategory().getProducts());
                }else{
                    fragment = CategoryFragment.getInstance(node.getChild());
                }



                ((MainActivity)getActivity()).navigateToFragment(fragment, nodes.get(position).getCategory().getName());

            }
        }));


    }




}

