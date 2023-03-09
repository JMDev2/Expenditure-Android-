package com.example.personalexpenditure.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentNewBudgetBinding


class NewBudgetFragment : Fragment() {
    private lateinit var binding: FragmentNewBudgetBinding

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

        openMainFragment()
    }

    //This ,method opens the main fragment
    private fun openMainFragment() {
        binding.imready.setOnClickListener {
            val mainFragment = MainFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, mainFragment)
            transaction.commit()
        }
    }
}