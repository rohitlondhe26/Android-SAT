package net.in.sat;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.in.sat.model.APIResponse;
import net.in.sat.model.Category;
import net.in.sat.model.Node;
import net.in.sat.model.Product;
import net.in.sat.model.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String CATEGORY = "Category";
    public static final String RANKING = "Ranking";
    private ProgressDialog dialog;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Object parent = DataPool.getInstance().get(CATEGORY);

        if (parent == null)
            fetchData();
        else
            navigateToFragment(CategoryFragment.getInstance((ArrayList<Node>) parent), "parent");

    }

    private void fetchData() {
        RetroRequestBuilder retroRequestBuilder = new RetroRequestBuilder();
        dialog = ProgressDialog.show(this, "", "Loading...", true);
        Call<APIResponse> responseCall = retroRequestBuilder.getDataFetcher().fetchData();
        responseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                if (response.isSuccessful()) {

                    prepareData(response.body());

                    Toast.makeText(MainActivity.this, "DONE Fetch successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepareData(APIResponse body) {

        if (dialog != null)
            dialog.dismiss();

        ArrayList<Node> parent = new ArrayList<>();
        categories = body.getCategories();

        List<Integer> rootNodeId = findRootNodeList();

//        Log.d("Root Node List", rootNodeId.toString());

        for (Ranking ranking : body.getRankings()) {

            Collections.sort(ranking.getProductInfo(), new Comparator<Ranking.ProductInfo>() {
                @Override
                public int compare(Ranking.ProductInfo p1, Ranking.ProductInfo p2) {
                    return p1.getId() - p2.getId();
                }
            });
        }

        DataPool.getInstance().put(RANKING, body.getRankings());

        for (Integer temp : rootNodeId) {
            Node node = prepareCategoryHierarchy(temp);
            parent.add(node);
        }

        if (parent.size() > 0)
            DataPool.getInstance().put(CATEGORY, parent);

        DataPool.getInstance().remove(RANKING);
        categories = null;

        navigateToFragment(CategoryFragment.getInstance(parent), "parent");
    }


    private List<Integer> findRootNodeList() {

        List<Integer> parent = new ArrayList<>();
        List<Integer> child = new ArrayList<>();

        for (Category current : categories) {

            if (current.getChild_categories() != null && current.getChild_categories().size() > 0) {

                int current_category_id = current.getId();
                /**
                 * This Logic Manage to find 0th level of node in tree
                 */
                if (child.indexOf(current_category_id) == -1) {
                    parent.add(current_category_id);
                } else {
                    int index = parent.indexOf(current_category_id);
                    if (index >= 0)
                        parent.remove(index);
                }

                child.addAll(current.getChild_categories());

            }

        }

        child = null;
        return parent;
    }

    private Node prepareCategoryHierarchy(Integer key) {

        for (Category temp : categories) {

            if (key == temp.getId()) {
                ArrayList<Node> result = null;

                if (temp.getChild_categories() != null && temp.getChild_categories().size() > 0) {

                    result = new ArrayList<>();

                    for (Integer id : temp.getChild_categories()) {
                        result.add(prepareCategoryHierarchy(id));
                    }
                }

                appendValues(temp);

                return new Node(temp, result);
            }

        }

        return null;
    }

    private void appendValues(Category temp) {

        List<Ranking> rankingList = (List<Ranking>) DataPool.getInstance().get(RANKING);

        for (Product product : temp.getProducts()) {

            Ranking.ProductInfo viewInfo = binarySearch((ArrayList<Ranking.ProductInfo>) rankingList.get(0).getProductInfo(), 0, rankingList.get(0).getProductInfo().size() - 1, product.getId());
            Ranking.ProductInfo orderedInfo = binarySearch((ArrayList<Ranking.ProductInfo>) rankingList.get(1).getProductInfo(), 0, rankingList.get(1).getProductInfo().size() - 1, product.getId());
            Ranking.ProductInfo sharedInfo = binarySearch((ArrayList<Ranking.ProductInfo>) rankingList.get(2).getProductInfo(), 0, rankingList.get(2).getProductInfo().size() - 1, product.getId());

            product.setView_count(viewInfo == null ? 0 : viewInfo.getView_count());
            product.setOrdered_count(orderedInfo == null ? 0 : orderedInfo.getOrder_count());
            product.setShared_count(sharedInfo == null ? 0 : sharedInfo.getShares());
        }

    }


    private Ranking.ProductInfo binarySearch(ArrayList<Ranking.ProductInfo> arr, int first, int last, int key) {

        if (last >= first) {

            int mid = first + (last - first) / 2;

            if (arr.get(mid).getId() == key) {
                return arr.get(mid);
            }

            if (arr.get(mid).getId() > key) {
                return binarySearch(arr, first, mid - 1, key);//search in left subarray
            } else {
                return binarySearch(arr, mid + 1, last, key);//search in right subarray
            }
        }
        return null;
    }


    public boolean navigateToFragment(Fragment fragment, String addToBackStack) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);

            if (null != addToBackStack) {
                fragmentTransaction.addToBackStack(addToBackStack);
            }
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

}
