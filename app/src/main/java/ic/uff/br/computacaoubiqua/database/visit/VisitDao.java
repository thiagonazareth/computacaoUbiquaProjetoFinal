package ic.uff.br.computacaoubiqua.database.visit;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ic.uff.br.computacaoubiqua.database.user.User;

@Dao
public interface VisitDao {

    @Query("SELECT * FROM visit order by date")
    List<Visit> getAll();

    @Query("SELECT * FROM visit order by date")
    LiveData<List<Visit>> getAllinLiveData();

    @Query("SELECT * FROM visit WHERE id IN (:ids)")
    List<Visit> loadAllByIds(int[] ids);

    @Insert
    void insertAll(Visit... visits);

    @Update
    public void updateAll(Visit... visits);

    @Delete
    void delete(Visit visit);
}