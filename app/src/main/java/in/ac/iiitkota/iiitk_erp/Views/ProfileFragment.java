package in.ac.iiitkota.iiitk_erp.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import in.ac.iiitkota.iiitk_erp.Adapters.textfieldsAdapter;
import in.ac.iiitkota.iiitk_erp.Models.TimeTable;
import in.ac.iiitkota.iiitk_erp.R;
/*
This fragment will show the user profile to both faculty and student user
//todo make cool UI and then show data by using stored shared preferences
//eg. UserData.getInstance(context).getUser();
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    //Sample JSON file is at the end.

    ArrayList<TimeTable> List;
    String currentDay,nextWorkingDay;

    TextView mName,mBioDesc,mRole,mDay;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        mName=(TextView)v.findViewById(R.id.profile_name);
        mBioDesc=(TextView)v.findViewById(R.id.profile_bio_desc);
        mRole=(TextView)v.findViewById(R.id.profile_role);
        mDay=(TextView)v.findViewById(R.id.profile_currentDay);
        mRecyclerView=(RecyclerView)v.findViewById(R.id.profile_tt_rv);

        //todo fetch user info and set here
        mName.setText("Shaktiraj Daudra");
        mRole.setText("Student");
        mBioDesc.setText("This is sample description, I hope you liked it, Edit Button is working");

        currentDay = getCurrentDay();
        nextWorkingDay = getNextWorkingDay();
        mDay.setText(currentDay);

        /* recyclerView by-default give today's time-table
            One can select next day time-table or complete timetable from drop-down menu
         */
        //todo fetch data of timetable and pollute List
        fetchAndSetTimeTableList();

        v.findViewById(R.id.profile_logout).setOnClickListener(this);
        v.findViewById(R.id.profile_edit_bio).setOnClickListener(this);
        v.findViewById(R.id.profile_list_tt).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        int i=v.getId();
        if(i==R.id.profile_logout){
            //this button take care of log-out mechanism

            Intent intent  = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        if(i==R.id.profile_edit_bio){
            //this button take care of edit bio feature

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater=getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.dialog_edit_bio,null);
            final EditText mBioEdit = (EditText)view.findViewById(R.id.dialog_edit_bio);

            builder.setView(view).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String bioEdit = mBioEdit.getText().toString();

                    //todo update bioDesc of user with bioEdit
                    mBioDesc.setText(bioEdit);
                }
            });
            builder.setNegativeButton("Cancel",null);
            AlertDialog dialog = builder.create();
            dialog.show();

        }

        if(i==R.id.profile_list_tt){
            //this button take care of selecting desired day timetable

            PopupMenu popup = new PopupMenu(getActivity(),v);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int i = item.getItemId();
                    if(i==R.id.today_tt){
                        mDay.setText(currentDay);
                        fetchAndSetTimeTableList();
                        return true;
                    }
                    else if(i==R.id.next_tt){
                        mDay.setText(nextWorkingDay);
                        fetchAndSet_NextDay_TimeTableList();
                        return true;
                    }
                    else if(i==R.id.complete_tt){
                        //todo add complete timetable
                        mDay.setText("//todo add full timeTable");
                        return true;
                    }
                    else return false;
                }
            });
            popup.inflate(R.menu.time_table_day_menu);
            popup.show();
        }
    }

    public void fetchAndSetTimeTableList(){

        List = new ArrayList<>();

        if(currentDay!="Saturday"&&currentDay!="Sunday") {
            //todo fetch it from database
            for (int i = 0; i < 5; i++) {
                TimeTable current = new TimeTable();
                current.setFromTime("10:00");
                current.setToTime("10:55");
                current.setSubject("CST10" + String.valueOf(i));
                current.setVenue("VLTC");

                List.add(current);

                //set LayoutManager and Adapter
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);

                mAdapter = new textfieldsAdapter(getActivity());
                ((textfieldsAdapter) mAdapter).setTimeTableAdapter(List);
                mRecyclerView.setAdapter(mAdapter);
            }
        }else{
            TimeTable current = new TimeTable(true);

            List.add(current);

            //set LayoutManager and Adapter
            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new textfieldsAdapter(getActivity());
            ((textfieldsAdapter) mAdapter).setTimeTableAdapter(List);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void fetchAndSet_NextDay_TimeTableList(){

        List = new ArrayList<>();

        //todo fetch it from database
        for(int i=0;i<5;i++){
            TimeTable current = new TimeTable();
            current.setFromTime("10:00");
            current.setToTime("10:55");
            current.setSubject("CST10"+String.valueOf(i));
            current.setVenue("nextDayTimeTable");

            List.add(current);

            //set LayoutManager and Adapter
            mLayoutManager= new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new textfieldsAdapter(getActivity());
            ((textfieldsAdapter) mAdapter).setTimeTableAdapter(List);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public String getCurrentDay(){
        String Days[] = {"Sunday","Monday","Tuesday","Wednesday",
                            "Thursday","Friday","Saturday"};
        Calendar calendar = Calendar.getInstance();
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK)-1;

        return Days[dayIndex];
    }

    public String getNextWorkingDay(){
        String Days[] = {"Sunday","Monday","Tuesday","Wednesday",
                            "Thursday","Friday","Saturday"};
        Calendar calendar = Calendar.getInstance();
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);

        if(dayIndex<6) return Days[dayIndex];
        else return Days[1];
    }

}

/*
    Sample JSON Query:-

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
    }
    ...
    ...
    "TimeTable":{
        "Monday":{
            "subjectCode1":{
                "fromTime":"x",
                "toTime":"y",
                "Venue":"z"
            },
            "subjectCode2":{
                "fromTime":"x",
                "toTime":"y",
                "Venue":"z"
            },....
        },
        "Tuesday":{
            "subjectCode1":{
                "fromTime":"x",
                "toTime":"y",
                "Venue":"z"
            },
            "subjectCode2":{
                "fromTime":"x",
                "toTime":"y",
                "Venue":"z"
            },....
        },....
    }

    //first we need to take subjectCode of faculty/student
    //after that set recyclerView according to day
*/