package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemNoteBinding
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder.NoteViewHolder

class NoteAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = LayoutItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}