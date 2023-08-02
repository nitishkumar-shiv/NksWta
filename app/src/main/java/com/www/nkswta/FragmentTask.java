package com.www.nkswta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.www.nkswta.db.TaskInformationDataBase;
import com.www.nkswta.db.entity.TaskInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    EditText taskIDET, taskTitleET, priorityET, taskTypeET, progressET;
    Button createButton;
    String taskID, taskTitle, priority, taskType;
    int progress;
    private TaskInformationDataBase taskInformationDataBase;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTask() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentTask newInstance(String param1, String param2) {
        FragmentTask fragment = new FragmentTask();
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
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        taskIDET = view.findViewById(R.id.fragmentTaskTaskID);
        taskTitleET = view.findViewById(R.id.fragmentTaskTitle);
        taskTypeET =  view.findViewById(R.id.fragmentTaskType);
        priorityET = view.findViewById(R.id.fragmentTaskPriority);
        progressET = view.findViewById(R.id.fragmentTaskPercentage);
        createButton = view.findViewById(R.id.fragmentTaskCrete);

        taskInformationDataBase = Room.databaseBuilder(
                getContext(),
                TaskInformationDataBase.class,
                "TaskInfoDB"
        ).allowMainThreadQueries().build();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskID = taskIDET.getText().toString();
                taskTitle = taskTitleET.getText().toString();
                taskType = taskTypeET.getText().toString();
                priority = priorityET.getText().toString();
                progress = Integer.parseInt(progressET.getText().toString());

                taskInformationDataBase.getTaskInfoDAO().addTaskInfo(new TaskInfo(taskID,taskTitle,taskType,priority,progress));
                Toast.makeText(getActivity(),"Successfully added to database", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}