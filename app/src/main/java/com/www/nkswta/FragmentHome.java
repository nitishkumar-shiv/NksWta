package com.www.nkswta;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.www.nkswta.db.TaskInfoDAO;
import com.www.nkswta.db.TaskInformationDataBase;
import com.www.nkswta.db.entity.TaskInfo;

import java.util.ArrayList;


public class FragmentHome extends Fragment {

    FragmentInteractionListener fragmentInteractionListener;
    RecyclerView recyclerViewFragmentHome;
    TextView user_name,editableModalTitleText,editableModalCardPercentage,editableCardLocationText;
    EditText editableModalEditTitleText,editableModalPriorityEditText,editableModalTypeEditText;
    Button editableCardConfirmButton;
    SeekBar editableCardSeekBar;
    ModalClass[] MyListData;
    CustomAdaptor adaptor;
    SharedPreferences sharedPreferences;
    String userName;
    String taskID, taskTitle, priority, taskType, location;
    int progress;
    TaskInformationDataBase taskInformationDataBase;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof FragmentInteractionListener) {
            fragmentInteractionListener = (FragmentInteractionListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().toString() + " must implement FragmentInteractionListener");
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity mainActivity = (MainActivity) requireActivity();
        BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();

        // Hide the BottomNavigationView in this fragment
        bottomNavigationView.setVisibility(View.VISIBLE);
        recyclerViewFragmentHome = view.findViewById(R.id.recyclerViewFragmentHome);
        user_name = view.findViewById(R.id.header_userName);

        sharedPreferences = getActivity().getSharedPreferences("Nks_sharedPref", MODE_PRIVATE);
        userName = sharedPreferences.getString("name","");
        if(!userName.isEmpty()) {
            user_name.setText("Hi"+" "+userName+"!");
        }

        MyListData = new ModalClass[]{
                new ModalClass("MT-64","Android","High","Bug",50),
                new ModalClass("MT-65","Android","High","Bug",10),
                new ModalClass("MT-66","Android","High","Bug",60),
                new ModalClass("MT-67","Android","High","Bug",70),
                new ModalClass("MT-68","Android","High","Bug",80),
                new ModalClass("MT-69","Android","High","Bug",90),
                new ModalClass("MT-70","Android","High","Bug",30),
                new ModalClass("MT-71","Android","High","Bug",40),
                new ModalClass("MT-72","Android","High","Bug",60),
                new ModalClass("MT-73","Android","High","Bug",50),
                new ModalClass("MT-64","Android","High","Bug",50),
                new ModalClass("MT-65","Android","High","Bug",10),
                new ModalClass("MT-66","Android","High","Bug",60),
                new ModalClass("MT-67","Android","High","Bug",70),
                new ModalClass("MT-68","Android","High","Bug",80),
                new ModalClass("MT-69","Android","High","Bug",90),
                new ModalClass("MT-70","Android","High","Bug",30),
                new ModalClass("MT-71","Android","High","Bug",40),
                new ModalClass("MT-72","Android","High","Bug",60),
                new ModalClass("MT-73","Android","High","Bug",50),
                new ModalClass("MT-64","Android","High","Bug",50),
                new ModalClass("MT-65","Android","High","Bug",10),
                new ModalClass("MT-66","Android","High","Bug",60),
                new ModalClass("MT-67","Android","High","Bug",70),
                new ModalClass("MT-68","Android","High","Bug",80),
                new ModalClass("MT-69","Android","High","Bug",90),
                new ModalClass("MT-70","Android","High","Bug",30),
                new ModalClass("MT-71","Android","High","Bug",40),
                new ModalClass("MT-72","Android","High","Bug",60),
                new ModalClass("MT-73","Android","High","Bug",50),
        };

        adaptor = new CustomAdaptor(MyListData, new CustomAdaptor.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                    // Handle the click event for each card item here
                Log.d("itemClick", "onItemClick: ");
                Toast.makeText(getActivity(), "Clicked on item " + (position + 1), Toast.LENGTH_SHORT).show();
                showAlertDialogButtonClicked(MyListData[position]);
            }
        });
        recyclerViewFragmentHome.setHasFixedSize(true);
        recyclerViewFragmentHome.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewFragmentHome.setAdapter(adaptor);

        return view;
    }
    public void showAlertDialogButtonClicked(ModalClass myListDatum) {
        // Create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View customLayout = getLayoutInflater().inflate(R.layout.card_editable_modal, null);
        builder.setView(customLayout);
        AlertDialog dialog = builder.create();

        editableModalTitleText = customLayout.findViewById(R.id.editableModalTitleText);
        editableModalEditTitleText = customLayout.findViewById(R.id.editableModalEditTitleText);
        editableModalPriorityEditText = customLayout.findViewById(R.id.editableModalPriorityEditText);
        editableModalTypeEditText = customLayout.findViewById(R.id.editableModalTypeEditText);
        editableCardSeekBar = customLayout.findViewById(R.id.editableCardSeekBar);
        editableModalCardPercentage = customLayout.findViewById(R.id.editableModalCardPercentage);
        editableCardLocationText = customLayout.findViewById(R.id.editableCardLocationText);
        editableCardConfirmButton = customLayout.findViewById(R.id.editableCardConfirmButton);

        editableModalTitleText.setText(myListDatum.getTaskID());
        editableModalEditTitleText.setText(myListDatum.getTaskTitle());
        editableModalPriorityEditText.setText(myListDatum.getPriority());
        editableModalTypeEditText.setText(myListDatum.getTaskType());
        editableModalCardPercentage.setText(Integer.toString(myListDatum.getProgress())+"%");
        editableCardSeekBar.setProgress(myListDatum.getProgress());

        editableCardSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                editableModalCardPercentage.setText(Integer.toString(i)+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        editableCardConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskID = editableModalTitleText.getText().toString();
                taskTitle = editableModalEditTitleText.getText().toString();
                priority = editableModalPriorityEditText.getText().toString();
                taskType = editableModalTypeEditText.getText().toString();
                progress = editableCardSeekBar.getProgress();
                location = editableCardLocationText.getText().toString();
                Log.d("info", "onClick: "+taskID+taskTitle+priority+taskType+progress+location);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}