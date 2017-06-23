package com.android.ui.tasks;

import com.android.ApplicationModule;
import com.android.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;

@Module(injects = TasksActivity.class, addsTo = ApplicationModule.class)
public class TasksModule {

    @Provides
    TasksPresenter provideMyListPresenter(final RealmService realmService) {
        return new TasksPresenterImpl(realmService);
    }
}
