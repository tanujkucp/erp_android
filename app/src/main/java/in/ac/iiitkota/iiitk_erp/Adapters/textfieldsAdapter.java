package in.ac.iiitkota.iiitk_erp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.ac.iiitkota.iiitk_erp.Models.AttendanceStatus;
import in.ac.iiitkota.iiitk_erp.Models.ItemIssued;
import in.ac.iiitkota.iiitk_erp.Models.TimeTable;
import in.ac.iiitkota.iiitk_erp.R;

public class textfieldsAdapter extends RecyclerView.Adapter<textfieldsAdapter.ViewHolder> {

    public ArrayList<TimeTable> TimeTablelist;
    public ArrayList<AttendanceStatus> StatusList;
    public ArrayList<ItemIssued> ItemList;
    public Boolean TimeTable,AttendanceStatus,item_issued;
    public Context context;
    public Drawable expandIcon, compressIcon;

    //textfieldAdapter constructor
    public textfieldsAdapter(Context context){
        this.context = context;
        TimeTable = false;
        AttendanceStatus = false;
        item_issued = false;
    }

    public void setTimeTableAdapter(ArrayList<TimeTable> list){
        TimeTable = true;
        TimeTablelist = list;
    }

    public void setAttendanceStatusAdapter(ArrayList<AttendanceStatus> list){
        AttendanceStatus = true;
        StatusList = list;
    }

    public void setItemIssuedAdapter(ArrayList<ItemIssued> list){
        item_issued = true;
        ItemList = list;
        expandIcon = context.getResources().getDrawable(R.drawable.ic_expand_more);
        compressIcon = context.getResources().getDrawable(R.drawable.ic_expand_less);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mT1,mT2,mT3;
        public ImageView mI1;
        public ViewHolder(View itemView) {
            super(itemView);
            mT1 = (TextView) itemView.findViewById(R.id.cv_t1);
            mT2 = (TextView) itemView.findViewById(R.id.cv_t2);
            mT3 = (TextView) itemView.findViewById(R.id.cv_t3);
            mI1 = (ImageView) itemView.findViewById(R.id.cv_icon);
            mI1.setVisibility(View.GONE);
            if(item_issued){
                mI1.setClickable(true);
                mI1.setVisibility(View.VISIBLE);
                mI1.setImageDrawable(expandIcon);
                mI1.setTag(R.drawable.ic_expand_more);

                mT1.setVisibility(View.VISIBLE);
                mT2.setVisibility(View.GONE);
                mT3.setVisibility(View.GONE);

                mI1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        Log.e("inside","listner");
                        Integer imageId = (Integer) v.getTag();
                        Log.e("id's",String.valueOf(imageId)+" "+String.valueOf(R.drawable.ic_expand_more));
                        if(imageId==R.drawable.ic_expand_more) {
                            mT2.setVisibility(View.VISIBLE);
                            mT3.setVisibility(View.VISIBLE);
                            mI1.setImageDrawable(compressIcon);
                            mI1.setTag(R.drawable.ic_expand_less);
                            Log.e("till here","good");
                        }
                        else if(imageId==R.drawable.ic_expand_less){
                            mT2.setVisibility(View.GONE);
                            mT3.setVisibility(View.GONE);
                            mI1.setImageDrawable(expandIcon);
                            mI1.setTag(R.drawable.ic_expand_more);
                            Log.e("till here","better");
                        }
                    }
                });
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_textfields,viewGroup,false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
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
        else if(AttendanceStatus){
            AttendanceStatus current = StatusList.get(i);
            viewHolder.mT1.setText(current.t1);
            viewHolder.mT2.setText(current.t2);
            viewHolder.mT3.setText(current.t3);
        }
        else if(item_issued){
            ItemIssued current = ItemList.get(i);
            viewHolder.mT1.setText(current.t1);
            viewHolder.mT2.setText(current.t2);
            viewHolder.mT3.setText(current.t3);
        }
    }

    public int getItemCount() {
        if(TimeTable) return TimeTablelist.size();
        else if(AttendanceStatus) return StatusList.size();
        else if(item_issued) return ItemList.size();
        else return 0;
    }

}
