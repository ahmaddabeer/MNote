package com.example.mnote.di

import android.content.Context
import androidx.room.Room
import com.example.mnote.data.local.NoteDao
import com.example.mnote.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton

    fun provideDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {

        return Room.databaseBuilder(

            context,
            NoteDatabase::class.java,
            "note"
        ).fallbackToDestructiveMigration()
            .build()


    }

    @Provides
    fun provideNoteDao(
        database: NoteDatabase

    ): NoteDao {

        return database.noteDao()
    }
}