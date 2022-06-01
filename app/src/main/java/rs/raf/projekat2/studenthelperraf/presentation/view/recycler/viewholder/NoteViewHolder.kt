package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemNoteBinding

class NoteViewHolder (private val itemBinding: LayoutItemNoteBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(note: Note) {
        itemBinding.noteTitle.text = note.title
        itemBinding.noteContent.text = note.content
    }
}