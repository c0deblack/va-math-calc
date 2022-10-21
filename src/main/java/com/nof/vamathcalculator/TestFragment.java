package com.nof.vamathcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.os.AsyncTask;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SQLiteOpenHelper db_helper;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // This method is passed a reference of the parent activity, use it to get the context
    // for the VAMathDBHelper which is extended from SQLiteOpenHelper
    @Override
    public void onAttach(Activity activity) {
       super.onAttach(activity);
       db_helper = new VAMathDBHelper(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    // Wait until the fragment is created before getting references to views inside of it
    @Override
    public void onStart() {
        super.onStart();

        // Use the getView() method within a fragment to get a reference to its' root element

        new QueryDB().execute(VARates.Basic.Dependent_Status.Alone_No_Depends.getStatus());
    }

    private class QueryDB extends AsyncTask<String, Void, Double> {

        protected Double doInBackground(String... status) {
            try {
                SQLiteDatabase db = db_helper.getReadableDatabase();
                Cursor cursor = db.query(
                        VAMathDBHelper.BASIC_TABLE_NAME,
                        null,
                        //VAMathDBHelper.DEPEND_STATUS_COLUMN_NAME + " = ?",
                        //new String[]{status[0]},
                        null,
                        null,
                        null,
                        null,
                        null);

                if (cursor.moveToFirst()) {
                    // skip the first two columns (ID and dependency status)
                    double query_result = cursor.getDouble(2);
                    cursor.close();
                    db.close();
                    //return (double) cursor.getColumnCount();
                    return query_result;
                } else {
                    cursor.close();
                    db.close();
                    return 0.0;
                }

            } catch (SQLiteException e) {
                return 0.0;
            }
        }

        protected void onPostExecute(Double response) {
            if (response > 0.0) {
                View root_view = getView();
                TextView text_view;

                if (root_view != null) {
                    text_view = root_view.findViewById(R.id.test_fragment_text);
                    text_view.setText(Double.toString(response));
                }
            }
        }

    }
}