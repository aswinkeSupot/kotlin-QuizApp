package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.repository.QuizRepository
import com.remitap.quizapp.model.QuestionsList

class QuizViewModel: ViewModel() {

    var repository: QuizRepository = QuizRepository()

    lateinit var questionsLiveData: LiveData<QuestionsList>

    init {
        questionsLiveData = repository.getQuestionsFromAPI()
    }

    fun getQuestionsFromLiveData(): LiveData<QuestionsList>{
        return questionsLiveData
    }

}