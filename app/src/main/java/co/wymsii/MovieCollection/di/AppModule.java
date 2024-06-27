package co.wymsii.MovieCollection.di;

import android.content.Context;

import javax.inject.Singleton;

import co.wymsii.MovieCollection.data.MovieRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

public class AppModule {

//    @Module
//    @InstallIn(SingletonComponent.class) // for all singletons
//    public class AppModule {
//
//        @Provides
//        @Singleton
//        MovieRepository provideRepository(@ApplicationContext Context app) {
//            return new MovieRepository(app);
//        }
//    }


}
