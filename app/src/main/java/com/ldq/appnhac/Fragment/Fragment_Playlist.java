package com.ldq.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ldq.appnhac.Activity.DanhSachBaiHatActivity;
import com.ldq.appnhac.Activity.DanhSachPlayListActivity;
import com.ldq.appnhac.Adapter.PLaylistAdapter;
import com.ldq.appnhac.Model.Playlist;
import com.ldq.appnhac.R;
import com.ldq.appnhac.Server.APIServer;
import com.ldq.appnhac.Server.Dataserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {
    View view;
    ListView lvPlaylist;
    TextView txttitleplaylist,txtviewxemthemplaylist;
    PLaylistAdapter pLaylistAdapter;
    ArrayList<Playlist> arrayPlaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_playlist,container,false);


        lvPlaylist = (ListView) view.findViewById(R.id.listviewPlaylist);
        txttitleplaylist=(TextView) view.findViewById(R.id.textViewtitleplaylist);
        txtviewxemthemplaylist=(TextView) view.findViewById(R.id.textviewviewmoreplaylist);
        txtviewxemthemplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), DanhSachPlayListActivity.class);
                startActivity(intent);
            }
        });

        GetData();
        return view;
    }
    private void GetData(){
        Dataserver dataserver = APIServer.getServer();
        Call<List<Playlist>> callback = dataserver.GetPlaylistcurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {


                arrayPlaylist = (ArrayList<Playlist>) response.body();
                pLaylistAdapter= new PLaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayPlaylist);
                lvPlaylist.setAdapter(pLaylistAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);


                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist",arrayPlaylist.get(position));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
