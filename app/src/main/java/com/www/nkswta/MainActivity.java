package com.www.nkswta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener{
    FragmentHome home_fragment;
    FragmentSignIn sign_in_fragment;
    FragmentSignUp sign_up_fragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sign_in_fragment = new FragmentSignIn();
         sign_up_fragment = new FragmentSignUp();
         home_fragment = new FragmentHome();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        if (savedInstanceState == null) {
            selectFragment(FragmentSignIn.newInstance("param1","param2"));
        }
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                selectFragment(FragmentHome.newInstance("param1","param2"));
                return true;
            case R.id.reports:
                selectFragment(FragmentReports.newInstance("param1","param2"));
                return true;
            case R.id.notification:
                selectFragment(FragmentNotification.newInstance("param1","param2"));
                return true;
            // Handle other menu items as needed
        }
        return false;
    }

    private void selectFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }

    @Override
    public void navigateToFragmentHome() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, home_fragment)
                //.addToBackStack(null) // Optional: This enables the back button to go back to FragmentA
                .commit();
    }
    @Override
    public void navigateToFragmentSignUp() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, sign_up_fragment)
                .commit();
    }
    @Override
    public void navigateToFragmentSignIn() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, sign_in_fragment)
                .commit();
    }
}