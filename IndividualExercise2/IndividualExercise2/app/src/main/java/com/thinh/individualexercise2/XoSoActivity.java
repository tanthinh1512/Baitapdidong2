package com.thinh.individualexercise2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by thonghuynh on 4/28/2017.
 */

public class XoSoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xoso);

        Toolbar toolbar_result = (Toolbar)findViewById(R.id.toolbar_result);
        setSupportActionBar(toolbar_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
