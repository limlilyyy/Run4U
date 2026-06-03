

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.run4u.R
import com.example.run4u.ui.Song

class SongAdapter(
    private val songs: MutableList<Song>,
    private val onDeleteClick: (Song) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentlyPlayingUri: String? = null

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.songTitle)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
        val playButton: ImageView = itemView.findViewById(R.id.playButton)
        val pauseButton: ImageView = itemView.findViewById(R.id.pauseButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.songTitle.text = song.title

        holder.deleteButton.setOnClickListener {
            onDeleteClick(song)
        }

        holder.playButton.setOnClickListener {
            val context = holder.itemView.context
            if (currentlyPlayingUri != song.uri) {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(context, Uri.parse(song.uri))
                currentlyPlayingUri = song.uri
                mediaPlayer?.start()
            } else if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
            }
        }

        holder.pauseButton.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }
    }

    override fun getItemCount(): Int = songs.size

    private fun stopMediaPlayer() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
        currentlyPlayingUri = null
    }

    fun releaseMediaPlayer() {
        stopMediaPlayer()
    }
}
