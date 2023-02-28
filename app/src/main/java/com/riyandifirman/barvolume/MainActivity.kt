package com.riyandifirman.barvolume

//import library yang dibutuhkan
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//membuat class MainActivity yang merupakan turunan dari AppCompatActivity
//View.OnClickListener digunakan untuk mendengarkan event klik pada button
class MainActivity : AppCompatActivity(), View.OnClickListener {
    //deklarasi variabel
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    //membuat fungsi onCreate yang akan dipanggil ketika activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //menghubungkan layout activity_main.xml dengan class MainActivity.kt
        setContentView(R.layout.activity_main)

        //menghubungkan variabel dengan id pada layout
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        //mendengarkan event klik pada button
        btnCalculate.setOnClickListener(this)

        //mengambil nilai dari savedInstanceState
        if (savedInstanceState != null) {
            //mengambil nilai dari key STATE_RESULT
            val result = savedInstanceState.getString(STATE_RESULT)
            //menampilkan nilai dari result ke TextView
            tvResult.text = result
        }
    }

    //membuat companion object untuk menyimpan nilai dari key STATE_RESULT
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    //membuat fungsi onClick yang akan dipanggil ketika button diklik
    override fun onClick(v: View?) {
        //membuat kondisi ketika button diklik
        if (v?.id == R.id.btn_calculate) {
            //mengambil nilai dari EditText
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            //membuat kondisi untuk mengecek apakah field kosong atau tidak
            var isEmptyFields = false

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }

            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

    //membuat fungsi onSaveInstanceState yang akan dipanggil ketika activity dihentikan
    override fun onSaveInstanceState(outState: Bundle) {
        //memanggil fungsi super.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
        //menyimpan nilai dari TextView ke key STATE_RESULT
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}