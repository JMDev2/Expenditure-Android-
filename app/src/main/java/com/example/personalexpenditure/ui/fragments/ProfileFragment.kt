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
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentProfileBinding
import com.example.personalexpenditure.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: User
    private lateinit var uid: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //actionbar
        activity?.setTitle((Html.fromHtml("<font color=\"#333333\">" + getString(R.string.profile) + "</font>")));
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid

        databaseReference = FirebaseDatabase
            .getInstance()
            .getReference("users")

        if (uid.isNotEmpty()){
            getUserData()
        }
    }

    private fun getUserData() {
        binding.progressBarprofile.visibility = View.VISIBLE
        try {
            databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    binding.progressBarprofile.visibility = View.GONE
                    Log.d("profilenFragment", "DataSnapshot value: $snapshot")
                    user = snapshot.getValue(User::class.java) ?: return
                    binding.profilename.text = user.name
                    binding.profileEmail.text = user.email
                    binding.profilePhone.text = user.phone
                    Log.d("profilenFragment","name:${user.name}")
                    Log.d("profilenFragment","name:${user.email}")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error.
                    binding.progressBarprofile.visibility = View.GONE
                    Toast.makeText(activity, "Error loading", Toast.LENGTH_SHORT).show()
                }

            })
        } catch (e: Exception) {
            // Handle the error.
            println("Error: $e")
        }


}


    private fun imageClicks() {
        binding.apply {
            categoryLayout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToNewExpensesCategoryFragment()
                findNavController().navigate(action)
            }
        }
    }


}