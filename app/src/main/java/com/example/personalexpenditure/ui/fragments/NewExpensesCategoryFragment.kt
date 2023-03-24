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
        postExpenditure()


    }

    private fun postExpenditure() {

        binding.done.setOnClickListener {

                val expenditure = buildExpenditure()
                viewModel.postExpenditure(args.id.toString(), expenditure)

              //  Toast.makeText(requireContext(), "saved Succesfully", Toast.LENGTH_LONG).show()

        }


    }
    fun captureExpenditure() : Int{
        val expenditure = binding.expenditureText.text.toString()
       return when(expenditure.isNotBlank()){
            true -> expenditure.toInt()
            false -> 0

        }

    }
    fun captureTransport(): Int{
        var transport: Int = 0
        binding.transportLinearLayout.setOnClickListener{
            transport = captureExpenditure()
              Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()
        }
        return transport
    }


    fun captureShopping() : Int{
        var shopping : Int = 0
        binding.shoppingLinearLayout.setOnClickListener {
            shopping = captureExpenditure()
        }
            return shopping
        }


    fun captureFood() : Int{
        var food : Int = 0
        binding.foodLinearLayout.setOnClickListener{
            food = captureExpenditure()
        }
            return food
        }

    fun captureEntertainment() : Int{
        var entertainment : Int = 0
        binding.EntertainmentLinearLayout.setOnClickListener{
            entertainment = captureExpenditure()
        }
            return entertainment
        }


    fun captureRent() : Int{
        var rent : Int = 0
//        binding.re.setOnClickListener{
//            rent = captureExpenditure()
//        }
            return rent;
    }

    fun captureFee() : Int{
        var fee : Int = 0
        binding.schoolFeeesLinearLayout.setOnClickListener {
            fee = captureExpenditure()
        }
            return fee

    }

    fun captureHealth() : Int{
        var health : Int = 0
        binding.healthLinearLayout.setOnClickListener {
            health = captureExpenditure()
        }
            return health
        }

    fun captureMisc() : Int{
        var misc : Int = 0
        binding.miscelleniousLayout.setOnClickListener{
            misc = captureExpenditure()

        }
        return misc;
    }


    fun buildExpenditure() : Expenditure{
        val transport = captureTransport()
        val shopping = captureShopping()
        val food = captureFood()
        val entertainment = captureEntertainment()
        val rent = captureRent()
        val fee = captureFee()
        val health = captureHealth()
        val misc = captureMisc()

        val postData = PostData(0,0)


        return Expenditure(entertainment, food, health, postData, rent, fee, shopping, transport)
    }



}