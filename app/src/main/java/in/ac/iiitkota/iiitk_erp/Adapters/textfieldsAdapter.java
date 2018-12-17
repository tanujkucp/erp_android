package in.ac.iiitkota.iiitk_erp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.ac.iiitkota.iiitk_erp.Models.AttendanceStatus;
import in.ac.iiitkota.iiitk_erp.Models.TimeTable;
import in.ac.iiitkota.iiitk_erp.R;

public class textfieldsAdapter extends RecyclerView.Adapter<textfieldsAdapter.ViewHolder> {

    public ArrayList<TimeTable> TimeTablelist;
    public ArrayList<AttendanceStatus> StatusList;
    public Boolean TimeTable = false,AttendanceStatus = false;
    public Context context;

    //textfieldAdapter constructor
    public textfieldsAdapter(Context context){
        this.context = context;
    }

    public void setTimeTableAdapter(ArrayList<TimeTable> list){
        TimeTable = true;
        TimeTablelist = list;
    }

    public void setAttendanceStatusAdapter(ArrayList<AttendanceStatus> list){
        AttendanceStatus = true;
        StatusList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mT1,mT2,mT3;
        public ViewHolder(View itemView) {
            super(itemView);
            mT1 = (TextView) itemView.findViewById(R.id.cv_t1);
            mT2 = (TextView) itemView.findViewById(R.id.cv_t2);
            mT3 = (TextView) itemView.findViewById(R.id.cv_t3);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_textfields,viewGroup,false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if(TimeTable){
            TimeTable current = TimeTablelist.get(i);

            if(!current.getHoliday()) {
                viewHolder.mT1.setText(current.getTime());
                viewHolder.mT2.setText(current.getSubject());
                viewHolder.mT3.setText(current.getVenue());
            }else{
                viewHolder.mT2.setText(current.getHolidayMessage());
            }
        }
        if(AttendanceStatus){
            AttendanceStatus current = StatusList.get(i);
            viewHolder.mT1.setText(current.t1);
            viewHolder.mT2.setText(current.t2);
            viewHolder.mT3.setText(current.t3);
        }
    }

    public int getItemCount() {
        if(TimeTable) return TimeTablelist.size();
        else if(AttendanceStatus) return StatusList.size();
        else return 0;
    }

}
