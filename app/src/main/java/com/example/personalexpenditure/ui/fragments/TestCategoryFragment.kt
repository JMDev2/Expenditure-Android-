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
        validation()
    }




    private fun postExpenditure() {
        val isClicked = true
        binding.sendBtn.setOnClickListener {

           try {
               val expenditure = captureExpenditure()
               val incomeId = args.id.toString()

               viewModel.postExpenditure(incomeId, expenditure)
               Log.d("NewExpensesCategoryFragment", "Posting${expenditure}")
               Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()

               val action =
                   TestCategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
               findNavController().navigate(action)
           }catch (e: Exception) {
               Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
           }
        }

    }

    private fun captureExpenditure(): Expenditure {
        val transport = binding.transport.text.toString().toIntOrNull() ?: 0

        val entertainment = binding.entertainmentText.text.toString().toIntOrNull() ?: 0
        val fee = binding.fee.text.toString().toIntOrNull() ?: 0
        val food = binding.food.text.toString().toIntOrNull() ?: 0
        val shopping = binding.shopping.text.toString().toIntOrNull() ?: 0
        val rent = binding.rent.text.toString().toIntOrNull() ?: 0
        val health = binding.health.text.toString().toIntOrNull() ?: 0


        if (transport == null || transport == 0) {
            binding.transport.error = "Transport is required and cannot be zero"
            binding.transport.requestFocus()
            return Expenditure()
        }
        if (entertainment == null || entertainment == 0) {
            binding.entertainmentText.error = "Entertainment is required and cannot be zero"
            binding.entertainmentText.requestFocus()
            return Expenditure()
        }

        if (fee == null || fee == 0) {
            binding.fee.error = "Fee is required and cannot be zero"
            binding.fee.requestFocus()
            return Expenditure()
        }

        if (food == null || food == 0) {
            binding.food.error = "Food is required and cannot be zero"
            binding.food.requestFocus()
            return Expenditure()
        }

        if (shopping == null || shopping == 0) {
            binding.shopping.error = "Shopping is required and cannot be zero"
            binding.shopping.requestFocus()
            return Expenditure()
        }

        if (rent == null || rent == 0) {
            binding.rent.error = "Rent is required and cannot be zero"
            binding.rent.requestFocus()
            return Expenditure()
        }

        if (health == null || health == 0) {
            binding.health.error = "Health is required and cannot be zero"
            binding.health.requestFocus()
            return Expenditure()
        }

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
    private fun validation() {

    }



}


