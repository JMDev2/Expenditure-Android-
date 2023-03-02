package com.example.personalexpenditure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.personalexpenditure.adapter.OnBoardingViewPagerAdapter
import com.example.personalexpenditure.databinding.FragmentOnboardingBinding
import com.example.personalexpenditure.model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    var onBoardingViewPagerAdapter : OnBoardingViewPagerAdapter? = null
    val tabLayout : TabLayout? = null
    private var viewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(inflater, container, false )

        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Miana", "he is a cool guy", R.drawable.download))
        onBoardingData.add(OnBoardingData("Joseph", "he is a cool guy", R.drawable.download))
        onBoardingData.add(OnBoardingData("Mwangi", "he is a cool guy", R.drawable.download))

        setOnBoardingViewPagerAdapter(onBoardingData)
    }


    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(requireContext(), onBoardingData)
        viewPager?.adapter = onBoardingViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.viewpager)
    }

}