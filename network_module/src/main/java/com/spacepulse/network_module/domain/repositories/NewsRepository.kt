package com.spacepulse.network_module.domain.repositories

import com.spacepulse.core_module.utility.Result
import com.spacepulse.network_module.domain.models.Article

interface NewsRepository {

    //to get the articles list
    suspend fun getArticles(): Result<List<Article>>
}