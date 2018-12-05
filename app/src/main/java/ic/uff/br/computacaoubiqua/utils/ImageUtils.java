package ic.uff.br.computacaoubiqua.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

public class ImageUtils {

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        return Uri.parse(getImagePath(inContext,inImage));
    }

    public static String getImagePath(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return path;
    }


}
