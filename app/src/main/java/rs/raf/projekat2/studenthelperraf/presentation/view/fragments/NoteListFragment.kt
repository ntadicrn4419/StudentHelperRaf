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
import rs.raf.projekat2.studenthelperraf.data.models.Note
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
            parentFragmentManager.beginTransaction().setReorderingAllowed(true)
                .replace(R.id.main_fragment_container, NewNoteFragment())
                .addToBackStack(null).commit()
        }
        binding.searchNotes.doAfterTextChanged {
            val filter = it.toString()
            if(binding.switchBtn.isChecked){
                mainViewModel.getNotesByTitleOrContent(filter, true)
            }else{
                mainViewModel.getNotesByTitleOrContent(filter, false)
            }

        }
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                println("switch on")
                //prikazati sve beleske
                //mainViewModel.getAllNotes()
                mainViewModel.getNotesByTitleOrContent(binding.searchNotes.text.toString(), true)
            }
            else {
                println("switch off")
                //prikazati samo one koje nisu archived, tj. one ciji archived=false
                //mainViewModel.getNotesByArchived(false)
                mainViewModel.getNotesByTitleOrContent(binding.searchNotes.text.toString(), false)
            }
        }
    }

    private fun initRecycler() {
        binding.noteRv.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(::funDeleteBtnListener, ::funEditBtnListener, ::funArchiveBtnListener)
        binding.noteRv.adapter = adapter
    }

    private fun funDeleteBtnListener(note: Note){
        mainViewModel.deleteNote(note.id)
    }

    private fun funEditBtnListener(note: Note){
        val fragment = SingleNoteFragment()
        val arguments = Bundle()
        arguments.putInt("noteId", note.id)
        arguments.putString("noteTitle", note.title)
        arguments.putString("noteContent", note.content)
        arguments.putBoolean("noteArchived", note.archived)
        fragment.arguments = arguments
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }

    private fun funArchiveBtnListener(note: Note){
        mainViewModel.updateNote(note.id, note.title, note.content, !note.archived)
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