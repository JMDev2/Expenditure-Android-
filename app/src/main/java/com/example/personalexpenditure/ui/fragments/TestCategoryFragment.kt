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
import com.example.personalexpenditure.databinding.FragmentTestCategoryBinding
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestCategoryFragment : Fragment() {
    private lateinit var binding: FragmentTestCategoryBinding
    private val viewModel: IncomePostViewModel by viewModels()
    private val args: TestCategoryFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#0000\">" + getString(R.string.expenses) + "</font>")));

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        assert(actionBar != null) // null check
        actionBar?.setDisplayHomeAsUpEnabled(true)

        Log.d("NewExpensesCategoryFragment", "id:${args.id}")

        postExpenditure()
    }


    private fun postExpenditure() {
        binding.sendBtn.setOnClickListener {
            val expenditure = captureExpenditure()
            val incomeId = args.id.toString()

            viewModel.postExpenditure(incomeId, expenditure)
           Log.d("NewExpensesCategoryFragment", "Posting${expenditure}")
            Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()

            val action = TestCategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
            findNavController().navigate(action)

        }

    }

    private fun captureExpenditure() : Expenditure {
        val transport = binding.transport.text.toString().toIntOrNull() ?: 0
        val entertainment = binding.entertainment.text.toString().toIntOrNull() ?: 0
        val fee = binding.fee.text.toString().toIntOrNull() ?: 0
        val food = binding.food.text.toString().toIntOrNull() ?: 0
        val shopping = binding.shopping.text.toString().toIntOrNull() ?: 0
        val rent = binding.rent.text.toString().toIntOrNull() ?: 0
        val health = binding.health.text.toString().toIntOrNull() ?: 0

        Log.d("NewExpensesCategoryFragment", "Posting${transport}")
        Log.d("NewExpensesCategoryFragment", "Posting${entertainment}")
        Log.d("NewExpensesCategoryFragment", "Posting${fee}")
        Log.d("NewExpensesCategoryFragment", "Posting${food}")
        Log.d("NewExpensesCategoryFragment", "Posting${shopping}")
        Log.d("NewExpensesCategoryFragment", "Posting${rent}")
        Log.d("NewExpensesCategoryFragment", "Posting${health}")

        return Expenditure(
            entertainment = entertainment,
            food = food,
            health = health,
            postData = PostData(0, 0),
            rent = rent,
            schoolFee = fee,
            shopping = shopping,
            transport = transport,


            )


    }

}


