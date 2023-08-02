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
import java.util.List;


public class FragmentReports extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private TaskInformationDataBase taskInformationDataBase;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentReports() {
        // Required empty public constructor
    }


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
        getTaskReportBarChart(barChart);
        getTaskReportPieChart(pieChart);

        return view;
    }

    public  void getTaskReportBarChart(BarChart barChart){
        int length = taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo().size();
        int[] data = new int[length];
        String[] labels = new String[length];

        for(int i=0;i<length;i++) {
            data[i] = taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo().get(i).getProgress();
            labels[i] = taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo().get(i).getTaskID();
        }

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
        int length = taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo().size();
        int[] data = new int[length];

        for(int i=0;i<length;i++) {
            data[i] = taskInformationDataBase.getTaskInfoDAO().getAllTasksInfo().get(i).getProgress();
        }

        Float task_in_todo=0f, task_in_progress=0f, task_in_done=0f;
        for(int i=0;i<length;i++) {
            if(data[i]==0){
                task_in_todo++;
            }
            else if (data[i]==100) {
                task_in_done++;
            }
            else {
                task_in_progress++;
            }
        }
        task_in_done = (float)(task_in_done/length)*100;
        task_in_todo = (float)(task_in_todo/length)*100;
        task_in_progress = (float)(task_in_progress/length)*100;
        Log.d("TAG2", "getTaskReportPieChart: "+task_in_todo+" "+task_in_done+" "+task_in_progress);

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(task_in_todo, "TODO"));
        entries.add(new PieEntry(task_in_progress, "INPROGRESS"));
        entries.add(new PieEntry(task_in_done, "D0NE"));

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