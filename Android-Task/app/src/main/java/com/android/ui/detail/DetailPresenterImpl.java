package com.android.ui.detail;

import com.android.model.realm.RealmService;

public class DetailPresenterImpl implements DetailPresenter, RealmService.OnTransactionCallback {

    private final RealmService mRealmService;
    private final int mTaskId;
    private DetailView mMyDetailView = new DetailView.EmptyDetailView();

    public DetailPresenterImpl(final RealmService realmService, final int taskid) {
        mRealmService = realmService;
        mTaskId = taskid;
    }

    @Override
    public void setView(final DetailView view) {
        mMyDetailView = view;
        mMyDetailView.showTaskDetails(mRealmService.getTask(mTaskId));
    }

    @Override
    public void clearView() {
        mMyDetailView = new DetailView.EmptyDetailView();
    }

    @Override
    public void closeRealm() {
        mRealmService.closeRealm();
    }




    @Override
    public void onUpdateClick(String name, String description, String updateon) {
        mRealmService.updateTaskAsync(name, description, mTaskId, updateon, this);

    }

    @Override
    public void onRealmSuccess() {
        mMyDetailView.finish();
    }

    @Override
    public void onRealmError(Exception e) {

    }
}
