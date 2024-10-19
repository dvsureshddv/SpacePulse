package com.spacepulse.domain.use_cases

import com.spacepulse.core_module.utility.Result
import com.spacepulse.network_module.domain.models.Article
import com.spacepulse.network_module.domain.repositories.NewsRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    /*to fetch the articles from the api*/
    suspend fun getArticles(): Result<List<Article>> {
        return newsRepository.getArticles()
    }
}