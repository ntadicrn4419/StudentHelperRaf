package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.studenthelperraf.data.models.Note

class NoteDiffCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        if(oldItem.title == newItem.title && oldItem.content == newItem.content)
        {
            return true;
        }
        return false;
    }
}