package com.example.personalexpenditure

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.personalexpenditure.adapter.OnBoardingViewPagerAdapter
import com.example.personalexpenditure.databinding.FragmentOnboardingBinding
import com.example.personalexpenditure.fragments.HomeFragment
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
        onboardingData()
        moveNext()
        skipNext()

    }

    private fun skipNext() {
        binding.skipText.setOnClickListener {
            val homeFragment = HomeFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, homeFragment)
            transaction.commit()

        }
    }

    private fun moveNext() {
        binding.moveNext.setOnClickListener {
            if (binding.viewpager.currentItem + 1 < onBoardingViewPagerAdapter!!.count){
                binding.viewpager.currentItem += 1
            }else{
                binding.moveNext.text = "Get Started"
                val homeFragment = HomeFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, homeFragment)
                transaction.commit()
            }
        }

    }

    private fun onboardingData() {
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Make a budget", "Make a monthly small budget to ensure\n" +
                "you are spending your money wisely. \n" +
                "This way you will be able to save", R.drawable.onboardingone))
        onBoardingData.add(OnBoardingData("Track your spending", "Easily keep track of your spending as\n" +
                "they happen. Have a record of how\n" +
                "you spend.", R.drawable.onboardingtwo))
        onBoardingData.add(OnBoardingData("Get rewarded", "For every penny you\n" +
                "save you will get 20% airtime reward", R.drawable.onboardingthree))
        Log.d("OnboardingFragment", onBoardingData.size.toString())

        setOnBoardingViewPagerAdapter(onBoardingData)
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){

        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(requireContext(), onBoardingData)
        viewPager = binding.viewpager
        viewPager?.adapter = onBoardingViewPagerAdapter
        binding.tabIndicator.setupWithViewPager(binding.viewpager)
    }

}