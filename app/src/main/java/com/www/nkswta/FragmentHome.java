package com.www.nkswta;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FragmentHome extends Fragment {

    FragmentInteractionListener fragmentInteractionListener;
    RecyclerView recyclerViewFragmentHome;
    ModalClass[] MyListData;
    CustomAdaptor adaptor;
    SharedPreferences sharedPreferences;
    String userName;

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

        sharedPreferences = getActivity().getSharedPreferences("Nks_sharedPref", MODE_PRIVATE);
        userName = sharedPreferences.getString("name","");
        Log.d("userName", "onCreateView: "+userName);


        recyclerViewFragmentHome = view.findViewById(R.id.recyclerViewFragmentHome);
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

        adaptor = new CustomAdaptor(MyListData);
        recyclerViewFragmentHome.setHasFixedSize(true);
        recyclerViewFragmentHome.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewFragmentHome.setAdapter(adaptor);

        return view;
    }
}