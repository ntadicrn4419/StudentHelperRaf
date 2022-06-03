package rs.raf.projekat2.studenthelperraf.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.studenthelperraf.R
import rs.raf.projekat2.studenthelperraf.presentation.contract.MainContract
import rs.raf.projekat2.studenthelperraf.presentation.viewmodel.MainViewModel

class StatisticsFragment  : Fragment(R.layout.fragment_statistics) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()
    //private var _binding: FragmentStatisticsBinding? = null
    //private val binding get() = _binding!!

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Show()
            }
        }
    }
    @Composable
    fun Show() {

        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasSize = size
            val canvasWidth = size.width
            val canvasHeight = size.height
            val w = canvasWidth / 12
            val h = canvasHeight / 3
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 200F, y = canvasHeight / 3F),
                //size = canvasSize / 5F
                size = Size(w, h)
            )

            drawRect(
                color = Color.Blue ,
                topLeft = Offset(x = 350F, y = canvasHeight / 3F),
                //size = canvasSize / 5F
                size = Size(w, h - h/4)
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 500F, y = canvasHeight / 3F),
                //size = canvasSize / 5F
                size = Size(w, h)
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 650F, y = canvasHeight / 3F),
                //size = canvasSize / 5F
                size = Size(w, h - h/6)
            )
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x = 800F, y = canvasHeight / 3F),
                //size = canvasSize / 5F
                size = Size(w, h - h/3)
            )
//            drawRect(
//                color = Color.Blue,
//                topLeft = Offset(x = canvasWidth - canvasWidth/3F, y = canvasHeight / 3F),
//                //size = canvasSize / 5F
//                size = Size(w, h)
//            )
//
//            drawRect(
//                color = Color.Red,
//                topLeft = Offset(x = canvasWidth - canvasWidth/2F, y = canvasHeight / 3F),
//                //size = canvasSize / 5F
//                size = Size(w, h)
//            )
//            drawRect(
//                color = Color.Green,
//                topLeft = Offset(x = canvasWidth - canvasWidth/1.5F, y = canvasHeight / 3F),
//                //size = canvasSize / 5F
//                size = Size(w, h)
//            )
//            drawRect(
//                color = Color.Yellow,
//                topLeft = Offset(x = canvasWidth - canvasWidth/1.3F, y = canvasHeight / 3F),
//                //size = canvasSize / 5F
//                size = Size(w, h)
//            )
//            drawRect(
//                color = Color.DarkGray,
//                topLeft = Offset(x = canvasWidth - canvasWidth/1.1F, y = canvasHeight / 3F),
//                //size = canvasSize / 5F
//                size = Size(w, h)
//            )


        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Show()
    }

}