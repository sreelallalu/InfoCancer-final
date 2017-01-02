package info.CameraClick;

/**
 * Created by SLR on 9/26/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraPhoto {
    final String TAG = this.getClass().getSimpleName();
    private String photoPath;
    private Context context;
private Uri uri;

    public String getPhotoPath() {
        return this.photoPath;
    }

    public CameraPhoto(Context context) {


        this.context = context;
    }

    public Intent takePhotoIntent() throws IOException {
        Intent in = new Intent("android.media.action.IMAGE_CAPTURE");
        if(in.resolveActivity(this.context.getPackageManager()) != null) {
            File photoFile = this.createImageFile();
            if(photoFile != null) {
                in.putExtra("output", Uri.fromFile(photoFile));
            }
        }

        return in;

        //  }





    }

    public File createImageFile() throws IOException {
       String timeStamp = (new SimpleDateFormat("yyyyMMdd_HHmmss")).format(new Date());
        String imageFileName = "USERIMAGE_";

        /*String filenamec= Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/WhatsApp/Databases/";*/

   String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/Android/data/" + context.getPackageName() + "";

        File sd = new File(file_path);

        if (!sd.exists()) {
            sd.mkdir();
        }

            //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(imageFileName, ".jpg", sd);


            this.photoPath = image.getAbsolutePath();
            return image;




    }
    public void addToGallery() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(this.photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.context.sendBroadcast(mediaScanIntent);
    }
}