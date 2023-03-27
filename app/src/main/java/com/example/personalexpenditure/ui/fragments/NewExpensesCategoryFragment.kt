package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
    private val args: NewExpensesCategoryFragmentArgs by navArgs()

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

        Log.d("NewExpensesCategoryFragment", "id:${args.id}")
        cancelBtn()
        captureTransport()
        captureEntertainment()
        captureFee()
        captureFood()
        captureHealth()
        captureRent()
        captureShopping()
        postExpenditure()


    }

    private fun cancelBtn() {
        binding.cancel.setOnClickListener {
            binding.expenditureText.setText("")
        }
    }

    private fun postExpenditure() {
        binding.done.setOnClickListener {
            val expenditure = buildExpenditure()
            viewModel.postExpenditure(args.id.toString(), expenditure)
            Log.d("NewExpensesCategoryFragment", "expendi${expenditure}")
            Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()

            val action = NewExpensesCategoryFragmentDirections.actionNewExpensesCategoryFragmentToMainFragment()
            findNavController().navigate(action)

        }

    }
    fun captureExpenditure() : Int{
        val expenditure = binding.expenditureText.text.toString()
        Log.d("NewExpensesCategoryFragment", "expenditure${expenditure}")
       if (expenditure.isNotBlank()) {
           return expenditure.toInt()
       }
        else{
            return 0

        }
    }
    fun captureTransport(){
        val checkIcon = ImageView(requireContext()) // create a new ImageView
        checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
        checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible

        var isTransportSaved = false

        binding.transportLinearLayout.setOnClickListener {
            val transport = captureExpenditure()
            buildExpenditure(transport)

            Toast.makeText(requireContext(), " transportsaved", Toast.LENGTH_LONG).show()

            if (!isTransportSaved){
                checkIcon.visibility = View.VISIBLE
                binding.transportText.visibility = View.GONE
                binding.transportLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout

            }else{
                checkIcon.visibility = View.INVISIBLE
                binding.transportLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
                binding.expenditureText.setText("")
                binding.transportText.visibility = View.VISIBLE

            }
            isTransportSaved = !isTransportSaved
        }
    }

        fun captureShopping() {
            val checkIcon = ImageView(requireContext()) // create a new ImageView
            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible


            binding.shoppingLinearLayout.setOnClickListener {
               val shopping = captureExpenditure()
                buildExpenditure(shopping =shopping)

                Toast.makeText(requireContext(), "shoppping saved", Toast.LENGTH_LONG).show()
                checkIcon.visibility = View.VISIBLE
                binding.transportText.visibility = View.GONE
                binding.shoppingLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout

            }

        }


        fun captureFood(){
            binding.foodLinearLayout.setOnClickListener {
               val food = captureExpenditure()
                buildExpenditure(food = food)
                Toast.makeText(requireContext(), "food saved", Toast.LENGTH_LONG).show()
            }
        }

        fun captureEntertainment() {
            binding.EntertainmentLinearLayout.setOnClickListener {
               val entertainment = captureExpenditure()
                buildExpenditure(entertainment = entertainment)
                Toast.makeText(requireContext(), "Entertainment saved", Toast.LENGTH_LONG).show()
            }

        }


        fun captureRent() {
            binding.rentLayout.setOnClickListener {
               val rent = captureExpenditure()
                buildExpenditure(rent = rent)
                Toast.makeText(requireContext(), "rent saved", Toast.LENGTH_LONG).show()

            }
        }

        fun captureFee(){
            binding.schoolFeeesLinearLayout.setOnClickListener {
               val fee = captureExpenditure()
                buildExpenditure(fee = fee)
                Toast.makeText(requireContext(), "fee saved", Toast.LENGTH_LONG).show()

            }

        }

        fun captureHealth(){
            binding.healthLinearLayout.setOnClickListener {
              val health = captureExpenditure()
                buildExpenditure(health = health)
                Toast.makeText(requireContext(), "health saved", Toast.LENGTH_LONG).show()

            }

        }


        fun buildExpenditure(
            transport: Int? = null,
            entertainment: Int?= null,
            food: Int?= null,
            health: Int?= null,
            rent: Int?= null,
            fee: Int?= null,
            shopping: Int?= null

        ): Expenditure {
            var expenditure = Expenditure()

            val postData = PostData(0, 0)
            expenditure.transport = buildTransport(transport)
            expenditure.entertainment = buidlEntertainment(entertainment)
            expenditure.food = buildFood(food)
            expenditure.rent = buildRent(rent)
            expenditure.shopping = buildShopping(shopping)
            expenditure.health = buildHealth(health)
            expenditure.schoolFee = buildFee(fee)
            expenditure.postData = postData

            return expenditure
        }
    }

        fun buildTransport(transport : Int?): Int{
            if (transport != null){
                return transport
            }else{
                return 0
            }

        }

    fun buidlEntertainment(entertainment : Int?): Int{
        if (entertainment != null){
            return entertainment
        }else{
            return 0
        }
    }

   fun buildFee(fee : Int?): Int{
       if (fee != null){
           return fee
       }else{
           return 0
       }

   }


    fun buildFood(food : Int?): Int{
        if (food != null){
            return food
        }else{
            return 0
        }

    }

    fun buildHealth(health : Int?): Int{
        if (health != null){
            return health
        }else{
            return 0
        }
    }

    fun buildRent(rent : Int?): Int{
        if (rent != null){
            return rent
        }else{
            return 0
        }
    }

    fun buildShopping(shopping : Int?): Int{
        if (shopping != null){
            return shopping
        }else{
            return 0
        }
    }




