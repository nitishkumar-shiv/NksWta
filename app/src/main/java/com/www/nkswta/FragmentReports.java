package com.www.nkswta;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.www.nkswta.db.TaskInformationDataBase;
import com.www.nkswta.db.entity.TaskInfo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentReports#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentReports extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private TaskInformationDataBase taskInformationDataBase;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentReports() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentReports.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentReports newInstance(String param1, String param2) {
        FragmentReports fragment = new FragmentReports();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        PieChart pieChart = view.findViewById(R.id.pieChartView);
        BarChart barChart = view.findViewById(R.id.barChartView);

        taskInformationDataBase = Room.databaseBuilder(
                getContext(),
                TaskInformationDataBase.class,
                "TaskInfoDB").allowMainThreadQueries().build();
        taskInformationDataBase.getTaskInfoDAO().addTaskInfo(new TaskInfo("MT-64","Android","High","Bug",50));
        Log.d("tagdb", "onCreateView: "+taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo());
        getTaskReportBarChart(barChart);
        getTaskReportPieChart(pieChart);

        return view;
    }

    public  void getTaskReportBarChart(BarChart barChart){
        int[] data = {50, 80, 120, 40, 90,100,50,30,15,0};
        String[] labels = {"Label 1", "Label 2", "Label 3", "Label 4", "Label 5","m1","m2","m3","m4","m5"};

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            entries.add(new BarEntry(i, data[i]));
        }

        BarDataSet barDataSet = new BarDataSet(entries, "Data Set");
        barDataSet.setColors(Color.BLACK,Color.GREEN,Color.BLUE,Color.YELLOW,Color.RED,Color.CYAN);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setFitBars(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels)); // Set custom labels


        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(false); // Disable scaling if you only want horizontal scrolling
        barChart.setVisibleXRangeMaximum(6); // Number of visible labels at a time
        barChart.setPinchZoom(true);

        barChart.animateY(2000);
        barChart.getDescription().setEnabled(false);


    }

    public void getTaskReportPieChart(PieChart pieChart){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(40f, "TODO"));
        entries.add(new PieEntry(25f, "INPROGRESS"));
        entries.add(new PieEntry(35f, "D0NE"));

        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart Example");
        dataSet.setColors(Color.RED, Color.BLUE, Color.GREEN);

        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(15f);

        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);

        pieChart.setDrawCenterText(true); // Enable center text in the chart
        pieChart.setCenterText("TASK REPORT"); // Set the custom center text
        pieChart.setCenterTextColor(Color.BLUE); // Set the center text color
        pieChart.setCenterTextSize(20f); // Disable default center text in the chart

        pieChart.getDescription().setEnabled(false);
    }

}