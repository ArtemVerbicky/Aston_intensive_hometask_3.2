package ru.verb.aston_intensive_hometask_3_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

fun ImageView.downloadImageWithGlide(link: String?) {
    if(link.isNullOrBlank()) return
    Glide.with(this)
        .load(link)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Toast.makeText(context, "${e?.message}", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}

fun ImageView.downloadImageWithNativeTools(link: String?) {
    if(link.isNullOrBlank()) return
    val thread = Thread {
        val handler = Handler(Looper.getMainLooper())
        val url = URL(link)
        val connection: HttpURLConnection = (url.openConnection() as HttpURLConnection).apply {
            doInput = true
            connect()
        }
        val inputStream: InputStream = connection.inputStream
        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
        handler.post {
            this.setImageBitmap(bitmap)
        }
    }

    try {
        thread.start()
    } catch (e: Exception) {
        Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
    }
}



fun AppCompatActivity.getDebouncedTextWatcher(delay: Long, action: (String) -> Unit) = object :
    TextWatcher {
    var lastInput = 0L
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val currentTime = SystemClock.uptimeMillis()
        if (currentTime - lastInput > delay) {
            lastInput = currentTime
            action.invoke(s.toString())
        }

    }

    override fun afterTextChanged(s: Editable?) {}
}