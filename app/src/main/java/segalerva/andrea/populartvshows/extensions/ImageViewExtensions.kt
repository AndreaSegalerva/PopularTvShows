package segalerva.andrea.populartvshows.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import segalerva.andrea.populartvshows.R

/**
 * Created by andrea on 16/9/18.
 * ImageView common extensions
 */

/**
 * Load image using Picasso library
 */
fun ImageView.loadImage(path: String?) {

    Picasso.get().load("http://image.tmdb.org/t/p/w342/" + path).
            error(R.color.polar).
            placeholder(R.color.polar).
            into(this)
}