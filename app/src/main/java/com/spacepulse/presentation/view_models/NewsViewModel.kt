package com.spacepulse.presentation.view_models

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.utility.Result
import com.spacepulse.domain.use_cases.GetArticleUseCase
import com.spacepulse.network_module.domain.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticleUseCase,
    private val dispatcherRepository: DefaultDispatcherRepository
) : ViewModel() {

    //variable for the result of articles
    private val _articles = MutableSharedFlow<Result<List<Article>>>(replay = 1)
    //variable for observing the state
    val articles: SharedFlow<Result<List<Article>>> = _articles

    //variable for selected article
    private val _selectedArticle = MutableSharedFlow<Article?>(replay = 1)
    //variable for observing the state
    val selectedArticle: SharedFlow<Article?> = _selectedArticle

    //initial call to fetch the news articles
    init {
        fetchArticles()
    }

    /*get latest results from the api*/
    fun fetchArticles() {
        viewModelScope.launch(dispatcherRepository.io) {
            _articles.emit(Result.Loading)
            val result = getArticlesUseCase.getArticles()
            _articles.emit(result)
        }

    }

    //set the selected article
    fun setSelectedArticle(article: Article) {
        viewModelScope.launch {
            _selectedArticle.emit(article)
        }
    }


}