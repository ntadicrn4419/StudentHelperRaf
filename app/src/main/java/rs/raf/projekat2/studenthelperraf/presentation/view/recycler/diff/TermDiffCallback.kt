package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.studenthelperraf.data.models.Term

class TermDiffCallback: DiffUtil.ItemCallback<Term>() {

    override fun areItemsTheSame(oldItem: Term, newItem: Term): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Term, newItem: Term): Boolean {
        if(oldItem.subject == newItem.subject && oldItem.type == newItem.type && oldItem.teacher == newItem.teacher
            && oldItem.groups == newItem.groups && oldItem.day == newItem.day && oldItem.time == newItem.time && oldItem.classroom == newItem.classroom)
        {
            return true;
        }
        return false;
    }
}