package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewExpensesCategoryBinding
import com.example.personalexpenditure.model.Expenditure
import com.example.personalexpenditure.model.PostData
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewExpensesCategoryFragment : Fragment() {
    private lateinit var binding: FragmentNewExpensesCategoryBinding
    private val viewModel: IncomePostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewExpensesCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#0000\">" + getString(R.string.expenses) + "</font>")));

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        assert(actionBar != null) // null check
        actionBar?.setDisplayHomeAsUpEnabled(true)


        postExpenditure()


    }

    private fun postExpenditure() {

        binding.transportLinearLayout.setOnClickListener {
            val transport = binding.expenditureText.text.toString()
            if (transport.isNotBlank()) {
                Log.d("NewExpensesCategoryFragment", "transport:${transport}")
           //     viewModel.postExpenditure(
//                    Expenditure(
//                        "2023-3-1",
//                        0,
//                        0,
//                        0,
//                        0,
//                        postData = PostData(0, 0),
//                        0,
//                        0,
//                        0,
//                        0
//                    )
                //)

                Toast.makeText(requireContext(), "saved Succesfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(), "Please indicate the amount spent", Toast.LENGTH_LONG).show()

            }
        }
    }


}