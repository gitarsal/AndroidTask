package com.android.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.TasksApplication;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TasksApplication.injectModules(this, getModule());
    }

    @Override
    protected void onDestroy() {
        closeRealm();
        super.onDestroy();
    }

    protected abstract Object getModule();
    protected abstract void closeRealm();
}
