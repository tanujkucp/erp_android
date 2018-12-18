package in.ac.iiitkota.iiitk_erp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.Adapters.textfieldsAdapter;
import in.ac.iiitkota.iiitk_erp.Models.ItemIssued;
import in.ac.iiitkota.iiitk_erp.R;

public class ItemIssuedFragment extends Fragment {

    public ArrayList<ItemIssued> IssuedList;
    public RecyclerView mLibRecyclerView,mSportsRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.e("tag","atIssuedFragment");
        View v = inflater.inflate(R.layout.fragment_library_and_sports,container,false);

        mLibRecyclerView = (RecyclerView) v.findViewById(R.id.library_rv);
        mSportsRecyclerView = (RecyclerView) v.findViewById(R.id.sports_rv);

        fetchAndSetLibAdapter();
        fetchAndSetSportsAdapter();

        return v;
    }

    public void fetchAndSetLibAdapter(){
        IssuedList = new ArrayList<>();

        for(int i = 0;i<5;i++){
            ItemIssued current = new ItemIssued("LearnAndroid","15/01/2019","01/01/2019","xxxxxxxx");
            IssuedList.add(current);
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLibRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new textfieldsAdapter(getActivity());
        ((textfieldsAdapter) mAdapter).setItemIssuedAdapter(IssuedList);
        mLibRecyclerView.setAdapter(mAdapter);

        //((textfieldsAdapter) mAdapter).item_issued = false;
    }
    public void fetchAndSetSportsAdapter(){
        IssuedList = new ArrayList<>();

        for(int i = 0;i<5;i++){
            ItemIssued current = new ItemIssued("Chess","15/01/2019","01/01/2019","xxxxxxxx");
            IssuedList.add(current);
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mSportsRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new textfieldsAdapter(getActivity());
        ((textfieldsAdapter) mAdapter).setItemIssuedAdapter(IssuedList);
        mSportsRecyclerView.setAdapter(mAdapter);

        //((textfieldsAdapter) mAdapter).item_issued=false;
    }
}
