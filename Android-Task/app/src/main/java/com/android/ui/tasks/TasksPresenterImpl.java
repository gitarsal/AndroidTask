package com.android.ui.tasks;

import com.android.model.realm.RealmService;
import com.android.ui.adapter.TaskListAdapter;

public class TasksPresenterImpl implements TasksPresenter {

    private final RealmService mRealmService;
    private TasksView mMyListView = new TasksView.EmptyMyListView();

    private boolean tasksWereShown = false;

    public TasksPresenterImpl(final RealmService realmService) {
        mRealmService = realmService;
    }

    @Override
    public void setView(final TasksView view) {
        mMyListView = view;
        showTasksIfNeeded();
    }

    private void showTasksIfNeeded() {
        if (!tasksWereShown) {
            mMyListView.showTasks(mRealmService.getAllTasks());
            tasksWereShown = true;
        }
    }

    @Override
    public void clearView() {
        mMyListView = new TasksView.EmptyMyListView();
    }

    @Override
    public void closeRealm() {
        mRealmService.closeRealm();
    }

    @Override
    public void onTaskClick(final int id) {
        mMyListView.showTaskDetailView(id);
    }

    @Override
    public void onDelete(int id, TaskListAdapter adapter) {
        mRealmService.deleteTask(id,adapter);
    }

    @Override
    public void onAddNewTaskClick() {
        mMyListView.showAddNewTaskView();
    }
}
