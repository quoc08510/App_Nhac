package com.ldq.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldq.appnhac.Adapter.Adapter_Search_Bai_Hat;
import com.ldq.appnhac.Model.BaiHat;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Internal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbarsearchbaihat;
    RecyclerView recyclerviewsearchbaihat;
    TextView txtkocodulieu;
    Adapter_Search_Bai_Hat adapter_search_bai_hat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment_tim_kiem,container,false);
       toolbarsearchbaihat = view.findViewById(R.id.toolbarsreachbaihat);
       recyclerviewsearchbaihat = view.findViewById(R.id.recyclerviewsearchbaihat);
       txtkocodulieu= view.findViewById(R.id.textviewkhongcogi);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarsearchbaihat);
        toolbarsearchbaihat.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.itemmenusearch);
        SearchView searchview = (SearchView) menuItem.getActionView();
        searchview.setMaxWidth(Integer.MAX_VALUE);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTuKhoaBaiHat(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void searchTuKhoaBaiHat(String query){
        Dataserver dataserver = APIServer.getServer();
        Call<List<BaiHat>> callack = dataserver.getTimKiem(query);
        callack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> arraybaihat = (ArrayList<BaiHat>) response.body();
                if (arraybaihat.size()>0){
                    adapter_search_bai_hat = new Adapter_Search_Bai_Hat(getActivity(),arraybaihat);
                    LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                    recyclerviewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerviewsearchbaihat.setAdapter(adapter_search_bai_hat);
                    txtkocodulieu.setVisibility(View.GONE);
                    recyclerviewsearchbaihat.setVisibility(View.VISIBLE);
                }else{
                    txtkocodulieu.setVisibility(View.VISIBLE);
                    recyclerviewsearchbaihat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
