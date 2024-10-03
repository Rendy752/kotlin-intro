package com.example.introduction

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editWidth: EditText
    private lateinit var editLength: EditText
    private lateinit var editHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var tvMoveResult: TextView
    private val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvMoveResult.text = "Hasil activity = $selectedValue"
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editWidth = findViewById(R.id.edit_width)
        editLength = findViewById(R.id.edit_length)
        editHeight = findViewById(R.id.edit_height)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }

        val btnLinearLayoutActivity: Button = findViewById(R.id.btn_linear_layout_activity)
        btnLinearLayoutActivity.setOnClickListener(this)

        val btnConstraintLayoutActivity: Button = findViewById(R.id.btn_constraint_layout_activity)
        btnConstraintLayoutActivity.setOnClickListener(this)

        val btnRecycleViewActivity: Button = findViewById(R.id.btn_recycle_view_activity)
        btnRecycleViewActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnOpenGenshinImpact: Button = findViewById(R.id.btn_open_genshin)
        btnOpenGenshinImpact.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvMoveResult = findViewById(R.id.tv_move_result)

        val btnLoggingAndDebug: Button = findViewById(R.id.btn_logging_and_debug)
        btnLoggingAndDebug.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_linear_layout_activity -> {
                val moveIntent = Intent(this@MainActivity, LinearLayoutActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_constraint_layout_activity -> {
                val moveIntent = Intent(this@MainActivity, ConstraintLayoutActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_recycle_view_activity -> {
                val moveIntent = Intent(this@MainActivity, RecycleViewActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Rendy Pratama")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 21)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Rendy Pratama",
                    21,
                    "rendypratama752@gmaol.com",
                    "Palembang"
                )

                val moveWithObjectIntent =
                    Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "082279764871"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_open_genshin -> {
                var intent = packageManager.getLaunchIntentForPackage("com.miHoYo.GenshinImpact")

                if (intent == null) {
                    intent = try {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=com.miHoYo.GenshinImpact")
                        )
                    } catch (e: Exception) {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.miHoYo.GenshinImpact")
                        )
                    }
                    startActivity(intent)
                } else {
                    startActivity(intent)
                }
            }

            R.id.btn_move_for_result -> {
                val moveForResultIntent =
                    Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveForResultIntent)
            }

            R.id.btn_logging_and_debug -> {
                val loggingAndDebugIntent =
                    Intent(this@MainActivity, LoggingAndDebugActivity::class.java)
                startActivity(loggingAndDebugIntent)
            }

            R.id.btn_calculate -> {
                val inputLength = editLength.text.toString().trim()
                val inputWidth = editWidth.text.toString().trim()
                val inputHeight = editHeight.text.toString().trim()
                var isEmptyField = false
                if (inputLength.isEmpty()) {
                    isEmptyField = true
                    editLength.error = "Panjang tidak boleh kosong"
                }
                if (inputWidth.isEmpty()) {
                    isEmptyField = true
                    editWidth.error = "Lebar tidak boleh kosong"
                }
                if (inputHeight.isEmpty()) {
                    isEmptyField = true
                    editHeight.error = "Tinggi tidak boleh kosong"
                }
                if (!isEmptyField) {
                    val volume =
                        inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                    tvResult.text = volume.toString()
                }
            }
        }
    }
}