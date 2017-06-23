package com.android.ui.add;

import com.android.ui.BasePresenter;

public interface AddTaskPresenter extends BasePresenter<AddTaskView> {
    void onAddClick(String name, String description, String createon, final String updateon);
}
