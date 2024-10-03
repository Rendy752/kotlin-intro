package com.example.introduction

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.introduction.databinding.ActivityCountCubeBinding
import com.example.introduction.databinding.ActivityMainBinding

class CountCubeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityCountCubeBinding

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountCubeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener(this)
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            binding.tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, binding.tvResult.text.toString())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_calculate -> {
                val inputLength = binding.editLength.text.toString().trim()
                val inputWidth = binding.editWidth.text.toString().trim()
                val inputHeight = binding.editHeight.text.toString().trim()
                var isEmptyField = false
                if (inputLength.isEmpty()) {
                    isEmptyField = true
                    binding.editLength.error = "Panjang tidak boleh kosong"
                }
                if (inputWidth.isEmpty()) {
                    isEmptyField = true
                    binding.editWidth.error = "Lebar tidak boleh kosong"
                }
                if (inputHeight.isEmpty()) {
                    isEmptyField = true
                    binding.editHeight.error = "Tinggi tidak boleh kosong"
                }
                if (!isEmptyField) {
                    val volume =
                        inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                    binding.tvResult.text = volume.toString()
                }
            }
        }
    }
}