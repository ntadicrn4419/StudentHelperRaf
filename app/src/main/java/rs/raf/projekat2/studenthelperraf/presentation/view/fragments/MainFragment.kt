package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.data.models.Note
import rs.raf.projekat2.studenthelperraf.databinding.FragmentMainBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.adapters.MainPagerAdapter
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel

class MainFragment  : Fragment(R.layout.fragment_main){
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val note1 = Note(111, "111", "111", false, "2022-06-06")
        val note2 = Note(222, "211", "211", false, "2022-06-06")
        val note3 = Note(311, "311", "311", false, "2022-06-06")
        val note4 = Note(411, "411", "411", false, "2022-06-05")
        val note5 = Note(511, "511", "511", false, "2022-06-04")
        val note6 = Note(611, "611", "611", false, "2022-06-03")
        val note7 = Note(711, "711", "711", false, "2022-06-03")
        val note8 = Note(811, "811", "811", false, "2022-06-02")
        val note9 = Note(911, "911", "911", false, "2022-06-02")
        val note10 = Note(1011, "10-11", "10-11", false, "2022-06-02")
        val note11 = Note(1111, "11-11", "11-11", false, "2022-06-02")
        val note12 = Note(1211, "12-11", "12-11", false, "2022-06-02")

        mainViewModel.addNote(note1)
        mainViewModel.addNote(note2)
        mainViewModel.addNote(note3)
        mainViewModel.addNote(note4)
        mainViewModel.addNote(note5)
        mainViewModel.addNote(note6)
        mainViewModel.addNote(note7)
        mainViewModel.addNote(note8)
        mainViewModel.addNote(note9)
        mainViewModel.addNote(note10)
        mainViewModel.addNote(note11)
        mainViewModel.addNote(note12)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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
        binding.viewPager.adapter =
            this.activity?.let {
                MainPagerAdapter(
                    it.supportFragmentManager,
                    this.requireContext()
                )
            }
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

}