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
        dismiss()

    }

    private fun dismiss() {
        binding.dismiss.setOnClickListener {
            val action = CategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
            findNavController().navigate(action)
        }
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


            if (expenditure != null){
                viewModel.postExpenditure(userId = auth.currentUser!!.uid, expenditure)
//                    val action = CategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
//                    findNavController().navigate(action)
                Log.d("NewExpensesCategoryFragment", "Posting${expenditure}")
                Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()


            }else{
                Toast.makeText(requireActivity(), "invalid", Toast.LENGTH_SHORT).show()


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


        if (transport == null || transport == 0) {
            binding.tilTransport.error = "Transport is required and cannot be zero"
            binding.tilTransport.requestFocus()
        }
        if (entertainment == null || entertainment == 0) {
            binding.entertainmentText.error = "Entertainment is required and cannot be zero"
            binding.entertainmentText.requestFocus()

        }

        if (fee == null || fee == 0) {
            binding.tilFee.error = "Fee is required and cannot be zero"
            binding.tilFee.requestFocus()
        }

        if (food == null || food == 0) {
            binding.tilFood.error = "Food is required and cannot be zero"
            binding.tilFood.requestFocus()

        }

        if (shopping == null || shopping == 0) {
            binding.tilShopping.error = "Shopping is required and cannot be zero"
            binding.tilShopping.requestFocus()

        }

        if (rent == null || rent == 0) {
            binding.tilRent.error = "Rent is required and cannot be zero"
            binding.tilRent.requestFocus()

        }

        if (health == null || health == 0) {
            binding.tilHealth.error = "Health is required and cannot be zero"
            binding.tilHealth.requestFocus()

        }

        if (transport != null && entertainment != null && fee != null && food != null && shopping != null
            && rent != null && health != null) {
            return Expenditure(
                entertainment = entertainment,
                food = food,
                health = health,
                rent = rent,
                schoolFee = fee,
                shopping = shopping,
                transport = transport,

                )
        }


        return null
    }
//
//    private fun observeExpenditure() {
//        viewModel.observePostExpenditureLiveData().observe(
//            viewLifecycleOwner
//        ) { response ->
//            when (response.status) {
//                Status.SUCCESS -> {
//                    val expenditureId = response.data
//
//                    if (expenditureId != null){
////                        val action = CategoryFragmentDirections.actionTestCategoryFragmentToMainFragment()
////                        findNavController().navigate(action)
//
//                    }
//                }
//                // if error state
//                Status.ERROR -> {
////                    // TODO Dismiss progress dialog
////                    binding.progressBar.visibility = View.GONE
////                    // TODO Show error message in dialog.
////                    binding.constraint.visibility = View.GONE
////                    binding.errorText.visibility = View.VISIBLE
////                    binding.errorText.text = "set income"
//                }
//                // if still loading
//                Status.LOADING -> {
////                    binding.constraint.visibility = View.GONE
////                    binding.errorText.visibility = View.GONE
////                    binding.progressBar.visibility = View.VISIBLE
//
//                    // TODO Show progress dialog
//                }
//            }
//        }
//    }

}


