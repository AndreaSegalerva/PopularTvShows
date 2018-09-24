package segalerva.andrea.populartvshows.extensions

import android.view.View

/**
 * Created by andrea on 16/9/18.
 * Common View extensions
 */

/**
 * Change View visibility to VISIBLE
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Change View visibility to GONE
 */
fun View.hide() {
    this.visibility = View.GONE
}