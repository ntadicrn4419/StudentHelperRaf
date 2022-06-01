package rs.raf.projekat2.studenthelperraf.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.studenthelperraf.data.models.Term
import rs.raf.projekat2.studenthelperraf.databinding.LayoutItemTermBinding
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.diff.TermDiffCallback
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.viewholder.TermViewHolder

class TermAdapter : ListAdapter<Term, TermViewHolder>(TermDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermViewHolder {
        val itemBinding = LayoutItemTermBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TermViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}