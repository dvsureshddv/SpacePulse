package com.spacepulse.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.utility.Result
import com.spacepulse.domain.use_cases.GetArticleUseCase
import com.spacepulse.network_module.domain.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticleUseCase,
    private val dispatcherRepository: DefaultDispatcherRepository
) : ViewModel() {

    //variable for the result of articles
    private val _articles = MutableStateFlow<Result<List<Article>>>(Result.Loading)
    //variable for observing the state
    val articles: StateFlow<Result<List<Article>>> = _articles

    //variable for selected article
    private val _selectedArticle = MutableStateFlow<Article?>(Article())
    //variable for observing the state
    val selectedArticle: StateFlow<Article?> = _selectedArticle

    //initial call to fetch the news articles
    init {
        fetchArticles()
    }

    /*get latest results from the api*/
    fun fetchArticles() {
        viewModelScope.launch(dispatcherRepository.io) {
            _articles.value = Result.Loading
            val result = getArticlesUseCase.getArticles()
            _articles.value = result
        }

    }

    //set the selected article
    fun setSelectedArticle(article: Article) {
        viewModelScope.launch {
            _selectedArticle.emit(article)
        }
    }


}