package com.ucsdextandroid1.musicsearch.search;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.ucsdextandroid1.musicsearch.R;
import com.ucsdextandroid1.musicsearch.data.SongItem;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MusicDetailsActivity extends AppCompatActivity {

    private TextView albumName = null;
    private TextView artistName = null;
    private ImageView artWork = null;
    private ImageButton backButton = null;

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        this.albumName = (TextView) findViewById(R.id.textViewSongName);
        this.artistName = (TextView) findViewById(R.id.textViewArtistName);
        this.artWork = (ImageView) findViewById(R.id.imageViewArtWork);
        this.backButton = (ImageButton) findViewById(R.id.imageButtonBack);

        SongItem songItem = getIntent().getParcelableExtra("song");

        if (songItem != null){
            this.albumName.setText(songItem.getTrackName());
            this.artistName.setText("By: "+songItem.getArtistName());

            new DownloadImageTask(artWork).execute(songItem.getArtworkUrl());
        }

        this.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicDetailsActivity.this, SearchActivity.class);

                startActivity(intent);
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
