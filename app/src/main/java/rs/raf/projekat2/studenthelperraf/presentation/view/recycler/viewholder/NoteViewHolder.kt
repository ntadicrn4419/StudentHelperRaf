package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemNoteBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.activities.MainActivity
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.SingleNoteFragment

class NoteViewHolder (private val itemBinding: LayoutItemNoteBinding, private val mainViewModel: MainContract.ViewModel, private val activity: MainActivity) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(note: Note) {
        itemBinding.noteTitle.text = note.title
        itemBinding.noteContent.text = note.content
        itemBinding.noteDeleteBtn.setOnClickListener {
            mainViewModel.deleteNote(note.id)
        }
        itemBinding.noteEditBtn.setOnClickListener {
            val fragment = SingleNoteFragment()
            val arguments = Bundle()
            arguments.putInt("noteId", note.id)
            arguments.putString("noteTitle", note.title)
            arguments.putString("noteContent", note.content)
            fragment.arguments = arguments
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(null).commit()
        }
        itemBinding.noteArchiveBtn.setOnClickListener {
            //mainViewModel.deleteNote(note.id)
        }
    }
}