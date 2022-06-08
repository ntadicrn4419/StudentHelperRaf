package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.databinding.FragmentSingleNoteBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel

class SingleNoteFragment : Fragment(R.layout.fragment_single_note) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentSingleNoteBinding? = null
    private val binding get() = _binding!!
    private var id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        val arguments = arguments
        this.id = arguments?.getInt("noteId")
        initUi()
    }

    private fun initUi() {
        val arguments = arguments
        val t = arguments?.getString("noteTitle")
        val c = arguments?.getString("noteContent")
        binding.noteTitle.setText(t)
        binding.noteContent.setText(c)
        initListeners()
    }

    private fun initListeners() {
        binding.closeBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.noteEditBtn.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val content = binding.noteContent.text.toString()
            val archived = arguments?.getBoolean("noteArchived")
            if (archived != null) {
                this.mainViewModel.updateNote(this.id!!, title, content, archived)
            }
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.main_fragment_container, MainFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}