package rs.raf.projekat2.studenthelperraf.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.FilterFragment
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.NoteListFragment
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.ProfileFragment
import rs.raf.projekat2.studenthelperraf.presentation.view.fragments.StatisticsFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 4
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
        const val FRAGMENT_3 = 2
        const val FRAGMENT_4 = 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            FRAGMENT_1 -> FilterFragment()
            FRAGMENT_2 -> StatisticsFragment()
            FRAGMENT_3 -> NoteListFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FRAGMENT_1 -> context.getString(R.string.filter_fragment_title)
            FRAGMENT_2 -> context.getString(R.string.statistics_fragment_title)
            FRAGMENT_3 -> context.getString(R.string.note_list_fragment_title)
            else -> context.getString(R.string.profile_fragment_title)
        }
    }
}