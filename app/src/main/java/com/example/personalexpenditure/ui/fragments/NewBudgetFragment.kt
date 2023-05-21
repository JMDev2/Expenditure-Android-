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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewBudgetBinding
import com.example.personalexpenditure.model.Income


import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NewBudgetFragment : Fragment() {
    private val args: NewBudgetFragmentArgs by navArgs()
    private lateinit var binding: FragmentNewBudgetBinding
    private val viewModel: IncomePostViewModel by viewModels()
    private lateinit var auth : FirebaseAuth

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
        auth = FirebaseAuth.getInstance()

        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#0000\">" + getString(R.string.budget) + "</font>")));


        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Log.d("NewBudgetFragment","passedIncome:${args.income}")


        displayDate()

        postBudget(userId = auth.currentUser!!.uid, args.income)
        //observeExpenditurePost()
        cancelBtn()


    }

    private fun displayDate() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d, EEEE, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateText.text = formattedDate
    }
    private fun cancelBtn() {
        binding.cancel.setOnClickListener {
            binding.budgetText.setText("")
        }
    }

    private fun postBudget(userId: String, income: Int) {
        binding.setBudget.setOnClickListener {
            val budget = binding.budgetText.text.toString()
            if (budget.isNotBlank() && budget.toInt() <= income) {
                Log.d("NewBudgetFragment", "budget: $budget")
                Log.d("NewBudgetFragment", "income: $income")
                viewModel.postIncome(userId, Income(income, budget.toInt()))

                val action = NewBudgetFragmentDirections.actionNewBudgetFragmentToMainFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Invalid budget value", Toast.LENGTH_LONG).show()
            }
        }
    }



//    private fun observeExpenditurePost() {
//        viewModel.observePostIncomeLiveData().observe(
//            viewLifecycleOwner
//        ){ response ->
//            when (response.status) {
//                Status.SUCCESS ->{
//                    val response = response.data
//
//                    if (response != null){
//
//                    }
//
//                    Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()
//                    Log.d("NewBudgetFragment","incomeIdToHomeId: ${response}")
//
//
//                }
//                Status.ERROR ->{
//                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG)
//                        .show()
//
//                }
//                Status.LOADING ->{
//
//                }
//
//            }
//        }
//
//    }
}