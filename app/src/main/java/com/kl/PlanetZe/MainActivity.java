package com.kl.PlanetZe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting the layout to activity_main.xml
        // "R" means "res"
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance("https://planetze-88-default-rtdb.firebaseio.com");
        DatabaseReference myRef = db.getReference("testDemo");

//        myRef.child("movies").setValue("B07 Demo!");

        // 查找 Sign In 按钮
        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 加载 SignInFragment
                loadFragment(new SignInFragment());
//                myRef.child("movies").setValue("B07 Demo!");
            }
        });

        // 查找 Register 按钮
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 加载 RegisterFragment
                loadFragment(new RegisterFragment());
                myRef.child("books").setValue("<ABCD>");
            }
        });

//        if (savedInstanceState == null) {
//            loadFragment(new HomeFragment());
//        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}