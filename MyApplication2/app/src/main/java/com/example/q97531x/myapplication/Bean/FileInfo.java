package com.example.q97531x.myapplication.Bean;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by q97531x on 2016/5/2.
 */
public class FileInfo implements Parcelable{
    private int id;
    private String FilePath;
    private String FileName;
    private Uri uri;

    public static  final Creator<FileInfo> CREATOR = new Creator<FileInfo>() {
        @Override
        public FileInfo createFromParcel(Parcel source) {
            return new FileInfo(source);
        }

        @Override
        public FileInfo[] newArray(int size) {
            return new FileInfo[size];
        }
    };
    protected FileInfo(Parcel source){
        id = source.readInt();
        FilePath = source.readString();
        FileName = source.readString();
        uri = source.readParcelable(Uri.class.getClassLoader());
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(FilePath);
        dest.writeString(FileName);
        dest.writeParcelable(uri,PARCELABLE_WRITE_RETURN_VALUE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public FileInfo() {
    }
}
