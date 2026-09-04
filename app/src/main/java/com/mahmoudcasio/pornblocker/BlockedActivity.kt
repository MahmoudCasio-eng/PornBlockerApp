package com.mahmoudcasio.pornblocker

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BlockedActivity : AppCompatActivity() {
    private var player: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked)
        player = MediaPlayer.create(this, R.raw.ayah_16).apply { start() }
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }
    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}
