package ic.uff.br.computacaoubiqua.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import ic.uff.br.computacaoubiqua.activities.DetailActivity;

public class ImageUtils {

    public static final int IMAGE_MAX_WIDTH=1024;
    public static final int IMAGE_MAX_HEIGHT=768;

    public static Bitmap decodeFile(File f) throws IOException {
        Bitmap b = null;

        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = new FileInputStream(f);
        b = BitmapFactory.decodeStream(fis, null, o);
        fis.close();

        int scale = 1;
        int IMAGE_MAX_SIZE=Math.max(IMAGE_MAX_HEIGHT,IMAGE_MAX_WIDTH);
        if (o.outHeight > IMAGE_MAX_HEIGHT || o.outWidth > IMAGE_MAX_WIDTH) {
            scale = (int)Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        fis = new FileInputStream(f);
        b = BitmapFactory.decodeStream(fis, null, o2);
        fis.close();

        return b;
    }

    public static int getRotate(String path) throws IOException {
        ExifInterface exif = new ExifInterface(path);
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

        return rotationAngle;
    }

    public static String getImagePath(Context context, Uri uri){
        String path = null;
        String[] fileColumns = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, fileColumns, null, null, null);
        cursor.moveToFirst();
        int cIndex = cursor.getColumnIndex(fileColumns[0]);
        path = cursor.getString(cIndex);
        cursor.close();
        return path;
    }

    public static void showImage(Context context, String path, ImageView imageView) {
        if (path != null) {
            File f = new File(path);
            try {
                Bitmap bitmap = ImageUtils.decodeFile(f);
                imageView.setImageBitmap(bitmap);
                int rotate = ImageUtils.getRotate(path);
                Log.d("PHOTO", String.valueOf(rotate));
                imageView.setRotation(rotate);
            } catch (IOException e) {
                Toast.makeText(context, "Erro ao carregar a foto!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
