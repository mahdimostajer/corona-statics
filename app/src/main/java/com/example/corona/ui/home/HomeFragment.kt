package com.example.corona.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.corona.MyApplication
import com.example.corona.R
import com.example.corona.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val items = listOf(
            "cases", "todayCases", "deaths", "todayDeaths"
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        (binding.menu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.menu.editText?.addTextChangedListener {
            homeViewModel.setSortType(it.toString())
        }
        val countryAdapter = CountryAdapter()
        binding.countriesRecyclerview.adapter = countryAdapter
        homeViewModel.countries.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                countryAdapter.submitList(null);
                countryAdapter.submitList(it)
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