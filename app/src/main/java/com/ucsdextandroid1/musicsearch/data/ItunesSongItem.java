package com.ucsdextandroid1.musicsearch.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rjaylward on 2019-05-11
 */
public class ItunesSongItem implements SongItem, Parcelable{

    //TODO add all the members to this class with proper serialized names
    @SerializedName("trackId")
    private long trackId;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("collectionName")
    private String albumName;
    @SerializedName("artworkUrl100")
    private String artworkUrl;
    @SerializedName("previewUrl")
    private String previewUrl;
    @SerializedName("trackTimeMillis")
    private long trackTime;

    public ItunesSongItem(Long trackId, String trackName, String artistName, String albumName, String artworkUrl, String previewUrl, Long trackTime){
        this.trackId = trackId;
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.artworkUrl = artworkUrl;
        this.previewUrl = previewUrl;
        this.trackTime = trackTime;
    }


    protected ItunesSongItem(Parcel in) {
        trackId = in.readLong();
        trackName = in.readString();
        artistName = in.readString();
        albumName = in.readString();
        artworkUrl = in.readString();
        previewUrl = in.readString();
        trackTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(trackId);
        dest.writeString(trackName);
        dest.writeString(artistName);
        dest.writeString(albumName);
        dest.writeString(artworkUrl);
        dest.writeString(previewUrl);
        dest.writeLong(trackTime);
    }

    public static final Creator<ItunesSongItem> CREATOR = new Creator<ItunesSongItem>() {
        @Override
        public ItunesSongItem createFromParcel(Parcel in) {
            return new ItunesSongItem(in);
        }

        @Override
        public ItunesSongItem[] newArray(int size) {
            return new ItunesSongItem[size];
        }
    };

    @Override
    public long getTrackId() {
        return trackId;
    }

    @Override
    public String getTrackName() {
        return trackName;
    }

    @Override
    public String getArtistName() {
        return artistName;
    }

    @Override
    public String getAlbumName() {
        return albumName;
    }

    @Override
    public String getArtworkUrl() {
        return artworkUrl;
    }

    @Override
    public String getPreviewUrl() {
        return previewUrl;
    }

    @Override
    public long getTrackTime() {
        return trackTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
