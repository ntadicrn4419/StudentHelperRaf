package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemNoteBinding

class NoteViewHolder (
    private val itemBinding: LayoutItemNoteBinding,
    private val funDeleteListener: (noteId: Int) -> Unit,
    private val funEditListener: (noteId: Int, noteTitle: String, noteContent: String, noteArchived: Boolean) -> Unit,
    private val funArchiveListener: (noteId: Int, noteTitle: String, noteContent: String, noteArchived: Boolean) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(note: Note) {
        itemBinding.noteTitle.text = note.title
        itemBinding.noteContent.text = note.content

        itemBinding.noteDeleteBtn.setOnClickListener {
            funDeleteListener(note.id)
        }

        itemBinding.noteEditBtn.setOnClickListener {
            funEditListener(note.id, note.title, note.content, note.archived)
        }

        itemBinding.noteArchiveBtn.setOnClickListener {
            funArchiveListener(note.id, note.title, note.content, note.archived)
        }

    }
}