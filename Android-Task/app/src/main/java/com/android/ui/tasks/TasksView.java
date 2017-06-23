package com.android.ui.tasks;

import com.android.model.Task;

import io.realm.RealmResults;

public interface TasksView {

    void showTasks(RealmResults<Task> tasks);
    void showTaskDetailView(int id);
    void showAddNewTaskView();

    class EmptyMyListView implements TasksView {

        @Override
        public void showTasks(final RealmResults<Task> tasks) {

        }

        @Override
        public void showTaskDetailView(final int id) {
            
        }

        @Override
        public void showAddNewTaskView() {

        }
    }
}
