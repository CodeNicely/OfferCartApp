package com.example.aman.offercart_v1.helper.image_loader;

import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by meghal on 13/10/16.
 */

public interface ImageLoader {

    void loadImage(String url, ImageView imageView, ProgressBar progressBar);
//    void load_circular_image(String url, ImageView imageView);
}
