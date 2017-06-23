package com.android.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.model.Task;
import com.android.ui.BaseActivity;
import com.android.utils.Utils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p.androidtask.R;

public class DetailActivity extends BaseActivity implements DetailView {

    private static final String EXTRA_ID = "EXTRA_ID";

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.description)
    EditText description;
    @Bind(R.id.button_add)
    Button update;

    @Inject
    DetailPresenter mMyDetailPresenter;

    public static Intent getStartIntent(final Context context, final int isbn) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ID, isbn);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
        update.setText("Update");
    }

    @Override
    protected Object getModule() {
        return new DetailModule(getIntent().getExtras().getInt(EXTRA_ID));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMyDetailPresenter.setView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mMyDetailPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mMyDetailPresenter.closeRealm();
    }

    @Override
    public void showTaskDetails(final Task task) {
        name.setText(task.getName());
        description.setText(task.getDescription());
    }

    @OnClick(R.id.button_add)
    public void onAddClick() {
        mMyDetailPresenter.onUpdateClick(
                name.getText().toString(),
                description.getText().toString(),
                Utils.getCurrentDate());
    }


}
