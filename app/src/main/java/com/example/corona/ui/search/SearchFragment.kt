package com.example.corona.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import coil.load
import com.example.corona.MyApplication
import com.example.corona.R
import com.example.corona.databinding.FragmentSearchBinding
import com.example.corona.utils.formatNumber
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchButton.setOnClickListener {
            searchViewModel.getCountry(binding.outlinedTextField.editText?.text.toString())
        }
        searchViewModel.country.observe(viewLifecycleOwner, {
            if (it == null) {
                binding.noData.visibility = View.VISIBLE
                binding.cardView.visibility = View.GONE
            } else {
                binding.noData.visibility = View.GONE
                val imgUri = it.countryInfo.flag.toUri().buildUpon().scheme("https").build()
                binding.countryFlag.load(imgUri) {
                    placeholder(R.drawable.ic_blur)
                    error(R.drawable.ic_error)
                }
                binding.countryTitle.text = it.country
                binding.cases.text = context?.getString(
                    R.string.cases,
                    formatNumber(it.cases)
                )
                binding.todayCases.text = context?.getString(
                    R.string.today_cases,
                    formatNumber(it.todayCases)
                )
                binding.deaths.text = context?.getString(
                    R.string.deaths,
                    formatNumber(it.deaths)
                )
                binding.todayDeaths.text =
                    context?.getString(R.string.today_deaths, formatNumber(it.todayDeaths))
                binding.cardView.visibility = View.VISIBLE

            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }
}