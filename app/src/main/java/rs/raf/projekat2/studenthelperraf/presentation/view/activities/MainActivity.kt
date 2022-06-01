package rs.raf.projekat2.studenthelperraf.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.studenthelperraf.databinding.ActivityMainBinding
import rs.raf.projekat2.studenthelperraf.presentation.view.adapters.MainPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.viewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager,
                this
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}