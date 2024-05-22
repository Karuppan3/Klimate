package io.bash_psk.klimate.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.bash_psk.klimate.data.repository.EmptyLocationImpl
import io.bash_psk.klimate.data.repository.EmptyWeatherImpl
import io.bash_psk.klimate.domain.repository.EmptyLocation
import io.bash_psk.klimate.domain.repository.EmptyWeather
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        application: Application
    ) : FusedLocationProviderClient {

        return LocationServices.getFusedLocationProviderClient(application)
    }

    @Provides
    @Singleton
    fun provideEmptyLocation(
        application: Application,
        fusedLocationProviderClient: FusedLocationProviderClient
    ) : EmptyLocation {

        return EmptyLocationImpl(
            application = application,
            fusedLocationProviderClient = fusedLocationProviderClient
        )
    }

    @Provides
    @Singleton
    fun provideEmptyWeather(
        application: Application
    ) : EmptyWeather {

        return EmptyWeatherImpl(application = application)
    }
}