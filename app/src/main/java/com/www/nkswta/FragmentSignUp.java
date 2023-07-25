package com.www.nkswta;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FragmentSignUp extends Fragment {

    FragmentInteractionListener fragmentInteractionListener;
    FirebaseAuth firebaseAuth;
    EditText Name, Email, Password, ConfirmPassword;
    Button submit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSignUp() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentSignUp newInstance(String param1, String param2) {
        FragmentSignUp fragment = new FragmentSignUp();
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

        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        MainActivity mainActivity = (MainActivity) requireActivity();
        BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();
        // Hide the BottomNavigationView in this fragment
        bottomNavigationView.setVisibility(View.GONE);

        submit = view.findViewById(R.id.buttonSubmit);
        Name = view.findViewById(R.id.editTextName);
        Email = view.findViewById(R.id.editTextEmailAddress);
        Password = view.findViewById(R.id.editTextPassword);
        ConfirmPassword = view.findViewById(R.id.editTextConfirmPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentInteractionListener != null) {
                    firebaseAuth = FirebaseAuth.getInstance();
                    String email = Email.getText().toString();
                    String password = Password.getText().toString().trim();;
                    String confirmPassword = ConfirmPassword.getText().toString().trim();;


                    if (password.equals(confirmPassword)) {

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), task -> {
                                    if (task.isSuccessful()) {
                                        // User registration success
                                        Toast.makeText(getActivity(), "Signup Success.", Toast.LENGTH_SHORT).show();
                                        fragmentInteractionListener.navigateToFragmentSignIn();
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        // Do something with the user if needed
                                    } else {
                                        // User registration failed
                                        Toast.makeText(getActivity(), "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(getActivity(), "password mismatch.", Toast.LENGTH_SHORT).show();
                    }



                }
            }
        });

        return view;
    }
}