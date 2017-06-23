package com.android.ui.add;

public interface AddTaskView {
    void finish();
    void showAddTaskError();

    class EmptyAddTaskView implements AddTaskView {

        @Override
        public void finish() {

        }

        @Override
        public void showAddTaskError() {

        }
    }
}
