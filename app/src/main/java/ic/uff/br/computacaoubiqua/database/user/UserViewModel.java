package ic.uff.br.computacaoubiqua.database.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.services.BluetoothService;

public class UserViewModel extends AndroidViewModel {

    private final LiveData<List<User>> userList;

    public UserViewModel(@NonNull Application application) {
        super(application);

//        AppDatabase.getInstance(getApplication()).userDao().insertAll(new User(first_name,"TESTE",device.getAddress(),"É um cara bem legal", "eu mesmo", "maternidade"));

        userList = AppDatabase.getInstance(getApplication()).userDao().getAllinLiveData();
    }

    public LiveData<List<User>> getUsers() {
        return userList;
    }
}
