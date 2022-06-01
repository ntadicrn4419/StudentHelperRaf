package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.studenthelperraf.data.models.Term
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemTermBinding

class TermViewHolder (private val itemBinding: LayoutItemTermBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(term: Term) {
//        val str: String = term.subject + ", " + term.day + term.time + ", "  + term.classroom
//        itemBinding.termpresenter.text = str
//
        val str: String = term.subject + "-" + term.type
        itemBinding.subjectAndType.text = str
        itemBinding.teacher.text = term.teacher
        itemBinding.classroom.text = term.classroom
        itemBinding.groups.text = term.groups

        itemBinding.day.text = term.day
        itemBinding.time.text = term.time


    }
}