package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.view.states.ForLocalNoteState
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel
import timber.log.Timber

class StatisticsFragment  : Fragment(R.layout.fragment_statistics) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    private var Days5Ago: Int = 0
    private var Days4Ago: Int = 0
    private var Days3Ago: Int = 0
    private var Days2Ago: Int = 0
    private var Days1Ago: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                initObserver()
                mainViewModel.getNumberOfNotesOneDayAgo()
                mainViewModel.getNumberOfNotesTwoDaysAgo()
                mainViewModel.getNumberOfNotesThreeDaysAgo()
                mainViewModel.getNumberOfNotesFourDaysAgo()
                mainViewModel.getNumberOfNotesFiveDaysAgo()
                Show()
            }
        }
    }

    private fun initObserver() {
        mainViewModel.localNoteState.observe(viewLifecycleOwner, Observer {
            renderLocalNoteState(it)
        })
    }

    private fun renderLocalNoteState(state: ForLocalNoteState) {
        when (state) {
            is ForLocalNoteState.GetNotesCountOneDayAgo -> {
                Timber.e(state.toString())
                this.Days1Ago = state.cnt
            }
            is ForLocalNoteState.GetNotesCountTwoDaysAgo -> {
                Timber.e(state.toString())
                this.Days2Ago = state.cnt
            }
            is ForLocalNoteState.GetNotesCountThreeDaysAgo -> {
                Timber.e(state.toString())
                this.Days3Ago = state.cnt
            }
            is ForLocalNoteState.GetNotesCountFourDaysAgo -> {
                Timber.e(state.toString())
                this.Days4Ago = state.cnt
            }
            is ForLocalNoteState.GetNotesCountFiveDaysAgo -> {
                Timber.e(state.toString())
                this.Days5Ago = state.cnt
            }
            is ForLocalNoteState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun Show() {

//        val Days5Ago: Int = 50
//        val Days4Ago: Int = 60
//        val Days3Ago: Int = 20
//        val Days2Ago: Int = 10
//        val Days1Ago: Int = 30

        val list = listOf(Days1Ago, Days2Ago, Days3Ago, Days4Ago, Days5Ago)
        val max: Int = list.maxOrNull() ?: 0

        val multiple: Float = 100F

        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val w = canvasWidth / 12
            val h = canvasHeight / 3

            var size1: Float = 0f
            var size2: Float = 0f
            var size3: Float = 0f
            var size4: Float = 0f
            var size5: Float = 0f

            if(max * multiple > (canvasHeight-canvasHeight/3)){
                val times = (max * multiple) / (canvasHeight-canvasHeight/3)
                size5 = Days5Ago * (multiple / (times+1))
                size4 = Days4Ago * (multiple / (times+1))
                size3 = Days3Ago * (multiple / (times+1))
                size2 = Days2Ago * (multiple / (times+1))
                size1 = Days1Ago * (multiple / (times+1))
            }else{
                size5 = Days5Ago*multiple
                size4 = Days4Ago*multiple
                size3 = Days3Ago*multiple
                size2 = Days2Ago*multiple
                size1 = Days1Ago*multiple
            }

            withTransform({
                rotate(180.0f, center)
            }) {
                drawRect(
                    color = Color.Blue,
                    topLeft = Offset(x = 200F, y = canvasHeight / 3F),
                    size = Size(w, size1)
                )
            }

            withTransform({
                rotate(180.0f, center)
            }) {
                drawRect(
                    color = Color.Red,
                    topLeft = Offset(x = 350F, y = canvasHeight / 3F),
                    size = Size(w, size2)
                )
            }
            withTransform({
                rotate(180.0f, center)
            }) {
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(x = 500F, y = canvasHeight / 3F),
                    size = Size(w, size3)
                )
            }
            withTransform({
                rotate(180.0f, center)
            }) {
                drawRect(
                    color = Color.Yellow,
                    topLeft = Offset(x = 650F, y = canvasHeight / 3F),
                    size = Size(w, size4)
                )
            }
            withTransform({
                rotate(180.0f, center)
            }) {
                drawRect(
                    color = Color.Black,
                    topLeft = Offset(x = 800F, y = canvasHeight / 3F),
                    size = Size(w, size5)
                )
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Show()
    }

}