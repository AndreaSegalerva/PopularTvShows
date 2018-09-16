package segalerva.andrea.populartvshows.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import segalerva.andrea.populartvshows.R

/**
 * Created by andrea on 16/9/18.
 */

fun ImageView.loadImage(url: String) {

    Picasso.get().load(url).
            error(R.drawable.ic_poster_placeholder).
            placeholder(R.drawable.ic_poster_placeholder).
            into(this)
}