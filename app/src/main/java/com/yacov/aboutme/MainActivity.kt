package com.yacov.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.yacov.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Yacov")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        binding.button.setOnClickListener{ addNickname(it) }
        binding.nicknameText.setOnClickListener{ updateNickname(it) }

    }

    private fun addNickname(view: View) {
        binding.apply {
            myName?.nickname = binding.nicknameEdit.text.toString()
            invalidateAll()
            binding.nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun updateNickname (view: View) {
        binding.nicknameEdit.visibility = View.VISIBLE
        binding.button.visibility = View.VISIBLE
        view.visibility = View.INVISIBLE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.nicknameEdit.requestFocus()
        inputMethodManager.showSoftInput(nickname_edit, 0)
    }
}