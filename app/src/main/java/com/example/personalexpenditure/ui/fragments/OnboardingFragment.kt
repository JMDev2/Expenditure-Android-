package com.example.personalexpenditure.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.personalexpenditure.R
import com.example.personalexpenditure.adapter.OnBoardingViewPagerAdapter
import com.example.personalexpenditure.databinding.FragmentOnboardingBinding
import com.example.personalexpenditure.model.OnBoardingData
import com.example.personalexpenditure.utils.SharedPreferences
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    val firebaseAuth = FirebaseAuth.getInstance()


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
        activity?.setTitle("")
        onboardingData()
        moveNext()
        skipNext()

        setupOnBackPressedCallback()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false) //remove the onback stack arrow


    }



    //disable the onback ack
    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing to prevent navigating back to the onboarding screen
            }
        })
    }
    private fun skipNext() {
        binding.skipText.setOnClickListener {
            SharedPreferences.setOnboardingStatus(requireActivity(), true)
            Log.d("skip button click", SharedPreferences.getOnboardingStatus(requireActivity()).toString())
            val action = OnboardingFragmentDirections.actionOnboardingFragmentToSignUpFragment()
            findNavController().navigate(action)

        }
    }

    private fun moveNext() {
        // set page change listener
        binding.viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            private var settled = false
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == onBoardingViewPagerAdapter!!.count - 1) {
                    binding.moveNext.text = "Get Started"
                    binding.moveNext.setOnClickListener {
                        Log.d("get started button click", SharedPreferences.getOnboardingStatus(requireActivity()).toString())
                        SharedPreferences.setOnboardingStatus(requireActivity(), true)
                        val action = OnboardingFragmentDirections.actionOnboardingFragmentToSignUpFragment()
                        findNavController().navigate(action)
                    }
                } else {
                    binding.moveNext.text = "Next"
                    binding.moveNext.setOnClickListener {
                        Log.d("next button click", SharedPreferences.getOnboardingStatus(requireActivity()).toString())
                        SharedPreferences.setOnboardingStatus(requireActivity(), true)
                        binding.viewpager.currentItem += 1
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }


        })

    }

    private fun onboardingData() {
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Make a budget", "Make a monthly small budget to ensure\n" +
                "you are spending your money wisely. \n" +
                "This way you will be able to save", R.drawable.onboardingone
        ))
        onBoardingData.add(OnBoardingData("Track your spending", "Easily keep track of your spending as\n" +
                "they happen. Have a record of how\n" +
                "you spend.", R.drawable.onboardingtwo
        ))
        onBoardingData.add(OnBoardingData("Get rewarded", "For every penny you\n" +
                "save you will get 20% airtime reward", R.drawable.onboardingthree
        ))
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