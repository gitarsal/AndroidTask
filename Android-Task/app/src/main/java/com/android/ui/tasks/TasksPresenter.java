package com.android.ui.tasks;

import com.android.ui.BasePresenter;
import com.android.ui.adapter.TaskListAdapter;

public interface TasksPresenter extends BasePresenter<TasksView> {
    void onTaskClick(int id);

    void onDelete(int id, TaskListAdapter adapter);

    void onAddNewTaskClick();
}
