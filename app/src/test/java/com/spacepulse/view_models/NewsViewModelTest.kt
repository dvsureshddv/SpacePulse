package com.spacepulse.view_models

import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.utility.ErrorType
import com.spacepulse.core_module.utility.Result
import com.spacepulse.domain.use_cases.GetArticleUseCase
import com.spacepulse.network_module.domain.models.Article
import com.spacepulse.network_module.domain.repositories.NewsRepository
import com.spacepulse.presentation.view_models.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRepository: NewsRepository
    private lateinit var getArticleUseCase: GetArticleUseCase
    private lateinit var dispatcherRepo: DefaultDispatcherRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        //main dispatcher is set to run before tests
        Dispatchers.setMain(testDispatcher)
        //mock news repository
        newsRepository = Mockito.mock(NewsRepository::class.java)
        //mock dispatcher repo
        dispatcherRepo = Mockito.mock(DefaultDispatcherRepository::class.java)
        //get article use case instance
        getArticleUseCase = GetArticleUseCase(newsRepository = newsRepository)
        //non block dispatcher for test
        whenever(dispatcherRepo.io).thenReturn(testDispatcher)

        newsViewModel = NewsViewModel(
            getArticlesUseCase = getArticleUseCase,
            dispatcherRepository = dispatcherRepo
        )
    }

    //reset main after test  completion
    @After
    fun tearDown() {
        //reset main dispatcher to original dispatcher
        Dispatchers.resetMain()
    }

    //test case for success case
    @Test
    fun fetchArticlesSuccessTestCase() = runTest {
        val articleList: List<Article> = listOf(

            Article().apply {
                id = 1L
                title = "SpaceX"
                summary =
                    "The company won the first task orders issued under the National Security Space Launch"
                imageUrl =
                    "https://i0.wp.com/spacenews.com/wp-content/uploads/2023/05/SDA-0A_Sunrise_SW_Hill_03-31-2023_5597-2048x1365-1.jpg"
                newsSite = "SpaceNews"
            },

            Article().apply {
                id = 2L
                title = "NASA Michoud Gets a Rare Visitor"
                summary =
                    "TComet C/2023 A3 (Tsuchinshan-ATLAS) passes over NASA’s Michoud Assembly Facility in New Orleans in this Oct. 13, 2024, image."
                imageUrl =
                    "https://www.nasa.gov/wp-content/uploads/2024/10/epb-comet-tsuchinchanatlas-c2023a3orig.jpg?w=1367"
                newsSite = "SpaceNews"
            },

            Article().apply {
                id = 3L
                title = "NASA News"
                summary =
                    "ESP solves problems before you know you have them. If you are missing a canister of liquid nitrogen"
                imageUrl = "https://www.nasa.gov/wp-content/uploads/2024/10/image002.jpg?w=1778"
                newsSite = "NASA"
            }
        )

        //mock the success case
        whenever(getArticleUseCase.getArticles()).thenReturn(Result.Success(articleList))

        //fetch articles
        newsViewModel.fetchArticles()
        //wait for coroutine
        advanceUntilIdle()

        val responseState = newsViewModel.articles.value
        assert(responseState is Result.Success)
        assert((responseState as Result.Success).data == articleList)

    }

    //test case for network error
    @Test
    fun fetchArticlesErrorInCaseOfNetworkErrorTestCase() = runTest {
        // mock the error response from the use case
        whenever(getArticleUseCase.getArticles()).thenReturn(Result.Error(ErrorType.NETWORK_ERROR))

        // fetch articles
        newsViewModel.fetchArticles()
        // wait for coroutine
        advanceUntilIdle()

        val actualState = newsViewModel.articles.value
        assert(actualState is Result.Error)
        assert((actualState as Result.Error).message == ErrorType.NETWORK_ERROR)
    }

    //test case for data not available error
    @Test
    fun fetchArticlesNoDataErrorTestCase() = runTest {
        // mock the error response from the use case
        whenever(getArticleUseCase.getArticles()).thenReturn(Result.Error(ErrorType.DATA_NOT_AVAILABLE))

        // fetch articles
        newsViewModel.fetchArticles()
        // wait for coroutine
        advanceUntilIdle()

        val actualState = newsViewModel.articles.value
        assert(actualState is Result.Error)
        assert((actualState as Result.Error).message == ErrorType.DATA_NOT_AVAILABLE)
    }


    //test case for selected article
    @Test
    fun setSelectedArticleTestCase() = runTest {
        val article = Article().apply {
            id = 2L
            title = "NASA Michoud Gets a Rare Visitor"
            summary =
                "TComet C/2023 A3 (Tsuchinshan-ATLAS) passes over NASA’s Michoud Assembly Facility in New Orleans in this Oct. 13, 2024, image."
            imageUrl =
                "https://www.nasa.gov/wp-content/uploads/2024/10/epb-comet-tsuchinchanatlas-c2023a3orig.jpg?w=1367"
            newsSite = "SpaceNews"
        }

        newsViewModel.setSelectedArticle(article)
        //wait for coroutine
        advanceUntilIdle()

        val selectedArticleState = newsViewModel.selectedArticle.value
        assert(selectedArticleState == article)
    }
}
