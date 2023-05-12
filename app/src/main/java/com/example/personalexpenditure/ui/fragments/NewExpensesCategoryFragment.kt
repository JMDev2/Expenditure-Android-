package com.example.personalexpenditure.ui.fragments

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewExpensesCategoryFragment : Fragment() {
//    private lateinit var binding: FragmentNewExpensesCategoryBinding
//    private val viewModel: IncomePostViewModel by viewModels()
//    private val args: NewExpensesCategoryFragmentArgs by navArgs()
//
//    private var transportClicked = false
//    private var shoppingClicked = false
//    private var feeClicked = false
//    private var foodClicked = false
//    private var entertainmentClicked = false
//    private var healthClicked = false
//    private var rentClicked = false
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentNewExpensesCategoryBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //actionbar
//        activity?.setTitle((Html.fromHtml("<font color=\"#0000\">" + getString(R.string.expenses) + "</font>")));
//
//        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
//        assert(actionBar != null) // null check
//        actionBar?.setDisplayHomeAsUpEnabled(true)
//        displayDate()
//
//        Log.d("NewExpensesCategoryFragment", "id:${args.id}")
//        cancelBtn()
//        captureTransport()
//        captureEntertainment()
//        captureFee()
//        captureFood()
//        captureHealth()
//        captureRent()
//        captureShopping()
//        postExpenditure()
//
//    }
//    private fun displayDate() {
//        val currentDate = Date()
//        val dateFormat = SimpleDateFormat("d, EEEE, yyyy", Locale.getDefault())
//        val formattedDate = dateFormat.format(currentDate)
//        binding.dateText.text = formattedDate
//    }
//
//    private fun cancelBtn() {
//        binding.cancel.setOnClickListener {
//            binding.expenditureText.setText("")
//        }
//    }
//
//    private fun postExpenditure() {
//        binding.done.setOnClickListener {
//            val input = captureExpenditure()
//            var expenditure = Expenditure()
//            if (transportClicked){
//                expenditure.transport = input
//            }
//
//            if (shoppingClicked){
//                expenditure.shopping = input
//            }
//            if(healthClicked){
//                expenditure.health= input
//            }
//            if (feeClicked){
//                expenditure.schoolFee = input
//            }
//            if (foodClicked){
//                expenditure.food = input
//            }
//            if (entertainmentClicked){
//                expenditure.entertainment = input
//            }
//            if (rentClicked){
//                expenditure.rent = input
//            }
//            viewModel.postExpenditure(args.id.toString(), expenditure)
//            Log.d("NewExpensesCategoryFragment", "Posting${expenditure}")
//            Toast.makeText(requireContext(), "saved", Toast.LENGTH_LONG).show()
//
//            val action = NewExpensesCategoryFragmentDirections.actionNewExpensesCategoryFragmentToMainFragment()
//            findNavController().navigate(action)
//
//        }
//
//    }
//    fun captureExpenditure() : Int{
//        val expenditure = binding.expenditureText.text.toString()
//        Log.d("NewExpensesCategoryFragment", "expenditure${expenditure}")
//       if (expenditure.isNotBlank()) {
//           return expenditure.toInt()
//       }
//        else{
//            return 0
//
//        }
//    }
//    fun captureTransport(){
//        val checkIcon = ImageView(requireContext()) // create a new ImageView
//        checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//        checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//        binding.transportLinearLayout.setOnClickListener {
//            transportClicked = !transportClicked
//            if (transportClicked){
//                checkIcon.visibility = View.VISIBLE
//                binding.transportText.visibility = View.GONE
//                binding.transportLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                binding.shoppingLinearLayout.isClickable = false
//                binding.healthLinearLayout.isClickable = false
//                binding.schoolFeeesLinearLayout.isClickable = false
//                binding.rentLayout.isClickable = false
//                binding.EntertainmentLinearLayout.isClickable = false
//                binding.foodLinearLayout.isClickable = false
//            }else{
//                checkIcon.visibility = View.INVISIBLE
//                binding.transportLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                binding.expenditureText.setText("")
//                binding.transportText.visibility = View.VISIBLE
//
//            }
//
//        }
//    }
//
//        fun captureShopping() {
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.shoppingLinearLayout.setOnClickListener {
//               shoppingClicked = !shoppingClicked
//                if (shoppingClicked) {
//                    checkIcon.visibility = View.VISIBLE
//                    binding.shoppingText.visibility = View.GONE
//                    binding.shoppingLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                    binding.transportLinearLayout.isClickable = false
//                    binding.healthLinearLayout.isClickable = false
//                    binding.schoolFeeesLinearLayout.isClickable = false
//                    binding.rentLayout.isClickable = false
//                    binding.EntertainmentLinearLayout.isClickable = false
//                    binding.foodLinearLayout.isClickable = false
//
//                }else{
//                    checkIcon.visibility = View.INVISIBLE
//                    binding.shoppingLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                    binding.expenditureText.setText("")
//                    binding.shoppingText.visibility = View.VISIBLE
//
//                }
//
//            }
//
//        }
//
//        fun captureFood(){
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.foodLinearLayout.setOnClickListener {
//                foodClicked = !foodClicked
//               if (foodClicked){
//                   checkIcon.visibility = View.VISIBLE
//                   binding.foodText.visibility = View.GONE
//                   binding.foodLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                   binding.transportLinearLayout.isClickable = false
//                   binding.healthLinearLayout.isClickable = false
//                   binding.schoolFeeesLinearLayout.isClickable = false
//                   binding.rentLayout.isClickable = false
//                   binding.EntertainmentLinearLayout.isClickable = false
//                   binding.shoppingLinearLayout.isClickable = false
//               }else{
//                   checkIcon.visibility = View.INVISIBLE
//                   binding.foodLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                   binding.expenditureText.setText("")
//                   binding.foodText.visibility = View.VISIBLE
//               }
//            }
//        }
//
//        fun captureEntertainment() {
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.EntertainmentLinearLayout.setOnClickListener {
//                entertainmentClicked = !entertainmentClicked
//                if (entertainmentClicked){
//                    checkIcon.visibility = View.VISIBLE
//                    binding.entertainmentText.visibility = View.GONE
//                    binding.EntertainmentLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                    binding.transportLinearLayout.isClickable = false
//                    binding.healthLinearLayout.isClickable = false
//                    binding.schoolFeeesLinearLayout.isClickable = false
//                    binding.rentLayout.isClickable = false
//                    binding.shoppingLinearLayout.isClickable = false
//                    binding.foodLinearLayout.isClickable = false
//                }else{
//                    checkIcon.visibility = View.INVISIBLE
//                    binding.EntertainmentLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                    binding.expenditureText.setText("")
//                    binding.entertainmentText.visibility = View.VISIBLE
//                }
//            }
//
//        }
//
//        fun captureRent() {
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.rentLayout.setOnClickListener {
//               rentClicked = !rentClicked
//                if (rentClicked){
//                    checkIcon.visibility = View.VISIBLE
//                    binding.rentText.visibility = View.GONE
//                    binding.rentLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                    binding.transportLinearLayout.isClickable = false
//                    binding.healthLinearLayout.isClickable = false
//                    binding.schoolFeeesLinearLayout.isClickable = false
//                    binding.shoppingLinearLayout.isClickable = false
//                    binding.EntertainmentLinearLayout.isClickable = false
//                    binding.foodLinearLayout.isClickable = false
//                }else{
//                    checkIcon.visibility = View.INVISIBLE
//                    binding.rentLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                    binding.expenditureText.setText("")
//                    binding.rentText.visibility = View.VISIBLE
//                }
//
//                Toast.makeText(requireContext(), "rent saved", Toast.LENGTH_LONG).show()
//
//            }
//        }
//
//        fun captureFee(){
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.schoolFeeesLinearLayout.setOnClickListener {
//               feeClicked = !feeClicked
//                if (feeClicked){
//                    checkIcon.visibility = View.VISIBLE
//                    binding.schoolFeeesText.visibility = View.GONE
//                    binding.schoolFeeesLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                    binding.transportLinearLayout.isClickable = false
//                    binding.healthLinearLayout.isClickable = false
//                    binding.shoppingLinearLayout.isClickable = false
//                    binding.rentLayout.isClickable = false
//                    binding.EntertainmentLinearLayout.isClickable = false
//                    binding.foodLinearLayout.isClickable = false
//                }else{
//                    checkIcon.visibility = View.INVISIBLE
//                    binding.schoolFeeesLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                    binding.expenditureText.setText("")
//                    binding.schoolFeeesText.visibility = View.VISIBLE
//                }
//
//
//            }
//
//        }
//
//        fun captureHealth(){
//            val checkIcon = ImageView(requireContext()) // create a new ImageView
//            checkIcon.setImageResource(R.drawable.ic_baseline_check_circle_24) // set the icon drawable
//            checkIcon.visibility = View.INVISIBLE // initially set the visibility to invisible
//
//            binding.healthLinearLayout.setOnClickListener {
//              healthClicked = !healthClicked
//                if (healthClicked){
//                    checkIcon.visibility = View.VISIBLE
//                    binding.healthText.visibility = View.GONE
//                    binding.healthLinearLayout.addView(checkIcon) // add the ImageView to the LinearLayout
//
//                    binding.transportLinearLayout.isClickable = false
//                    binding.shoppingLinearLayout.isClickable = false
//                    binding.schoolFeeesLinearLayout.isClickable = false
//                    binding.rentLayout.isClickable = false
//                    binding.EntertainmentLinearLayout.isClickable = false
//                    binding.foodLinearLayout.isClickable = false
//                }else{
//                    checkIcon.visibility = View.INVISIBLE
//                    binding.healthLinearLayout.removeView(checkIcon) // remove the ImageView from the LinearLayout
//                    binding.expenditureText.setText("")
//                    binding.healthText.visibility = View.VISIBLE
//                }
//
//                Toast.makeText(requireContext(), "health saved", Toast.LENGTH_LONG).show()
//
//            }
//
//        }
//
//    }
//
//        fun buildTransport(transport : Int): Int{
//
//                return transport
//
//        }
//
//    fun buidlEntertainment(entertainment : Int?): Int{
//        if (entertainment != null){
//            return entertainment
//        }else{
//            return 0
//        }
//    }
//
//   fun buildFee(fee : Int?): Int{
//       if (fee != null){
//           return fee
//       }else{
//           return 0
//       }
//
//   }
//
//
//    fun buildFood(food : Int?): Int{
//        if (food != null){
//            return food
//        }else{
//            return 0
//        }
//
//    }
//
//    fun buildHealth(health : Int?): Int{
//        if (health != null){
//            return health
//        }else{
//            return 0
//        }
//    }
//
//    fun buildRent(rent : Int?): Int{
//        if (rent != null){
//            return rent
//        }else{
//            return 0
//        }
//    }
//
//    fun buildShopping(shopping : Int?): Int{
//        if (shopping != null){
//            return shopping
//        }else{
//            return 0
//        }
    }




