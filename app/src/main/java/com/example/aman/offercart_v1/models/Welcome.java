package com.example.aman.offercart_v1.models;

/**
 * Created by aman on 11/10/16.
 */
public class Welcome {


    private String Version;
    private int id;
    private String img;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Welcome() {
    }

    public Welcome(String version, int id, String img) {
        Version = version;
        this.id = id;
        this.img = img;
    }
}
