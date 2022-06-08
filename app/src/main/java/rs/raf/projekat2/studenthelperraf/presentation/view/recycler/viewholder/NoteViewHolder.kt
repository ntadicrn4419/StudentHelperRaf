package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemNoteBinding

class NoteViewHolder (
    private val itemBinding: LayoutItemNoteBinding,
    private val funDeleteListener: (position: Int) -> Unit,
    private val funEditListener: (position: Int) -> Unit,
    private val funArchiveListener: (position: Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init{
        itemBinding.noteDeleteBtn.setOnClickListener {
            funDeleteListener(layoutPosition)
        }

        itemBinding.noteEditBtn.setOnClickListener {
            funEditListener(layoutPosition)
        }

        itemBinding.noteArchiveBtn.setOnClickListener {
            funArchiveListener(layoutPosition)
        }
    }

    fun bind(note: Note) {
        itemBinding.noteTitle.text = note.title
        itemBinding.noteContent.text = note.content
    }
}