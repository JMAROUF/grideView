package com.example.jamal.gridexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    GridView myGrid;
    ImageView fullImage;
    Animation alphaAnimation;
    TranslateAnimation translateAnimation;
    Integer[] imageIds={R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,};
    String[] imageNames= {"pic1",
            "pic2",
            "pic3",
            "pic4",
            "pic5",
            "pic6",
            "pic7",
            "pic8",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullImage = (ImageView) findViewById(R.id.fullImage);
        myGrid = (GridView) findViewById(R.id.myGrid);
        fullImage.setVisibility(View.INVISIBLE);

        // on implemente l'adaptateur à notre GridView
        myGrid.setAdapter(new GridAdapter(getApplicationContext(),imageIds,imageNames));


        // on ajoute un listener à  notre GridView
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // notre imageView va contenir l item qui subit l evenement

                fullImage.setImageResource(imageIds[position]);

                // on affiche notre imageView et on applique les deux animations
                showFull();
            }
        });



    }


    //show fullImage
    public void showFull(){
        // Alpha Animation ( la tansparence ) on telecharge l'animation depuis un fichier XML dans le dossier anim
        alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alphaanimation);
        // on donne un temps d'execution à notre animation (4 seconde dans ce cas )
        alphaAnimation.setDuration(4000);

        // Translate Animation , cette animation est déclarée en java et non pas en XML comme l'autre
        // , c'est pour but de traiter les deux possibiltés
        translateAnimation = new TranslateAnimation(0,0,fullImage.getHeight(),0);
        // on donne un temps d'execution à notre animation (1,5 seconde dans ce cas )
        translateAnimation.setDuration(1500);

        // on lance la translation sur notre imageView
        fullImage.startAnimation(translateAnimation);

        // on lance la transparence  sur notre GridView
        myGrid.startAnimation(alphaAnimation);

        // et en fin on affiche notre imageView
        fullImage.setVisibility(View.VISIBLE);

    }
}
