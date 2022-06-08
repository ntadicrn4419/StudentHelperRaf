package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.databinding.FragmentMainBinding
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.adapters.MainPagerAdapter
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel

class MainFragment  : Fragment(R.layout.fragment_main){
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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