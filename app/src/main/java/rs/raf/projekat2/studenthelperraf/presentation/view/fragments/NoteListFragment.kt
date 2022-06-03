package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.databinding.FragmentNoteListBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel
import timber.log.Timber


class NoteListFragment : Fragment(R.layout.fragment_note_list) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
        initUi()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initListeners() {
        binding.newNoteBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_fragment_container, NewNoteFragment())
                ?.addToBackStack(null)?.commit()
        }
        binding.searchNotes.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getNotesByTitleOrContent(filter)
        }
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                println("switch on")
                //prikazati sve beleske
                mainViewModel.getAllNotes()
                //mainViewModel.getNotesByArchived(true)
            }
            else {
                println("switch off")
                //prikazati samo one koje nisu archived, tj. one ciji archived=false
                mainViewModel.getNotesByArchived(false)
            }
        }
    }

    private fun initRecycler() {
        binding.noteRv.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(::funDeleteBtnListener, ::funEditBtnListener, ::funArchiveBtnListener)
        binding.noteRv.adapter = adapter
    }

    private fun funDeleteBtnListener(noteId: Int){
        mainViewModel.deleteNote(noteId)
    }

    private fun funEditBtnListener(noteId: Int, noteTitle: String, noteContent: String, noteArchived: Boolean){
        val fragment = SingleNoteFragment()
        val arguments = Bundle()
        arguments.putInt("noteId", noteId)
        arguments.putString("noteTitle", noteTitle)
        arguments.putString("noteContent", noteContent)
        arguments.putBoolean("noteArchived", noteArchived)
        fragment.arguments = arguments
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_fragment_container, fragment)
            ?.addToBackStack(null)?.commit()
    }

    private fun funArchiveBtnListener(noteId: Int, noteTitle: String, noteContent: String, noteArchived: Boolean){
        mainViewModel.updateNote(noteId, noteTitle, noteContent, !noteArchived)
    }

    private fun initObservers() {
        mainViewModel.localNoteState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        //mainViewModel.getAllNotes()
        mainViewModel.getNotesByArchived(false)

    }

    private fun renderState(state: ForLocalNoteState) {
        when (state) {
            is ForLocalNoteState.SuccessfullyGotAllNotes -> {
                adapter.submitList(state.notes)
            }
            is ForLocalNoteState.SuccessfullyGotFilteredNotes -> {
                adapter.submitList(state.notes)
            }
            is ForLocalNoteState.SuccessfullyGotNonArchiveNotes -> {
                adapter.submitList(state.notes)
            }
            is ForLocalNoteState.UpdatedNote -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is ForLocalNoteState.DeletedNote -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is ForLocalNoteState.GetNote -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is ForLocalNoteState.AddedNote -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
            is ForLocalNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}