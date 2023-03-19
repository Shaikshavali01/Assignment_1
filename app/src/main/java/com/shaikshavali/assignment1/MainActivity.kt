package com.shaikshavali.assignment1

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var etname : EditText? = null
    private var etclgname : EditText? = null

    private var tvradio1 : TextView? = null
    private var tvradio2 : TextView? = null


    private var genderselected : Int = 0
    private var isyearselected :Boolean = true


    private var tvselectdobdate : TextView? = null
    private var btndob : Button? = null

    private var tvselectdatetime : TextView? = null
    private var btndatetime : Button? = null

    val years = arrayOf("SELECT","2023","2022","2021")

    private var btnsubmit : Button?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_main)

        etname = findViewById(R.id.etname)
        etclgname = findViewById(R.id.etclgname)


        tvradio1 = findViewById(R.id.radiomale1)
        tvradio2 = findViewById(R.id.radiofemale1)


        selectinggender()


        tvselectdobdate = findViewById(R.id.tvselectdobdate1)
        btndob = findViewById(R.id.imgdob1)

        btndob?.setOnClickListener {
            selectingdob1(it)
        }

        tvselectdatetime = findViewById(R.id.tvselectdatetime1)
        btndatetime = findViewById(R.id.imgdatetime1)

        btndatetime?.setOnClickListener {
            selectingdobandtime1(it)
        }

        val spinner1 = findViewById<Spinner>(R.id.spinner1)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,years)
        spinner1.adapter = arrayAdapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

//               Toast.makeText(applicationContext,"passed out year is "+years[position],Toast.LENGTH_SHORT).show()
                if(years[position] == "SELECT"){
                   isyearselected = false
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        btnsubmit = findViewById(R.id.submitbtn)
        btnsubmit?.setOnClickListener {

            when
            {
                etname?.text?.isEmpty() == true ->
                    Toast.makeText(this,"Name is not Filled", Toast.LENGTH_SHORT).show()

                etclgname?.text?.isEmpty()==true->
                    Toast.makeText(this,"College name is not Filled", Toast.LENGTH_SHORT).show()

                genderselected == 0 ->
                    Toast.makeText(this,"Gender is not selected", Toast.LENGTH_SHORT).show()

                isyearselected ->
                    Toast.makeText(this,"Passout year is not selected", Toast.LENGTH_SHORT).show()

                tvselectdobdate?.text?.equals("Select DOB date")==true ->
                    Toast.makeText(this,"DOB is Not Selected", Toast.LENGTH_SHORT).show()

                tvselectdatetime?.text?.equals("Select Date and Time")==true ->
                    Toast.makeText(this,"Date and Time is Not Selected", Toast.LENGTH_SHORT).show()

                true->{
                    Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    private fun selectinggender() {
        tvradio1?.setOnClickListener {

            tvradio1?.background = ContextCompat.getDrawable(this, R.drawable.radiobuttontoggled)
            tvradio2?.background = ContextCompat.getDrawable(this, R.drawable.radiobutton)
            genderselected = 1

        }

        tvradio2?.setOnClickListener{

            tvradio2?.background =ContextCompat.getDrawable(this,R.drawable.radiobuttontoggled)
            tvradio1?.background = ContextCompat.getDrawable(this,R.drawable.radiobutton)
            genderselected = 2
        }
    }

    fun selectingdob1(view: View){

        val cal= Calendar.getInstance()
        val year=cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dpg= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, sday ->

            val selectedDate="$sday-${month+1}-$year"


            tvselectdobdate?.text = selectedDate

        },
            year,
            month,
            day)
        dpg.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpg.show()
    }

    fun selectingdobandtime1(view: View){

        val cal= Calendar.getInstance()
        val year=cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val dpg= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, sday ->

            val selectedDate="$sday-${month+1}-$year"

            val hour = cal.get(Calendar.HOUR_OF_DAY)
            val min = cal.get(Calendar.MINUTE)

            TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener{ view, hour, min->
                    val selectedtime = "$hour:$min"



                    tvselectdatetime?.text = "$selectedDate | $selectedtime"

                },
                hour,
                min,
                true
            ).show()


        },
            year,
            month,
            day)
        dpg.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpg.show()
    }


}

