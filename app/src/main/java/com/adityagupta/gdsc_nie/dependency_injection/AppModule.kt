package com.adityagupta.gdsc_nie.dependency_injection

import com.adityagupta.gdsc_nie.data.repository.EventDetailsRepositoryImpl
import com.adityagupta.gdsc_nie.data.repository.HomeFragmentRepositoryImpl
import com.adityagupta.gdsc_nie.data.repository.PastEventsFragmentRepositoryImpl
import com.adityagupta.gdsc_nie.domain.repository.EventDetailsRepository
import com.adityagupta.gdsc_nie.domain.repository.HomeFragmentRepository
import com.adityagupta.gdsc_nie.domain.repository.PastEventsRepository
import com.adityagupta.gdsc_nie.presentation.main.home.HomeFragment
import com.bumptech.glide.annotation.GlideModule
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPastEventsRepository(): PastEventsRepository{
        return PastEventsFragmentRepositoryImpl(providesFirebaseDatabaseReference())
    }

    @Provides
    @Singleton
    fun providesHomeFragmentRepository(): HomeFragmentRepository{
        return HomeFragmentRepositoryImpl(providesFirebaseDatabaseReference())
    }

    @Provides
    @Singleton
    fun providesEventDetailsFragmentRepository(): EventDetailsRepository{
        return EventDetailsRepositoryImpl(providesFirebaseDatabaseReference())
    }

    @Provides
    @Singleton
    fun providesFirebaseDatabaseReference(): DatabaseReference{
        return FirebaseDatabase.getInstance().reference
    }


}