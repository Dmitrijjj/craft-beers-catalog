package com.dimidroid.beerscatalog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dimidroid.beerscatalog.models.BeerResponseItem
import com.dimidroid.beerscatalog.util.Constants.Companion.DATABASE_NAME

@Database(
    entities = [BeerResponseItem::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BeersDatabase : RoomDatabase() {

    abstract fun getBeerDao(): BeersDao

    companion object {
        //other threads can see when it changes
        @Volatile
        private var instance: BeersDatabase? = null

        // to sure that will be only 1 instance of db
        private val LOCK = Any()

        // when we create an instance it will be called
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BeersDatabase::class.java,
                DATABASE_NAME
            ).build()

    }


}