package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.databinding.FragmentNoteListBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel


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
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initListeners() {
        binding.newNoteBtn.setOnClickListener {
//            val transaction = activity?.supportFragmentManager?.beginTransaction()
//            if (transaction != null) {
//                transaction.replace(R.id.fragment_note_list_layout, SingleNoteFragment())
//                transaction.disallowAddToBackStack()
//                transaction.commit()
//            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_layout, SingleNoteFragment())
                ?.addToBackStack(null)?.commit()

        }
    }

    private fun initRecycler() {
        binding.noteRv.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter()
        binding.noteRv.adapter = adapter
    }

    private fun initObservers() {
//        mainViewModel.localNoteState.observe(viewLifecycleOwner, Observer {
//            Timber.e(it.toString())
//            renderState(it)
//        })
        //mainViewModel.getAllNotes()
    }

    private fun renderState(state: ForLocalNoteState) {
        when (state) {
            is ForLocalNoteState.Success -> {
                adapter.submitList(state.notes)
            }
            is ForLocalNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}