package com.example.personalexpenditure.ui.fragments

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
import com.example.personalexpenditure.databinding.FragmentNewExpensesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewExpensesFragment : Fragment() {
    private lateinit var binding: FragmentNewExpensesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewExpensesBinding.inflate(inflater, container, false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#333333\">" + getString(R.string.expenses) + "</font>")));

        openCategoryFragment()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun openCategoryFragment() {
        binding.category.setOnClickListener {
            val action = NewExpensesFragmentDirections.actionNewExpensesFragmentToNewExpensesCategoryFragment()
            findNavController().navigate(action)
//            
        }
    }
}