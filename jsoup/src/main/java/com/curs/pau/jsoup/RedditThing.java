package com.curs.pau.jsoup;

/**
 * Created by pau on 04/02/15.
 */

import android.graphics.Bitmap;

/**
 * Created by pau on 04/02/15.
 */
public class RedditThing {
    public String title;
    public Bitmap img;

    public RedditThing() {}

    public RedditThing(String title, Bitmap img) {
        this.title = title;
        this.img = img;
    }
}
