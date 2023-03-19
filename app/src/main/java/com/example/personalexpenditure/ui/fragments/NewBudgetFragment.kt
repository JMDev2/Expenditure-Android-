package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewBudgetBinding
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewBudgetFragment : Fragment() {
    private val args: NewBudgetFragmentArgs by navArgs()
    private lateinit var binding: FragmentNewBudgetBinding
    private val viewModel: IncomePostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewBudgetBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#0000\">" + getString(R.string.budget) + "</font>")));


        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Log.d("NewBudgetFragment","passedIncome:${args.income}")


        postBudget(args.income)

    }

    private fun postBudget(income: Int) {
        binding.setBudget.setOnClickListener {
            val budget = binding.budgetText.text.toString()
            if (budget.isNotBlank()){
                Log.d("NewBudgetFragment","budget:${budget}")
                viewModel.postIncome(PostData(income, budget.toInt()))
                Toast.makeText(requireContext(), "saved Succesfully", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(requireContext(), "Input must not be null", Toast.LENGTH_LONG).show()

            }

        }
    }

}