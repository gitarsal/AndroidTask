package com.android.ui.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.model.Task;
import com.android.ui.BaseActivity;
import com.android.ui.adapter.TaskListAdapter;
import com.android.ui.add.AddTaskActivity;
import com.android.ui.detail.DetailActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmResults;
import p.androidtask.R;

public class TasksActivity extends BaseActivity implements TasksView, TaskListAdapter.OnTaskClickListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    TasksPresenter mTasksPresenter;

    private TaskListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initList();
    }

    @Override
    protected Object getModule() {
        return new TasksModule();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initList() {
        mAdapter = new TaskListAdapter();
        mAdapter.setOnTaskClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTasksPresenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTasksPresenter.clearView();
    }

    @Override
    protected void closeRealm() {
        mTasksPresenter.closeRealm();
    }

    @Override
    public void showTasks(final RealmResults<Task> tasks) {
        mAdapter.setTasks(tasks);
    }

    @Override
    public void onTaskClick(final int id) {
        mTasksPresenter.onTaskClick(id);
    }

    @Override
    public void delete(int id) {
        mTasksPresenter.onDelete(id, mAdapter);
    }

    @OnClick(R.id.fab)
    public void onAddNewTaskClick() {
        mTasksPresenter.onAddNewTaskClick();
    }

    @Override
    public void showTaskDetailView(final int id) {
        startActivity(DetailActivity.getStartIntent(this, id));
    }

    @Override
    public void showAddNewTaskView() {
        startActivity(new Intent(this, AddTaskActivity.class));
    }
}
