package kz.ablazim.itbookstore.data.cache

import androidx.room.Room
import kz.ablazim.itbookstore.core.ITBookStoreApp

private const val DATABASE_NAME = "itbookstore"

interface RoomProvider {

    fun provide(): ITBookStoreDatabase

    class Base : RoomProvider {
        override fun provide(): ITBookStoreDatabase {
            return Room.databaseBuilder(
                ITBookStoreApp(),
                ITBookStoreDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}