package com.example.personalexpenditure.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewIncomeBinding


class NewIncomeFragment : Fragment() {

    private lateinit var binding: FragmentNewIncomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#333333\">" + getString(R.string.newincome) + "</font>")));
        openBudgetFragment()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    //This method opens the budget fragment
    private fun openBudgetFragment() {
        binding.setBudget.setOnClickListener {
            val newBudgetFragment = NewBudgetFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, newBudgetFragment)
            transaction.addToBackStack("")
            transaction.commit()
        }
    }


}