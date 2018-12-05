package ic.uff.br.computacaoubiqua.activities.ui.detail;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.activities.DetailActivity;
import ic.uff.br.computacaoubiqua.activities.MainActivity;
import ic.uff.br.computacaoubiqua.adapters.DeviceAdapter;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.user.User;
import ic.uff.br.computacaoubiqua.utils.ImageUtils;

import static android.app.Activity.RESULT_OK;

public class DetailPersonFragment extends Fragment {

    private TextView txViewdeviceName;
    private TextView txViewMacAddress;
    private TextView txViewFirstName;
    private TextView txViewLastName;
    private TextView txViewDescription;
    private TextView txViewKindship;
    private TextView txViewPlace;
    private ImageView imgView;

    public static DetailPersonFragment newInstance() {
        return new DetailPersonFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment_person, container, false);

        DetailActivity activity = (DetailActivity) getActivity();

        txViewdeviceName = ((TextView) view.findViewById(R.id.txViewdeviceName));
        txViewdeviceName.setText(activity.user.getDeviceName());
        txViewMacAddress = ((TextView) view.findViewById(R.id.txViewMacAddress));
        txViewMacAddress.setText(activity.user.getMacAddress());
        txViewFirstName = ((TextView) view.findViewById(R.id.txViewFirstName));
        txViewFirstName.setText(activity.user.getFirstName());
        txViewLastName = ((TextView) view.findViewById(R.id.txViewLastName));
        txViewLastName.setText(activity.user.getLastName());
        txViewDescription = ((TextView) view.findViewById(R.id.txViewDescription));
        txViewDescription.setText(activity.user.getDescription());
        txViewKindship = ((TextView) view.findViewById(R.id.txViewKindship));
        txViewKindship.setText(activity.user.getKinship());
        txViewPlace = ((TextView) view.findViewById(R.id.txViewPlace));
        txViewPlace.setText(activity.user.getPlace());

        imgView = ((ImageView) view.findViewById(R.id.imageView));

        if (activity.user.getPhotoPath() != null) {

            File f = new File(activity.user.getPhotoPath());
            try {
                Bitmap bitmap = ImageUtils.decodeFile(f);
                imgView.setImageBitmap(bitmap);
            }
            catch(Exception e){

            }
         }

        return view;
    }

}
