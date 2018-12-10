package ic.uff.br.computacaoubiqua.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.database.user.UserDao;
import ic.uff.br.computacaoubiqua.database.visit.Visit;
import ic.uff.br.computacaoubiqua.database.visit.VisitDao;
import ic.uff.br.computacaoubiqua.utils.DateTypeConverter;

@Database(entities = {User.class, Visit.class}, version = 1,  exportSchema = false)
@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    protected AppDatabase(){

    }

    public static synchronized AppDatabase getInstance(Context context){
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "CompUbiqua").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();
    public abstract VisitDao visitDao();

}
