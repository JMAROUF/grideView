package com.example.jamal.gridexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by jamal on 06/01/2018.
 */

public class DownloadImage extends AsyncTask<Integer, Void, Bitmap> {
    ImageView bmImage;
    Context context;

    public DownloadImage(Context context, ImageView bmImage) {
        this.bmImage = bmImage;
        this.context=context;

    }
    protected Bitmap doInBackground(Integer... ids) {
        int id = ids[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = context.getResources().openRawResource(id);
            mIcon11 = BitmapFactory.decodeStream(in);
            in.close();

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
