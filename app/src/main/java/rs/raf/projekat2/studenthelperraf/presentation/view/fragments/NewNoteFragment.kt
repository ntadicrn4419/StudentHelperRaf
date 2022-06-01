package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.FragmentNewNoteBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel
import java.util.*

class NewNoteFragment : Fragment(R.layout.fragment_new_note) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.closeBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fragment_container, MainFragment())
                ?.addToBackStack(null)?.commit()
        }
        binding.saveBtn.setOnClickListener {
            val noteTitle = binding.noteTitle.text.toString()
            val noteContent = binding.noteContent.text.toString()
            if (noteTitle.isBlank() || noteContent.isBlank()) {
                Toast.makeText(context, "Failed. Title and content can not be empy.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            binding.noteTitle.text.clear()
            binding.noteContent.text.clear()

            val temp: UUID = UUID.randomUUID()
            val id = temp.mostSignificantBits + temp.leastSignificantBits

            val noteToAdd = Note( id.toInt(), noteTitle, noteContent, false)
            mainViewModel.addNote(noteToAdd)

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fragment_container, MainFragment())
                ?.addToBackStack(null)?.commit()
        }
    }
}