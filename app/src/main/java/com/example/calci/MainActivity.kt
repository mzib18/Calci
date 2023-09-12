package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function
import net.objecthunter.exp4j.operator.Operator
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var one:TextView
    lateinit var two:TextView
    lateinit var three:TextView
    lateinit var four:TextView
    lateinit var five:TextView
    lateinit var six:TextView
    lateinit var seven:TextView
    lateinit var eight:TextView
    lateinit var nine:TextView
    lateinit var zero:TextView
    lateinit var decimal:TextView
    lateinit var add:TextView
    lateinit var substract:TextView
    lateinit var multiply:TextView
    lateinit var division:TextView
    lateinit var equals:TextView
    lateinit var changeSign:TextView
    lateinit var allClear:TextView
    lateinit var backSpace:ImageView
    lateinit var inv:TextView
    lateinit var button:TextView
    lateinit var sin:TextView
    lateinit var cos:TextView
    lateinit var tan:TextView
    lateinit var modulo:TextView
    lateinit var ln:TextView
    lateinit var log:TextView
    lateinit var squareRoot:TextView
    lateinit var exponent:TextView
    lateinit var pie:TextView
    lateinit var etoThePower:TextView
    lateinit var openParanthesis:TextView
    lateinit var closingParanthesis:TextView
    lateinit var factorial:TextView
    lateinit var result:TextView
    lateinit var expression:TextView
    lateinit var degree:TextView
     var isInverse:Boolean = false
    var isDEG:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        one =findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        add = findViewById(R.id.add)
        substract = findViewById(R.id.substract)
        multiply = findViewById(R.id.multiply)
        division = findViewById(R.id.division)
        decimal = findViewById(R.id.decimal)
        backSpace = findViewById(R.id.backSpace)
        allClear= findViewById(R.id.clearAll)
        changeSign = findViewById(R.id.changeSign)
        equals = findViewById(R.id.equalTo)
        inv = findViewById(R.id.INV)
        button = findViewById(R.id.button)
        sin = findViewById(R.id.sin)
        cos = findViewById(R.id.cos)
        tan = findViewById(R.id.tan)
        modulo = findViewById(R.id.modulo)
        ln = findViewById(R.id.ln)
        log = findViewById(R.id.log)
        squareRoot = findViewById(R.id.squareRoot)
        exponent = findViewById(R.id.raisedToX)
        pie = findViewById(R.id.pie)
        etoThePower = findViewById(R.id.e)
        openParanthesis = findViewById(R.id.openingParanthesis)
        closingParanthesis = findViewById(R.id.closingParantehsis)
        factorial = findViewById(R.id.factorial)
        result = findViewById(R.id.result)
        expression = findViewById(R.id.expression)
        degree= findViewById(R.id.DEG)
        one.setOnClickListener {
            appendText("1",true)
        }
        degree.setOnClickListener {
            if(isDEG){
                degree.text="DEG"
                degree.textAlignment=View.TEXT_ALIGNMENT_CENTER
                button.text="RAD"
                isDEG=false
            }else{
                degree.text="RAD"
                degree.textAlignment=View.TEXT_ALIGNMENT_CENTER
                button.text="DEG"
                isDEG=true
            }
        }
        inv.setOnClickListener {
            if(isInverse){
                isInverse=false
                sin.text="sin"
                sin.textAlignment=View.TEXT_ALIGNMENT_GRAVITY
                cos.text="cos"
                tan.text="tan"
            }else{
                isInverse=true
                sin.text="sin-1"
                sin.textAlignment=View.TEXT_ALIGNMENT_GRAVITY
                cos.text="cos-1"
                tan.text="tan-1"
            }
        }
        two.setOnClickListener {
            appendText("2",true)
        }
        three.setOnClickListener {
            appendText("3",true)
        }
        four.setOnClickListener {
            appendText("4",true)
        }
        five.setOnClickListener {
            appendText("5",true)
        }
        six.setOnClickListener {
            appendText("6",true)
        }
        seven.setOnClickListener {
            appendText("7",true)
        }
        eight.setOnClickListener {
            appendText("8",true)
        }
        nine.setOnClickListener {
            appendText("9",true)
        }
        zero.setOnClickListener {
            appendText("0",true)
        }
        decimal.setOnClickListener {
            appendText(".",true)
        }
        add.setOnClickListener {
            appendText("+",false)
        }
        substract.setOnClickListener {
            appendText("-",false)
        }
        multiply.setOnClickListener {
            appendText("*",false)
        }
        division.setOnClickListener {
            appendText("/",false)
        }
        modulo.setOnClickListener {
            appendText("%",false)
        }
        allClear.setOnClickListener {
            result.text=""
            result.hint=""
            expression.text=""
        }
        backSpace.setOnClickListener {
            result.text=""
            result.hint=""
            val value=expression.text
            if(value.isNotEmpty()){
                expression.text=value.substring(0,value.length-1)
            }
        }
        ln.setOnClickListener {
            appendText("log",false)
        }
        log.setOnClickListener {
            appendText("log10",false)
        }
        squareRoot.setOnClickListener {
            appendText("sqrt",false)
        }
        etoThePower.setOnClickListener {
            appendText("e",false)
        }
        pie.setOnClickListener {
            appendText("π",false)
        }
        exponent.setOnClickListener {
            appendText("^",false)
        }
        changeSign.setOnClickListener {
            result.text=""
            result.hint=""
            if(expression.text[0]=='-'){
                expression.text="+"+expression.text.substring(1)
            }else{
                expression.text="-"+expression.text
            }
        }
        sin.setOnClickListener {
            if(isInverse){
                    appendText("asin(", false)
            }else {
                    appendText("sin(", false)
            }
        }
        factorial.setOnClickListener {

            appendText("!",false)
        }
        var factorial: Operator = object : Operator("!", 1, true, PRECEDENCE_POWER + 1) {
            override fun apply(vararg args: Double): Double {
                val arg = args[0].toInt()
                require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
                require(arg >= 0) { "The operand of the factorial can not be less than zero" }
                var result = 1.0
                for (i in 1..arg) {
                    result *= i.toDouble()
                }
                return result
            }
        }

            cos.setOnClickListener {
            if(isInverse){
                        appendText("acos(", false)

            }else {
                    appendText("cos(", false)

            }
        }
        tan.setOnClickListener {
            if(isInverse){

                    appendText("atan(", false)
            }else{
                    appendText("tan(", false)
            }
        }
        var asin: Function = object : Function("asin") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return Math.toDegrees(Math.asin(args[0]))
                }else{
                    return Math.asin(args[0])
                }
            }
        }

        var acos: Function = object : Function("acos") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return Math.toDegrees(Math.acos(args[0]))
                }else{
                    return Math.asin(args[0])
                }
            }
        }

        var atan: Function = object : Function("atan") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return Math.toDegrees(Math.atan(args[0]))
                }else{
                    return Math.atan(args[0])
                }
            }
        }

        var sin: Function = object : Function("sin") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return (Math.sin(args[0] * Math.PI / 180))
                }else{
                    return (Math.sin(args[0]))
                }
            }
        }
        var cos: Function = object : Function("cos") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return (Math.cos(args[0] * Math.PI / 180))
                }else{
                    return (Math.cos(args[0]))
                }
            }
        }
        var tan: Function = object : Function("tan") {
            override fun apply(vararg args: Double): Double {
                if(isDEG) {
                    return (Math.tan(args[0] * Math.PI / 180))
                }else{
                    return (Math.tan(args[0]))
                }
            }
        }
        openParanthesis.setOnClickListener {
            appendText("(",false)
        }
        closingParanthesis.setOnClickListener {
            appendText(")",false)
        }
        equals.setOnClickListener {
            try {
                val ans = ExpressionBuilder(expression.text.toString()).operator(factorial).function(sin).function(cos).function(tan).function(asin).function(acos).function(atan).build().evaluate().toFloat()
                val toast=Toast.makeText(this,ans.toString(),Toast.LENGTH_LONG).show()
                result.text=ans.toString()
                //appendText("",false)
            }catch (e:Exception){
                val toast=Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
                result.text=e.message
            }
        }
    }

    private fun appendText(value:String,toBeCleared:Boolean){
        if(result.text!=""){
            expression.text=""
        }
        if(toBeCleared){
            result.text=""
            expression.append(value)
        }else{
            expression.append(result.text)
            expression.append(value)
            result.text=""
        }
        result.hint=expression.text
    }

}