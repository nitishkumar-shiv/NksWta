package com.www.nkswta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentInteractionListener{
    FragmentHome home_fragment;
    FragmentSignIn sign_in_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sign_in_fragment = new FragmentSignIn();
         home_fragment = new FragmentHome();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, sign_in_fragment)
                .commit();
    }

    @Override
    public void navigateToFragmentHome() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, home_fragment)
                //.addToBackStack(null) // Optional: This enables the back button to go back to FragmentA
                .commit();
    }
}