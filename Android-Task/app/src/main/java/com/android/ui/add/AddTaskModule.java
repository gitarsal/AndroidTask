package com.android.ui.add;

import com.android.ApplicationModule;
import com.android.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;

@Module(injects = AddTaskActivity.class, addsTo = ApplicationModule.class)
public class AddTaskModule {

    @Provides
    AddTaskPresenter provideAddTaskPresenter(final RealmService realmService) {
        return new AddTaskPresenterImpl(realmService);
    }
}
