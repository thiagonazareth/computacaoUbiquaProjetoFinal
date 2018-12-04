package ic.uff.br.computacaoubiqua.database.visit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;

public class VisitViewModel extends AndroidViewModel {

    private VisitDao visitDao;
    private LiveData<List<Visit>> visitsLiveData;

    public VisitViewModel(@NonNull Application application) {
        super(application);
        visitDao = AppDatabase.getInstance(application).visitDao();
        visitsLiveData = visitDao.getAllinLiveData();
    }

    public LiveData<List<Visit>> getVisitsList() {
        return visitsLiveData;
    }

}
