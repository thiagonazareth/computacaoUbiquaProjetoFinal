package ic.uff.br.computacaoubiqua.database;

import android.app.Activity;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.ContextWrapper;

import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.user.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    protected AppDatabase(){

    }

    public static synchronized AppDatabase getInstance(ContextWrapper contextWrapper){
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(contextWrapper.getApplicationContext(), AppDatabase.class, "CompUbiqua").build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();

}
