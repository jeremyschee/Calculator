package android.com.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //numbers
        tvOne.setOnClickListener { appendOnExpression("1",canClear = true) }
        tvTwo.setOnClickListener { appendOnExpression("2",canClear = true) }
        tvThree.setOnClickListener { appendOnExpression("3",canClear = true) }
        tvFour.setOnClickListener { appendOnExpression("4",canClear = true) }
        tvFive.setOnClickListener { appendOnExpression("5",canClear = true) }
        tvSix.setOnClickListener { appendOnExpression("6",canClear = true) }
        tvSeven.setOnClickListener { appendOnExpression("7",canClear = true) }
        tvEight.setOnClickListener { appendOnExpression("8",canClear = true) }
        tvNine.setOnClickListener { appendOnExpression("9",canClear = true) }
        tvZero.setOnClickListener { appendOnExpression("0",canClear = true) }
        tvDot.setOnClickListener { appendOnExpression(".",canClear = true) }

        //operators
        tvPlus.setOnClickListener { appendOnExpression("+",canClear = false) }
        tvMinus.setOnClickListener { appendOnExpression("-",canClear = false) }
        tvMul.setOnClickListener { appendOnExpression("*",canClear = false) }
        tvDivide.setOnClickListener { appendOnExpression("/",canClear = false) }
        tvOpen.setOnClickListener { appendOnExpression("(",canClear = false) }
        tvClose.setOnClickListener { appendOnExpression(")",canClear = false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e:Exception){
                Log.d("Exception","message:" + e.message)
            }
        }
    }

    fun appendOnExpression(string:String, canClear:Boolean){

        if (tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }
        if(canClear){
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}
