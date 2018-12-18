package in.ac.iiitkota.iiitk_erp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ac.iiitkota.iiitk_erp.Models.TimeTable;
import in.ac.iiitkota.iiitk_erp.R;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.TimeTableViewHolder> {

    public Context context;
    public ArrayList<TimeTable> List;

    public class TimeTableViewHolder extends RecyclerView.ViewHolder{

        public TextView mTime,mSubject,mVenue;

        public TimeTableViewHolder(View itemView) {
            super(itemView);

            mTime = (TextView) itemView.findViewById(R.id.cv_time);
            mSubject = (TextView) itemView.findViewById(R.id.cv_subject);
            mVenue = (TextView) itemView.findViewById(R.id.cv_venue);

        }
    }

    public TimeTableAdapter(Context context,ArrayList<TimeTable> List){
        this.context = context;
        this.List = List;
    }

    @Override
    public TimeTableViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_time_table,viewGroup,false);
        return new TimeTableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeTableViewHolder timeTableViewHolder, int i) {
        TimeTable current = List.get(i);

        if(!current.getHoliday()) {
            timeTableViewHolder.mTime.setText(current.getTime());
            timeTableViewHolder.mSubject.setText(current.getSubject());
            timeTableViewHolder.mVenue.setText(current.getVenue());
        }else{
            timeTableViewHolder.mSubject.setText(current.getHolidayMessage());
        }
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

}
