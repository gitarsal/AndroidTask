package com.android;

import com.android.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(injects = TasksApplication.class, library = true)
public class ApplicationModule {

    public ApplicationModule() {}

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}
