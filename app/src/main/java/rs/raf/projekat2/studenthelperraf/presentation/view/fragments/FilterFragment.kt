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
        init()
    }

    private fun init() {
        initUi()
        initObservers()
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
//        binding.inputEt.doAfterTextChanged {
//            val filter = it.toString()
//            mainViewModel.getTermsBySubject(filter)
//        }
        binding.searchBtn.setOnClickListener {
            val group = binding.groupFilter.selectedItem.toString()
            val day = binding.dayFilter.selectedItem.toString()
            val teacherOrSubject = binding.inputEt.text.toString()
            if(group != null && day != null && teacherOrSubject != null &&
               group != "" && day != "" && teacherOrSubject != ""){
                val filter = MyFilter(group, day, teacherOrSubject)
                mainViewModel.getTermsByAllFilters(filter)
            }else{
                Toast.makeText(activity,"Wrong filters. You have to fill all fields.",Toast.LENGTH_LONG).show();
            }
            println(group + "-" + day + "-" + teacherOrSubject)
        }
    }

    private fun initObservers() {
        mainViewModel.remoteTermState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        mainViewModel.getAllTerms()
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        mainViewModel.fetchAllTerms()
    }

    private fun renderState(state: ForRemoteTermState) {
        when (state) {
            is ForRemoteTermState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.terms)
            }
            is ForRemoteTermState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ForRemoteTermState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is ForRemoteTermState.Loading -> {
                showLoadingState(true)
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
