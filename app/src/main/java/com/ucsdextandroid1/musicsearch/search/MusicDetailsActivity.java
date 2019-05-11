package com.ucsdextandroid1.musicsearch.search;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.ucsdextandroid1.musicsearch.R;
import com.ucsdextandroid1.musicsearch.data.SongItem;
import com.ucsdextandroid1.musicsearch.utils.Utils;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MusicDetailsActivity extends AppCompatActivity {

    private TextView albumName = null;
    private TextView artistName = null;
    private ImageView artWork = null;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        this.albumName = (TextView) findViewById(R.id.textViewSongName);
        this.artistName = (TextView) findViewById(R.id.textViewArtistName);
        this.artWork = (ImageView) findViewById(R.id.imageViewArtWork);

        SongItem songItem = getIntent().getParcelableExtra("song");

        if (songItem != null){
            this.albumName.setText(songItem.getTrackName());
            this.artistName.setText("Track ID: "+songItem.getTrackId()+"\n"+songItem.getAlbumName()+"  :  "+songItem.getArtistName()+"\n"+
                    "Duration: "+songItem.getTrackTime()+"ms");

            Picasso.get().load(songItem.getArtworkUrl())
                    .placeholder(new ColorDrawable(Utils.randomColor()))
                    .into(artWork);
        }

    }
}
