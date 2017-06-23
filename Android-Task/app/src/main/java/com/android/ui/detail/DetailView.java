package com.android.ui.detail;

import com.android.model.Task;

public interface DetailView {

    void showTaskDetails(Task task);
    void finish();

    class EmptyDetailView implements DetailView {

        @Override
        public void showTaskDetails(final Task task) {

        }

        @Override
        public void finish() {

        }
    }
}
