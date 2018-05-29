package com.example.jamal.gridexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by jamal on 13/01/2018.
 */

public class GridAdapter extends BaseAdapter {

    Integer[] imageIds;
    String[] imageNames;
    Context context;
    ImageView image;
    TextView textName,textDate;
    // cette objet sert à afficher la date selon un format (HH:mm:ss dans ce cas )  (technique java )
    SimpleDateFormat dateFromat =
    new SimpleDateFormat("HH:mm:ss" ,new Locale("FR","fr"));

    // on précise le timeZone d'afrique pour que notre date soit à jour (technique java)
    static {

        TimeZone tzone = TimeZone.getTimeZone("Africa/Timbuktu");
        TimeZone.setDefault(tzone);
    }

    public GridAdapter(Context c,Integer[] ids,String[] names){
        this.imageNames=names;
        this.imageIds=ids;
        this.context=c;

    }
    @Override
    public int getCount() {
        // on retourne le nombre d'elements de notre liste pour que la methode getView sache combien de vue doit mettre en place

        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView;
        if(convertView==null){
            myView = new View(context);
            LayoutInflater inflater = (LayoutInflater)
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myView= inflater.inflate(R.layout.targetview,null);
            image = (ImageView) myView.findViewById(R.id.myImage);
            textName = (TextView) myView.findViewById(R.id.picName);
            textDate = (TextView) myView.findViewById(R.id.dateCreation);
            // ici on telecharge l'image directement par la methode setImageResource mais ce traitement est trop lourd pour le
            // le processeur , alors je l'ignore ( elle est fonctionnelle quand meme )

           // PREMIERE SOLUTION image.setImageResource(imageIds[position]);

            // cette solution lance un nouveau THREAD qui va remplir notre ImageView par l'image appropriée , on verra cette technique
            // aprés , ça rentre dans le cadre du travail en arrriére plan , cette class hérite de AsyncTask()
           // new DownloadImage(context,image).execute(imageIds[position]);
            image.setImageResource(imageIds[position]);
            // on remplit notre TextView par le nom de l'image
            textName.setText(imageNames[position]);

            // la même chose pour le TextView de la date
            textDate.setText(dateFromat.format(new Date()));
        } else{

            // si notre vue n'est pas nulle ( la vue a été déja inflatée ) alors on la recupére
            myView = convertView;
        }
        return myView;
    }
}
