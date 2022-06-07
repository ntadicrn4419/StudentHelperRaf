package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.data.models.MyFilter
import rs.raf.projekat2.studenthelperraf.databinding.FragmentFilterBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.recycler.adapter.TermAdapter
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalTermState
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForRemoteTermState
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel
import timber.log.Timber


class FilterFragment : Fragment(R.layout.fragment_filter) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TermAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("metoda onViewCreated")
        init()
    }

    private fun init() {
        initObservers()
        initUi()
    }

    private fun initUi() {
        initRecycler()
        initDropdowns()
        initListeners()
    }

    private fun initDropdowns() {
        val groups = resources.getStringArray(R.array.group_filter_array)
        val groupsAdapter = this.activity?.let {
            ArrayAdapter(
                it.baseContext,
                android.R.layout.simple_spinner_item, groups)
        }
        binding.groupFilter.adapter = groupsAdapter
        val days = resources.getStringArray(R.array.day_filter_array)
        val daysAdapter = this.activity?.let {
            ArrayAdapter(
                it.baseContext,
                android.R.layout.simple_spinner_item, days)
        }
        binding.dayFilter.adapter = daysAdapter
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = TermAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
        binding.searchBtn.setOnClickListener {
            val group = binding.groupFilter.selectedItem.toString()
            val day = binding.dayFilter.selectedItem.toString()
            val teacherOrSubject = binding.inputEt.text.toString()
            val filter = MyFilter(group, day, teacherOrSubject)

            Timber.e("Ovo je filter: " + filter.group + ", " + filter.day + ", " + filter.teacherOrSubject)

            when{
                group != "Group" && day != "Day" && teacherOrSubject != "" -> mainViewModel.getTermsByAllFilters(filter)
                group == "Group" && day != "Day" && teacherOrSubject != "" -> mainViewModel.getTermsByDayAndTeacherSubject(filter)
                group != "Group" && day == "Day" && teacherOrSubject != "" -> mainViewModel.getTermsByGroupAndTeacherSubject(filter)
                group == "Group" && day == "Day" && teacherOrSubject != "" -> mainViewModel.getTermsByTeacherSubject(filter)
                group != "Group" && day == "Day" && teacherOrSubject == "" -> mainViewModel.getTermsByGroup(filter)
                group == "Group" && day != "Day" && teacherOrSubject == "" -> mainViewModel.getTermsByDay(filter)
                group != "Group" && day != "Day" && teacherOrSubject == "" -> mainViewModel.getTermsByGroupAndDay(filter)
                group == "Group" && day == "Day" && teacherOrSubject == "" -> mainViewModel.getAllTerms()
            }
        }
    }

    private fun initObservers() {
        mainViewModel.remoteTermState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderRemoteTermState(it)
        })
        mainViewModel.localTermState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderLocalTermState(it)
        })
        mainViewModel.getAllTerms()
        mainViewModel.fetchAllTerms()
    }

    private fun renderRemoteTermState(state: ForRemoteTermState) {
        when (state) {
            is ForRemoteTermState.SuccessRemoteState -> {
                showLoadingState(false)
                adapter.submitList(state.terms)
            }
            is ForRemoteTermState.ErrorRemoteState -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ForRemoteTermState.DataFetchedRemoteState -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is ForRemoteTermState.LoadingRemoteState -> {
                showLoadingState(true)
            }
        }
    }

    private fun renderLocalTermState(state: ForLocalTermState) {
        when (state) {
            is ForLocalTermState.SuccessfullyGotAll -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByAllFilters -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByDayAndTeacherSubject -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByGroupAndTeacherSubject -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByGroupAndDay -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByDay -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByTeacherOrSubject -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.SuccessfullyGotByGroup -> {
                adapter.submitList(state.terms)
            }
            is ForLocalTermState.ErrorLocalState -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.inputEt.isVisible = !loading
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
