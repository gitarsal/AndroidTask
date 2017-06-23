package com.android.ui.add;

import com.android.model.realm.RealmService;

public class AddTaskPresenterImpl implements AddTaskPresenter, RealmService.OnTransactionCallback {

    private final RealmService mRealmService;
    private AddTaskView mAddTaskView = new AddTaskView.EmptyAddTaskView();

    public AddTaskPresenterImpl(final RealmService realmService) {
        mRealmService = realmService;
    }

    @Override
    public void setView(final AddTaskView view) {
        mAddTaskView = view;
    }

    @Override
    public void clearView() {
        mAddTaskView = new AddTaskView.EmptyAddTaskView();
    }

    @Override
    public void closeRealm() {
        mRealmService.closeRealm();
    }

    @Override
    public void onAddClick(final String title, final String author, final String isbn, final String publisher) {
        mRealmService.addTaskAsync(title, author, isbn, publisher, this);
    }

    @Override
    public void onRealmSuccess() {
        mAddTaskView.finish();
    }

    @Override
    public void onRealmError(final Exception e) {
        e.printStackTrace();
        mAddTaskView.showAddTaskError();
    }
}
