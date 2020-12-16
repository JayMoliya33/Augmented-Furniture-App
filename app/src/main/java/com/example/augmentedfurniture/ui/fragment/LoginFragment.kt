package com.example.augmentedfurniture.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.example.augmentedfurniture.R
import android.widget.*
import com.example.augmentedfurniture.base.BaseFragment
import com.example.augmentedfurniture.model.User
import com.example.augmentedfurniture.ui.activity.HomeActivity
import com.example.augmentedfurniture.ui.activity.LoginActivity
import com.example.augmentedfurniture.utility.REGISTER_FRAGMENT
import com.example.augmentedfurniture.utility.ValidationUtils
import com.google.firebase.database.*

class LoginFragment : BaseFragment() {

    private lateinit var evPhone: EditText
    private lateinit var evPassword: EditText
    private lateinit var tvForgotPassword: TextView
    private lateinit var btnSignIn: ImageView
    private lateinit var tvCreateAccount: TextView
    private lateinit var loginProgressBar: ProgressBar
    private lateinit var databaseReference: DatabaseReference

    override fun setContentView(): Int {
        return R.layout.fragment_login
    }

    override fun initView(view: View?, savedInstanceState: Bundle?) {

        evPhone = view!!.findViewById(R.id.evPhone)
        evPassword = view.findViewById(R.id.evPassword)
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword)
        btnSignIn = view.findViewById(R.id.btnSignIn)
        tvCreateAccount = view.findViewById(R.id.tvCreateAccount)
        loginProgressBar = view.findViewById(R.id.loginProgressBar)
        databaseReference = FirebaseDatabase.getInstance().reference

        tvCreateAccount.setOnClickListener {
            (activity as LoginActivity).navigateToFragment(
                    RegisterFragment(),
                    true,
                    REGISTER_FRAGMENT
            )
        }

        btnSignIn.setOnClickListener {
            signInUser()
        }
    }

    private fun signInUser() {

        val phone = evPhone.text.toString()
        val password = evPassword.text.toString()

        if (isValidate()) {
            loginProgressBar.visibility = View.VISIBLE
            checkCredentials(phone, password)
        }
    }

    private fun isValidate(): Boolean {

        val phone = evPhone.text.toString()
        val password = evPassword.text.toString()

        if (!(ValidationUtils.isValidPhone(phone))) {
            evPhone.error = "Enter Valid Phone Number"
            evPhone.requestFocus()
            return false
        }
        if (!(ValidationUtils.isValidPassword(password))) {
            evPassword.error = "Enter Valid Password"
            evPassword.requestFocus()
            return false
        }
        return true
    }

    private fun checkCredentials(phone: String, password: String) {

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.child("Users").child(phone).exists()) {
                    val userData: User =
                            snapshot.child("Users").child(phone).getValue(User::class.java)!!
                    if (userData.phone == phone) {
                        if (userData.password == password) {
                            loginProgressBar.visibility = View.GONE

                            val sp = PreferenceManager.getDefaultSharedPreferences(activity)
                            val editor = sp.edit()
                            editor.putBoolean("booleanIsChecked", true)
                            editor.apply()

                            startActivity(Intent(activity, HomeActivity::class.java))

                        } else {
                            loginProgressBar.visibility = View.GONE
                            Toast.makeText(activity, "Incorrect Password", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                } else {
                    Toast.makeText(activity, "User with $phone does not exists", Toast.LENGTH_SHORT)
                            .show()
                    loginProgressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })


    }

    override fun setListeners() {

    }

}