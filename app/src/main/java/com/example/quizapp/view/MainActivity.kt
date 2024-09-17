package com.example.quizapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.viewmodel.QuizViewModel
import com.remitap.quizapp.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionsList: List<Question>

    companion object {
        var result = 0
        var totalQuesions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Resetting the scores
        result = 0
        totalQuesions = 0

        // Getting the response
        quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        // Displaying the First Question
        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if(it.size >0){
                    questionsList = it
                    Log.i("TAGGY", "This the 1st Question: ${questionsList[0]}")

                    binding.apply {
                        txtQuestion.text = questionsList!![0].question
                        radio1.text = questionsList!![0].option1
                        radio2.text = questionsList!![0].option2
                        radio3.text = questionsList!![0].option3
                        radio4.text = questionsList!![0].option4
                    }
                }

            })
        }

        // Adding Functionality to Next Button
        var i = 1
        binding.apply {
            btnNext.setOnClickListener {
                val selectedOption = radioGroup?.checkedRadioButtonId

                if(selectedOption != -1) {
                    val radbutton = findViewById<View>(selectedOption!!) as RadioButton

                    questionsList.let {
                        if (i<it.size!!){
                            // Getting number of questions
                            totalQuesions = it.size

                            // Check if it is correct or not
                            if(radbutton.text.toString().equals(it[i-1].correct_option)){
                                result++
                                txtResult?.text = "Correct Answer : $result"
                            }

                            // Displaying the next questions
                            txtQuestion.text = "Question ${i+1}: " + questionsList[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            // Checking if it is the last question
                            if(i == it.size!!.minus(1)){
                                btnNext.text = "FINISH"
                            }

                            radioGroup?.clearCheck()
                            i++


                        } else {
                            if (radbutton.text.toString().equals(it[i-1].correct_option)){
                               result++
                               txtResult?.text = "Correct Answer : $result"
                            } else {
                            }

                            val intent = Intent(this@MainActivity, ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity,"Please Select One Option", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}