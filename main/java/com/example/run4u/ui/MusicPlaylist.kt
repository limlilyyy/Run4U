package com.example.run4u.ui
import SongAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.run4u.R
import com.google.android.material.floatingactionbutton.FloatingActionButton



class MusicPlaylist : AppCompatActivity() {

    private val songList = mutableListOf<Song>()
    private lateinit var songAdapter: SongAdapter
    private val PICK_AUDIO_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val playlistRecyclerView = findViewById<RecyclerView>(R.id.playlistRecyclerView)
        val addSongFab = findViewById<FloatingActionButton>(R.id.addSongFab)

        songAdapter = SongAdapter(songList) { song ->
            songList.remove(song)
            songAdapter.notifyDataSetChanged()
        }

        playlistRecyclerView.layoutManager = LinearLayoutManager(this)
        playlistRecyclerView.adapter = songAdapter

        addSongFab.setOnClickListener {
            openAudioFilePicker()
        }
    }

    private fun openAudioFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "audio/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, PICK_AUDIO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AUDIO_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                val fileName = getFileName(uri)
                val newSong = Song(fileName, uri.toString())
                songList.add(newSong)
                songAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        var fileName = "Unknown"
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst()) {
                fileName = cursor.getString(nameIndex)
            }
        }
        return fileName
    }
}
