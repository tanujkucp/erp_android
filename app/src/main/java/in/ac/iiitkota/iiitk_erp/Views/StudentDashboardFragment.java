package in.ac.iiitkota.iiitk_erp.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.Adapters.textfieldsAdapter;
import in.ac.iiitkota.iiitk_erp.Models.AttendanceStatus;
import in.ac.iiitkota.iiitk_erp.R;
/*
This fragment is for student account;
This fragment will show attendance details and courses registered details for the registered student;
//todo Use of circular and bar graphs to show attendance of the user
*/
public class StudentDashboardFragment extends Fragment {

    ArrayList<AttendanceStatus> list;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_student_dashboard, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.status_recyclerView);

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        fetchAndSetAdapter();

        return v;
    }

    public void fetchAndSetAdapter(){
        progressDialog.dismiss();
        list = new ArrayList<>();

        //todo fetch and set data "from-database:to-list"
        for(int i=1; i<6; i++){
            String subjectCode = "CST-10"+String.valueOf(i);
            list.add(new AttendanceStatus(subjectCode,15,20,1));
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new textfieldsAdapter(getActivity());
        ((textfieldsAdapter) mAdapter).setAttendanceStatusAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }

}
/*  //update student attendance status at the time of submitting list with the help of student id
    .....
    "Users":{
        "user1_uniqueId(if_faculty)/college_id(if_student)":{
            "name":"something",
            "role":"faculty",
            ...
            ...
            "bioDesc":"something",
            "Subjects":{
                "1":{
                    "SubjectCode":"CST101",
                    "present":null,
                    "absent":null,
                    "leave":null,
                    "total":null
                },
                "2":{
                    "SubjectCode":"CST102",
                    "present":null,
                    "absent":null,
                    "leave":null,
                    "total":null
                },
            ...
        },
        "user2_uniqueId(if_faculty)/college_id(if_student)":{
            "name":"something",
            "role":"student",
            ...
            ...
            "bioDesc":"something",
            "Subjects":{
                "1":{
                    "SubjectCode":"CST101",
                    "present":15,
                    "absent":4,
                    "leave":1,
                    "total":20
                },
                "2":{
                    "SubjectCode":"CST101",
                    "present":15,
                    "absent":4,
                    "leave":1,
                    "total":20
                },....
        },....
    },....
*/