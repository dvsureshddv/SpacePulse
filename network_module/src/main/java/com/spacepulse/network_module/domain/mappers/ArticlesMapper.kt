package com.spacepulse.network_module.domain.mappers

import com.spacepulse.network_module.data.dto.ApiResponseDto
import com.spacepulse.network_module.data.dto.ArticleDto
import com.spacepulse.network_module.domain.models.Article

object ArticlesMapper {

    //convert list of article dto to article
    fun ApiResponseDto<List<ArticleDto>?>.toArticlesList(): List<Article> {
        return results.orEmpty().map { it.toArticle() }
    }

    //convert article dto to article
    private fun ArticleDto.toArticle(): Article {
        return Article(
            id = id ?: 1L,
            title = title ?: "-",
            summary = summary ?: "-",
            imageUrl = imageUrl ?: "",
            newsSite = newsSite ?: ""
        )
    }
}