package com.remitap.quizapp.retrofit

import com.remitap.quizapp.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aswin on 12-09-2024.
 */
interface QuestionsAPI {

    @GET("questionsapi.php")
    suspend fun getQuestions() : Response<QuestionsList>
}