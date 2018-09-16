package segalerva.andrea.populartvshows.extensions

import android.view.View

/**
 * Created by andrea on 16/9/18.
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}