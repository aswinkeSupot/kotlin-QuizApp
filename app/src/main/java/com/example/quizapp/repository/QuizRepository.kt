package com.example.quizapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.remitap.quizapp.model.QuestionsList
import com.remitap.quizapp.retrofit.QuestionsAPI
import com.remitap.quizapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {
    var questionsAPI: QuestionsAPI

    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance().create(QuestionsAPI::class.java)
    }

    fun getQuestionsFromAPI():LiveData<QuestionsList> {
        //Live Data
        var data = MutableLiveData<QuestionsList>()

        var questionsList : QuestionsList

        GlobalScope.launch(Dispatchers.IO) {

            //Returning the Response<QuestionsList>
            val response = questionsAPI.getQuestions()

            if(response != null){
                // Saving the data to list
                questionsList = response.body()!!

                data.postValue(questionsList)

                Log.i("TAGGY", ""+data.value)
            }
        }
        return data
    }
}