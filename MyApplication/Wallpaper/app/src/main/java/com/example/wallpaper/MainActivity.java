package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    ImageView myImageView;
    Drawable myDrawable;
    WallpaperManager myWallpaperManager;


    Integer[]myImageArray={
            R.drawable.wall,
            R.drawable.aa,
            R.drawable.buto,
            R.drawable.butteer,
            R.drawable.heart,
            R.drawable.moon,
            R.drawable.rose,
            R.drawable.sool
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.myGridView);
        myImageView=findViewById(R.id.myImageView);


        myWallpaperManager=WallpaperManager.getInstance(getApplicationContext());
   myDrawable=myWallpaperManager.getDrawable();
      myImageView.setImageDrawable(myDrawable);

    }
}
