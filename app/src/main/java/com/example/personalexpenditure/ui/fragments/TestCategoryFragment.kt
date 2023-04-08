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
import com.example.personalexpenditure.utils.Status
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
        observeExpenditure()

    }


    private fun postExpenditure() {
        binding.sendBtn.setOnClickListener {


                val expenditure = captureExpenditure()
                val incomeId = args.id.toString()

                if (expenditure != null){
                    viewModel.postExpenditure(incomeId, expenditure)
                    Log.d("NewExpensesCategoryFragment", "Posting${expenditure}")
                    Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()


                }else{
                    Toast.makeText(requireActivity(), "invalid", Toast.LENGTH_SHORT).show()


            }
        }

    }

    private fun captureExpenditure(): Expenditure? {
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
        }
        if (entertainment == null || entertainment == 0) {
            binding.entertainmentText.error = "Entertainment is required and cannot be zero"
            binding.entertainmentText.requestFocus()

        }

        if (fee == null || fee == 0) {
            binding.fee.error = "Fee is required and cannot be zero"
            binding.fee.requestFocus()
        }

        if (food == null || food == 0) {
            binding.food.error = "Food is required and cannot be zero"
            binding.food.requestFocus()

        }

        if (shopping == null || shopping == 0) {
            binding.shopping.error = "Shopping is required and cannot be zero"
            binding.shopping.requestFocus()

        }

        if (rent == null || rent == 0) {
            binding.rent.error = "Rent is required and cannot be zero"
            binding.rent.requestFocus()

        }

        if (health == null || health == 0) {
            binding.health.error = "Health is required and cannot be zero"
            binding.health.requestFocus()

        }

        if (transport != null && entertainment != null && fee != null && food != null && shopping != null
            && rent != null && health != null) {
            return Expenditure(
                entertainment = entertainment,
                food = food,
                health = health,
                postData = PostData(0, 0),
                rent = rent,
                schoolFee = fee,
                shopping = shopping,
                transport = transport
            )
        }


        return null
    }

    private fun observeExpenditure() {
        viewModel.observePostExpenditureLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    val expenditureId = response.data?.id

                    Log.d("TestCategoryFragment", "ID${expenditureId}")
                    if (expenditureId != null){
                        val action =
                            TestCategoryFragmentDirections.actionTestCategoryFragmentToMainFragment(expenditureId = expenditureId)
                        findNavController().navigate(action)
                        Log.d("TestCategoryFragment", "ID1:${expenditureId}")
                    }
                }
                // if error state
                Status.ERROR -> {
//                    // TODO Dismiss progress dialog
//                    binding.progressBar.visibility = View.GONE
//                    // TODO Show error message in dialog.
//                    binding.constraint.visibility = View.GONE
//                    binding.errorText.visibility = View.VISIBLE
//                    binding.errorText.text = "set income"
                }
                // if still loading
                Status.LOADING -> {
//                    binding.constraint.visibility = View.GONE
//                    binding.errorText.visibility = View.GONE
//                    binding.progressBar.visibility = View.VISIBLE

                    // TODO Show progress dialog
                }
            }
        }
    }

}


