package com.ids.favoritemovie.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ids.favoritemovie.R;
import com.ids.favoritemovie.ui.mainfav.MainFavFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFavFragment.newInstance())
                    .commitNow();
        }
    }
}
