package segalerva.andrea.populartvshows.extensions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import segalerva.andrea.populartvshows.R

/**
 * Created by andrea on 21/9/18.
 * Toast common extensions
 */

/**
 * Create & show base toast being used as generic one in the application
 * @param context
 * @param message
 * @param gravity
 * @param duration
 */
fun Toast.showToast(context: Context, message: String, gravity: Int, duration: Int) {
    val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.base_toast, (context as Activity).findViewById(R.id.base_toast_content))
    layout.findViewById<TextView>(R.id.tv_message).text = message
    setGravity(gravity, 0, 0)
    setDuration(duration)
    view = layout
    show()
}