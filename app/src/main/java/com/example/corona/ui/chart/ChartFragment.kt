package com.example.corona.ui.chart

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corona.MyApplication
import com.example.corona.R
import com.example.corona.databinding.FragmentChartBinding
import com.example.corona.utils.distanceBetweenDates
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import javax.inject.Inject
import kotlin.math.abs


class ChartFragment : Fragment() {

    @Inject
    lateinit var chartViewModel: ChartViewModel
    private var _binding: FragmentChartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentChartBinding.inflate(inflater, container, false)

        val entries = ArrayList<Entry>()
        chartViewModel.history.observe(viewLifecycleOwner, {
            if (it != null) {
                for ((key, value) in it.cases) {
                    entries.add(Entry(abs(30 - distanceBetweenDates(key)), value.toFloat()))
                    Log.d("chartFragment", abs(30 - distanceBetweenDates(key)).toString())

                }
                Log.d("chartFragment", sortEntries(entries).toString())
                val vl = LineDataSet(sortEntries(entries), "cases")

                vl.setDrawValues(false)
                vl.setDrawFilled(true)
                vl.lineWidth = 3f
                vl.fillColor = R.color.gray
                vl.fillAlpha = R.color.red

                binding.lineChart.xAxis.labelRotationAngle = 0f

                binding.lineChart.data = LineData(vl)

                binding.lineChart.axisRight.isEnabled = false
                binding.lineChart.xAxis.axisMaximum = 30f + 0.1f

                binding.lineChart.setTouchEnabled(true)
                binding.lineChart.setPinchZoom(true)

                binding.lineChart.description.text = "Days"
                binding.lineChart.setNoDataText("No forex yet!")

                binding.lineChart.animateX(1800, Easing.EaseInExpo)

                val markerView = CustomMarker(requireContext(), R.layout.marker_view)
                binding.lineChart.marker = markerView
            }
        })


        return binding.root
    }

    fun sortEntries(entries: ArrayList<Entry>): ArrayList<Entry> {
        val comparator = Comparator { o1: Entry, o2: Entry ->
            return@Comparator (o1.x - o2.x).toInt()
        }
        val copy = arrayListOf<Entry>().apply { addAll(entries) }
        copy.sortWith(comparator)
        return copy
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
}