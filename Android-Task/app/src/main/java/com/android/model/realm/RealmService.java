package com.android.model.realm;

import com.android.model.Task;
import com.android.ui.adapter.TaskListAdapter;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {

    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    public RealmResults<Task> getAllTasks() {
        return mRealm.allObjects(Task.class);
    }

    public Task getTask(final int taskId) {
        return mRealm.where(Task.class).equalTo("id", taskId).findFirst();

    }

    public void updateTaskAsync(final String name, final String description, final int id, final String updatedon,
                                final OnTransactionCallback onTransactionCallback) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                Task task = realm.where(Task.class).equalTo("id", id).findFirst();
                task.setName(name);
                task.setDescription(description);
                task.setDateUpdated(updatedon);

            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                }
            }

            @Override
            public void onError(final Exception e) {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmError(e);
                }
            }
        });
    }

    public void addTaskAsync(final String name, final String description, final String createon, final String updatedon,
                             final OnTransactionCallback onTransactionCallback) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                Task task = realm.createObject(Task.class);
                task.setId(realm.allObjects(Task.class).size());
                task.setName(name);
                task.setDescription(description);
                task.setDateCreated(createon);
                task.setDateUpdated(updatedon);

            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                }
            }

            @Override
            public void onError(final Exception e) {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmError(e);
                }
            }
        });
    }


    private String[] splitAuthorName(final String author) {
        return author.split(" ");
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();

        void onRealmError(final Exception e);
    }

    public void deleteTask(final int id, final TaskListAdapter adapter) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(final Realm realm) {
                Task task = realm.where(Task.class).equalTo("id", id).findFirst();
                task.removeFromRealm();
            }
        }, new Realm.Transaction.Callback() {

            @Override
            public void onSuccess() {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(final Exception e) {

            }
        });

    }

}
