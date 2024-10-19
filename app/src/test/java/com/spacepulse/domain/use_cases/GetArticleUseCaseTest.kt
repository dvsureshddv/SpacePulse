package com.spacepulse.domain.use_cases

import com.spacepulse.core_module.utility.ErrorType
import com.spacepulse.core_module.utility.Result
import com.spacepulse.network_module.domain.models.Article
import com.spacepulse.network_module.domain.repositories.NewsRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetArticleUseCaseTest {

    private lateinit var getArticleUseCase: GetArticleUseCase
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setUp() {
        //mock news repository
        newsRepository = mock(NewsRepository::class.java)
        //get article use case instance
        getArticleUseCase = GetArticleUseCase(newsRepository)
    }

    //articles successfully returned case
    @Test
    fun getArticleSuccessTestCase() = runTest {

        //mock the articles
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

        whenever(newsRepository.getArticles()).thenReturn(Result.Success(articleList))
        //use case with getArticles
        val result = getArticleUseCase.getArticles()
        assertEquals(Result.Success(articleList), result)
    }

    //network error result test case
    @Test
    fun networkErrorResultTestCase() = runTest {
        //mock the error result
        val mockError = Result.Error(ErrorType.NETWORK_ERROR)
        whenever(newsRepository.getArticles()).thenReturn(mockError)
        val result = getArticleUseCase.getArticles()
        assertEquals(mockError, result)
    }

    //data not available error result test case
    @Test
    fun dataNotAvailableErrorResultTestCase() = runTest {
        //mock the error result
        val mockError = Result.Error(ErrorType.DATA_NOT_AVAILABLE)
        whenever(newsRepository.getArticles()).thenReturn(mockError)
        val result = getArticleUseCase.getArticles()
        assertEquals(mockError, result)
    }

    //server error result test case
    @Test
    fun serverErrorResultTestCase() = runTest {
        //mock the error result
        val mockError = Result.Error(ErrorType.SERVER_ERROR)
        whenever(newsRepository.getArticles()).thenReturn(mockError)
        val result = getArticleUseCase.getArticles()
        assertEquals(mockError, result)
    }

    //loading state test case
    @Test
    fun loadingStateTestCase() = runTest {
        val mockLoading = Result.Loading
        whenever(newsRepository.getArticles()).thenReturn(mockLoading)
        val result = getArticleUseCase.getArticles()
        assertEquals(Result.Loading, result)
    }
}
