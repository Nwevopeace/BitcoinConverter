package com.peacecodes.bitcoinconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peacecodes.bitcoinconverter.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            convert()
        }
    }

    private fun convert() {
        val inputText = binding.input.text.toString().toDoubleOrNull()
        if (inputText == null || inputText == 0.0){
            display(0.0)
            return
        }

        val btc = 0.0000000468451008
        var result = inputText * btc
        result = if (binding.switched.isChecked) {
            round(result)
        } else {
            result.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }
       display(result)
    }

    private fun display(result: Double) {
        val value = "$result"
        binding.Result.text = getString(R.string.btc_equivalence,  value)
    }
}