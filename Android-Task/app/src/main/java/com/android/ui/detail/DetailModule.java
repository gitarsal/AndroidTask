package com.android.ui.detail;

import com.android.ApplicationModule;
import com.android.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;

@Module(injects = DetailActivity.class, addsTo = ApplicationModule.class)
public class DetailModule {

    private final int mTaskId;

    public DetailModule(final int taskId) {
        mTaskId = taskId;
    }

    @Provides
    DetailPresenter provideMyDetailPresenter(final RealmService realmService) {
        return new DetailPresenterImpl(realmService, mTaskId);
    }
}
