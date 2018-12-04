package ic.uff.br.computacaoubiqua.activities.ui.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.DetailActivity;
import ic.uff.br.computacaoubiqua.activities.HomeActivity;
import ic.uff.br.computacaoubiqua.activities.MainActivity;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;

public class DetailFragment extends Fragment {

    //    private UserViewModel mViewModel;
    private Activity activity;
    private TextView txViewdeviceName;
    private TextView txViewMacAddress;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextDescription;
    private EditText editTextKindship;
    private EditText editTextPlace;
    private Button btnSavePerson;
    private ImageView img;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        DetailActivity activity = (DetailActivity) getActivity();

        txViewdeviceName = ((TextView) view.findViewById(R.id.txViewdeviceName));
        txViewdeviceName.setText(activity.user.getDeviceName());
        txViewMacAddress = ((TextView) view.findViewById(R.id.txViewMacAddress));
        txViewMacAddress.setText(activity.user.getMacAddress());

        editTextFirstName = ((EditText) view.findViewById(R.id.editTextFirstName));
        editTextLastName = ((EditText) view.findViewById(R.id.editTextLastName));
        editTextDescription = ((EditText) view.findViewById(R.id.editTextDescription));
        editTextKindship = ((EditText) view.findViewById(R.id.editTextKindship));
        editTextPlace = ((EditText) view.findViewById(R.id.editTextPlace));

        img = ((ImageView) view.findViewById(R.id.imageView));

        btnSavePerson = (Button) view.findViewById(R.id.btnSavePerson);

        btnSavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(editTextFirstName.getText().toString(),
                        editTextLastName.getText().toString(),
                        activity.user.getMacAddress(),
                        activity.user.getDeviceName(),
                        editTextDescription.getText().toString(),
                        editTextKindship.getText().toString(),
                        editTextPlace.getText().toString(),
                        null);

                new UserInsertAsyncTask().execute(new ViewAndUserHelper(view.getContext(), user));
            }
        });


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(v.getContext().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        return view;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private class UserInsertAsyncTask extends AsyncTask<ViewAndUserHelper, Integer, ViewAndUserHelper> {
        protected ViewAndUserHelper doInBackground(ViewAndUserHelper... vwusers) {
            if (vwusers.length > 0) {
                AppDatabase.getInstance(activity).userDao().insertAll(vwusers[0].user);
                return vwusers[0];
            }
            return null;
        }

        protected void onPostExecute(ViewAndUserHelper vwuser) {
            if (vwuser != null){
                Toast.makeText(vwuser.context, "Pessoa adicionada às PESSOAS CONHECIDAS!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(vwuser.context, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private class ViewAndUserHelper {
        Context context;
        User user;

        private ViewAndUserHelper(Context context, User user) {
            this.context = context;
            this.user = user;
        }
    }

}
