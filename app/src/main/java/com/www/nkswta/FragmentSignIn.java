package com.www.nkswta;

import static android.content.Context.MODE_PRIVATE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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

public class FragmentSignIn extends Fragment {

    private FragmentInteractionListener fragmentInteractionListener;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSignIn() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentSignIn newInstance(String param1, String param2) {
        FragmentSignIn fragment = new FragmentSignIn();
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
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        MainActivity mainActivity = (MainActivity) requireActivity();
        BottomNavigationView bottomNavigationView = mainActivity.getBottomNavigationView();

        // Hide the BottomNavigationView in this fragment
        bottomNavigationView.setVisibility(View.GONE);

        Button login = view.findViewById(R.id.buttonLogIn);
        Button signUp = view.findViewById(R.id.buttonSignUp);
        EditText Email = view.findViewById(R.id.editTextTextEmailAddress);
        EditText Password = view.findViewById(R.id.editTextTextPassword);

        createNotificationChannel();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentInteractionListener != null) {

                    firebaseAuth = FirebaseAuth.getInstance();
                    String email = Email.getText().toString() ;
                    String password = Password.getText().toString();

                    if(!email.isEmpty() &&  !password.isEmpty()) {

                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), task -> {
                                    if (task.isSuccessful()) {

                                        sharedPreferences = getActivity().getSharedPreferences("Nks_sharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("logIn",true);
                                        editor.commit();
                                        onLoginSuccess();
                                        Toast.makeText(getActivity(), "Login successfully.", Toast.LENGTH_SHORT).show();
                                        fragmentInteractionListener.navigateToFragmentHome();
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        // Do something with the user if needed
                                    } else {
                                        Toast.makeText(getActivity(), "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(getActivity(), "Email and Password should not be empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentInteractionListener != null) {
                    fragmentInteractionListener.navigateToFragmentSignUp();
                }
            }
        });
        return view;
    }
    // Create the notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "default_channel";
            String channelName = "Default Channel";
            String channelDescription = "Default Channel for app notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    // This method is called when the login is successful
    private void onLoginSuccess() {
        // Create and show the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "default_channel")
                .setSmallIcon(R.drawable.ic_baseline_add_task_24)
                .setContentTitle("Login Successful")
                .setContentText("You have successfully logged in.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        notificationManager.notify(/*notificationId*/ 1, builder.build());
    }


}