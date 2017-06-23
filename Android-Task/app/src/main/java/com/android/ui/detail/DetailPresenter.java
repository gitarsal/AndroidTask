package com.android.ui.detail;

import com.android.ui.BasePresenter;

public interface DetailPresenter extends BasePresenter<DetailView> {
    void onUpdateClick(String name, String description, final String updateon);
}
