package in.ac.iiitkota.iiitk_erp.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.iiitkota.iiitk_erp.Adapters.FacultyCoursesAdapter;
import in.ac.iiitkota.iiitk_erp.Utilities.DBHelper;
import in.ac.iiitkota.iiitk_erp.Utilities.MyToast;
import in.ac.iiitkota.iiitk_erp.R;
/*
This fragment is for faculty user;
This fragment will show the courses a particular faculty teaches with full details;
Every taught course will have an option to take attendance poll for all the students registered in that course.
//todo Make an API to fetch courses related to a faculty
 */
public class FacultyDashboardFragment extends Fragment {

    RecyclerView recyclerCourses;
    DBHelper dbHelper;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_faculty_dashboard, container, false);

        recyclerCourses = v.findViewById(R.id.courses);
        recyclerCourses.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching courses...");
        progressDialog.show();
        //fetch the courses for the faculty
        fetchCourses();

        //todo attach to the FacultyCoursesAdapter
        //populate adapter with the data
        int arr[] = {1, 2, 3, 4};
        recyclerCourses.setAdapter(new FacultyCoursesAdapter(getActivity(), arr));

        dbHelper = new DBHelper(getActivity(), null, 1);
        findBackup();

        return v;
    }

    public void fetchCourses() {
        //todo call an API with the faculty ID to fetch the courses taught by him/her

        //todo resolve the result received

        progressDialog.dismiss();
    }

    public void findBackup() {
        int count = dbHelper.getCount();
        //todo fetch course id and faculty username from sharedPreferences
        if (count > 0) {
            //todo show the backup found card and options to clear or continue
            new MyToast(getActivity(), "Backup found : " + count + " entries").show();
        }
    }


}
