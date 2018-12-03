package ic.uff.br.computacaoubiqua.database.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllinLiveData();

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE mac_address IN (:macAddress)")
    List<User> loadAllByIds(int[] macAddress);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE mac_address LIKE :mac_address LIMIT 1")
    User findByMacAddress(String mac_address);

    @Query("SELECT * FROM User")
    public List<UserWithVisits> loadUsersWithVisits();

    @Insert
    void insertAll(User... users);

    @Update
    public void updateAll(User... users);

    @Delete
    void delete(User user);
}