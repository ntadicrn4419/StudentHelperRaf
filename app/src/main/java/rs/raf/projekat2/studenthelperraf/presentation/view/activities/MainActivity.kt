package rs.raf.projekat2.studenthelperraf.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.databinding.ActivityMainBinding
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initFragment()
    }


    private fun initFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.add(R.id.main_fragment_container, MainFragment())
        transaction.commit()
    }
}