package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener{
            clickHandlerFunction(it)
        }

        buttonReset.setOnClickListener{
            editTextHeight.text = null
            editTextWeight.text = null

            textViewBMI.text = "BMI: "
            imageViewProfile.setImageResource(R.drawable.empty)
        }
    }

    private fun clickHandlerFunction(viewThatIsClicked: View){
        try {
            var weight: Double =
                findViewById<EditText>(R.id.editTextWeight).text.toString().toDouble()
            var height: Double =
                findViewById<EditText>(R.id.editTextHeight).text.toString().toDouble()
            height = height / 100

            var bmiText = findViewById<TextView>(R.id.textViewBMI)

            var bmi: Double = weight / Math.pow(height, 2.0)

            var value: String = ""

            var image = findViewById<ImageView>(R.id.imageViewProfile)


            when {
                bmi < 18.5 -> {
                    value = "Underweight"
                    image.setImageResource(R.drawable.under)
                }
                bmi in 18.5..25.0 -> {
                    value = "Normal"
                    image.setImageResource(R.drawable.normal)
                }
                bmi > 25.0 -> {
                    value = "Overweight"
                    image.setImageResource(R.drawable.over)
                }
            }
            bmiText.text = "BMI: %.2f (%s)".format(bmi, value)
        }
        catch(ex:Exception){
            val myToast:Toast = Toast.makeText(this, "Weight or height cannot be empty", Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.CENTER,0,0)
            myToast.show()

        }
    }
}
