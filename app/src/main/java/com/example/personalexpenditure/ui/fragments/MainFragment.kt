package com.example.personalexpenditure.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.databinding.FragmentMainBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.personalexpenditure.R
import com.example.personalexpenditure.model.User
import com.example.personalexpenditure.testfragment.BlankFragment
import com.example.personalexpenditure.utils.Status
import com.example.personalexpenditure.viewmodels.GetIncomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {
   // private lateinit var navController: NavController

    private lateinit var binding: FragmentMainBinding
    private val viewModel: GetIncomeViewModel by viewModels()
    private val args: MainFragmentArgs by navArgs()
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: User
    private lateinit var uid: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid
        databaseReference = FirebaseDatabase
            .getInstance()
            .getReference("users")

        openIncome()
        openExpenses()
        underLine()


        home()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        setupOnBackPressedCallback()


       // Log.d("MainFragment","checkexpenditureId:${args.expenditureId}")
       // viewModel.getExpenditure(args.expenditureId.toString())

        viewModel.getTotalExpenditure(auth.currentUser!!.uid) //call this when no init method in viewmodel
       // viewModel.getExpenditure(auth.currentUser!!.uid)
       // viewModel.getTotalIncome(auth.currentUser!!.uid)
        Log.d("MainFragment - sabsabsa","incomeIdToHome mainaF:${args.incomeIdToHome}")

       // viewModel.getTotalIncome(auth.currentUser!!.uid)

        observeIncome()
        observeExpenditure()
        displayDate()

        if (uid.isNotEmpty()){
            getUserData()
        }

    }

    private fun underLine() {
        binding.incomeTv.paintFlags = binding.incomeTv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.expensesTv.paintFlags = binding.expensesTv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun getUserData() {
        try {
            databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    Log.d("mainFragment", "DataSnapshot value: $snapshot")
                    user = snapshot.getValue(User::class.java) ?: return
                    binding.name.text = "Hello ${user.name},"

                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error.

                }

            })
        } catch (e: Exception) {
            // Handle the error.
            println("Error: $e")
        }
    }
    private fun displayDate() {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("d yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
        binding.dateText.text = formattedDate
    }

    private fun observeIncome() {
        viewModel.observeTotalExpenditureLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    // val total = response.data
                    val totalIncome = response.data?.income


                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE

                    if (totalIncome != null) {
                        binding.constraint.visibility = View.VISIBLE
                        binding.income.text = totalIncome.income.toString()
                        Log.d("MainFragment", "income ${totalIncome.income}")
                    } else {
                        binding.constraint.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                        binding.errorText.text = "Set income"
                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    binding.progressBar.visibility = View.GONE
                    // TODO Show error message in dialog.
                    binding.constraint.visibility = View.GONE
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()

                }
                // if still loading
                Status.LOADING -> {
                    binding.constraint.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                    // TODO Show progress dialog
                }
            }
        }
    }

    private fun observeExpenditure() {
        viewModel.observeTotalExpenditureLiveData().observe(
            viewLifecycleOwner
        ) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    val res = response.data?.expenditure
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE

                    res?.let {
                        binding.constraint.visibility = View.VISIBLE
                        binding.expenses.text = res.total.toString()
                        binding.rentPercentage.text = String.format("%.2f", res.rentPercentage )
                        binding.foodPercentage.text = String.format("%.2f", res.foodPercentage)
                        binding.healthPercentage.text = String.format("%.2f", res.healthPercentage)
                        binding.entertainmentPercentage.text = String.format("%.2f", res.entertainmentPercentage)
                        binding.transportPercentage.text = String.format("%.2f", res.transportPercentage)
                        binding.feePercentage.text = String.format("%.2f", res.schoolFeePercentage)
                        binding.shoppingPercentage.text = String.format("%.2f", res.shoppingPercentage)
                        // Log.d("MainFragment", "expendureId ${it.id}")
                        Log.d("MainFragment11", "totalExpenditure ${res.total}")

                    }
                }
                // if error state
                Status.ERROR -> {
                    // TODO Dismiss progress dialog
                    binding.progressBar.visibility = View.GONE
                    // TODO Show error message in dialog.
                    binding.constraint.visibility = View.GONE

                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG)
//                        .show()
                }
                // if still loading
                Status.LOADING -> {
                    binding.constraint.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE

                    // TODO Show progress dialog
                }
            }
        }
    }




    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing to prevent navigating back to the onboarding screen
            }
        })
    }

    private fun home() {

        binding.apply {
            home.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                    val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                    findNavController().navigate(action)
                }
            hospital.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }
            transport.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }
            entertainmentImage.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }
            education.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }
            food.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }

            glocery.setOnClickListener {
                val blankFragment = BlankFragment()
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fill_fragment, blankFragment)
                transaction.commit()
//                val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
//                findNavController().navigate(action)

            }
            userProfile.setOnClickListener {

                val action = MainFragmentDirections.actionMainFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }

        }


    private fun openIncome() {
        binding.incomeLinearLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToNewIncomeFragment()
            findNavController().navigate(action)
        }

    }

    private fun openExpenses() {
        binding.expenseLinearLayout.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToTestCategoryFragment()
            findNavController().navigate(action)
           // Toast.makeText(activity, "Error logging in", Toast.LENGTH_SHORT).show()

        }

    }

}