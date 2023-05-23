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
import com.example.personalexpenditure.databinding.FragmentcategoryBinding
import com.example.personalexpenditure.model.Expenditure


import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.IncomePostViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentcategoryBinding
    private val viewModel: IncomePostViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentcategoryBinding.inflate(inflater, container, false)

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
        auth = FirebaseAuth.getInstance()

        postExpenditure()
       // observeExpenditure()
        displayDate()


    }



    private fun displayDate() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d, EEEE, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateText.text = formattedDate
    }


    private fun postExpenditure() {
        binding.sendBtn.setOnClickListener {
            val expenditure = captureExpenditure()

            if (isExpenditureValid(expenditure)) {
                if (expenditure != null) {
                    viewModel.postExpenditure(userId = auth.currentUser!!.uid, expenditure)
                }
                val action = CategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
                findNavController().navigate(action)
                Log.d("NewExpensesCategoryFragment", "Posting $expenditure")
            } else {
                Toast.makeText(requireActivity(), "Please fill all the required fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun captureExpenditure(): Expenditure? {
        val transport = binding.etTransport.text.toString().toIntOrNull() ?: 0
        val entertainment = binding.entertainmentText.text.toString().toIntOrNull() ?: 0
        val fee = binding.etFee.text.toString().toIntOrNull() ?: 0
        val food = binding.etFood.text.toString().toIntOrNull() ?: 0
        val shopping = binding.etShopping.text.toString().toIntOrNull() ?: 0
        val rent = binding.etRent.text.toString().toIntOrNull() ?: 0
        val health = binding.etHealth.text.toString().toIntOrNull() ?: 0

        if (transport == 0) {
            binding.tilTransport.error = "Transport is required and cannot be zero"
            binding.tilTransport.requestFocus()
            return null
        }

        if (entertainment == 0) {
            binding.entertainmentText.error = "Entertainment is required and cannot be zero"
            binding.entertainmentText.requestFocus()
            return null
        }

        if (fee == 0) {
            binding.tilFee.error = "Fee is required and cannot be zero"
            binding.tilFee.requestFocus()
            return null
        }

        if (food == 0) {
            binding.tilFood.error = "Food is required and cannot be zero"
            binding.tilFood.requestFocus()
            return null
        }

        if (shopping == 0) {
            binding.tilShopping.error = "Shopping is required and cannot be zero"
            binding.tilShopping.requestFocus()
            return null
        }

        if (rent == 0) {
            binding.tilRent.error = "Rent is required and cannot be zero"
            binding.tilRent.requestFocus()
            return null
        }

        if (health == 0) {
            binding.tilHealth.error = "Health is required and cannot be zero"
            binding.tilHealth.requestFocus()
            return null
        }

        return Expenditure(
            entertainment = entertainment,
            food = food,
            health = health,
            rent = rent,
            schoolFee = fee,
            shopping = shopping,
            transport = transport
        )
    }

    private fun isExpenditureValid(expenditure: Expenditure?): Boolean {
        return expenditure != null
    }



}


