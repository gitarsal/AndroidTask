package com.android.ui.add;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.ui.BaseActivity;
import com.android.utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p.androidtask.R;

public class AddTaskActivity extends BaseActivity implements AddTaskView {

    @Bind(R.id.layout_container)
    LinearLayout mLayoutContainer;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.description)
    EditText description;
    @Inject
    AddTaskPresenter mAddTaskPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
    }

    @Override
    protected Object getModule() {
        return new AddTaskModule();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAddTaskPresenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAddTaskPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mAddTaskPresenter.closeRealm();
    }

    @OnClick(R.id.button_add)
    public void onAddClick() {
        mAddTaskPresenter.onAddClick(
                name.getText().toString(),
                description.getText().toString(),
                Utils.getCurrentDate(),
                getString(R.string.notupdatedyet));
    }

    @Override
    public void showAddTaskError() {
        Snackbar.make(mLayoutContainer, R.string.add_new_task_error, Snackbar.LENGTH_SHORT).show();
    }


}
