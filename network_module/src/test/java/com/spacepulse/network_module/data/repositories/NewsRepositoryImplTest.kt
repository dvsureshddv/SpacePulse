package com.spacepulse.network_module.data.repositories


import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.domain.repositories.NetworkStateRepo
import com.spacepulse.core_module.utility.ErrorType
import com.spacepulse.core_module.utility.Result
import com.spacepulse.network_module.data.api.ConnectionApi
import com.spacepulse.network_module.data.dto.ApiResponseDto
import com.spacepulse.network_module.data.dto.ArticleDto
import com.spacepulse.network_module.domain.mappers.ArticlesMapper.toArticlesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class NewsRepositoryImplTest {

    private lateinit var newsRepository: NewsRepositoryImpl //repository
    private lateinit var connectionApi: ConnectionApi //connection api
    private lateinit var dispatcherRepo: DefaultDispatcherRepository // dispatchers
    private lateinit var networkStateRepo: NetworkStateRepo //network state

    @Before
    fun setUp() {
        connectionApi = mock(ConnectionApi::class.java)
        dispatcherRepo = mock(DefaultDispatcherRepository::class.java)
        networkStateRepo = mock(NetworkStateRepo::class.java)
        // mock dispatcherRepo for io dispatcher
        whenever(dispatcherRepo.io).thenReturn(Dispatchers.IO)

        newsRepository = NewsRepositoryImpl(
            connectionApi = connectionApi,
            dispatcherRepo = dispatcherRepo,
            networkStateRepo = networkStateRepo
        )
    }

    @Test
    fun fetchArticlesApiSuccessTestCase() = runBlocking {

        val articleDtoList: List<ArticleDto> = listOf(

            ArticleDto().apply {
                id = 1L
                title = "SpaceX"
                summary =
                    "The company won the first task orders issued under the National Security Space Launch"
                imageUrl =
                    "https://i0.wp.com/spacenews.com/wp-content/uploads/2023/05/SDA-0A_Sunrise_SW_Hill_03-31-2023_5597-2048x1365-1.jpg"
                newsSite = "SpaceNews"
            },

            ArticleDto().apply {
                id = 2L
                title = "NASA Michoud Gets a Rare Visitor"
                summary =
                    "TComet C/2023 A3 (Tsuchinshan-ATLAS) passes over NASA’s Michoud Assembly Facility in New Orleans in this Oct. 13, 2024, image."
                imageUrl =
                    "https://www.nasa.gov/wp-content/uploads/2024/10/epb-comet-tsuchinchanatlas-c2023a3orig.jpg?w=1367"
                newsSite = "SpaceNews"
            },

            ArticleDto().apply {
                id = 3L
                title = "NASA News"
                summary =
                    "ESP solves problems before you know you have them. If you are missing a canister of liquid nitrogen"
                imageUrl = "https://www.nasa.gov/wp-content/uploads/2024/10/image002.jpg?w=1778"
                newsSite = "NASA"
            }
        )

        // mock the network state to be connected
        whenever(networkStateRepo.isInternetConnected()).thenReturn(true)
        val apiResponse: ApiResponseDto<List<ArticleDto>?> = ApiResponseDto(articleDtoList)
        // mock the api response
        whenever(connectionApi.getArticles()).thenReturn(apiResponse)

        val result = newsRepository.getArticles()
        val expectedArticles = apiResponse.toArticlesList()
        assert(result is Result.Success)
        assertEquals(expectedArticles, (result as Result.Success).data)
    }

    @Test
    fun noInternetTestCase() = runBlocking {
        // mock the network state disconnected
        whenever(networkStateRepo.isInternetConnected()).thenReturn(false)
        val result = newsRepository.getArticles()
        assert(result is Result.Error)
        assertEquals(ErrorType.NETWORK_ERROR, (result as Result.Error).message)
    }

    @Test
    fun noDataAvailableTestCase() = runBlocking {
        // mock the network state to be connected
        whenever(networkStateRepo.isInternetConnected()).thenReturn(true)
        //mock api response to return empty data
        whenever(connectionApi.getArticles()).thenReturn(ApiResponseDto(emptyList()))
        val result = newsRepository.getArticles()
        assert(result is Result.Error)
        assertEquals(ErrorType.DATA_NOT_AVAILABLE, (result as Result.Error).message)
    }

    @Test
    fun serverErrorWhenExceptionOccurredTestCase() = runBlocking {
        // mock the network state to be connected
        whenever(networkStateRepo.isInternetConnected()).thenReturn(true)
        // mock api to throw an exception
        whenever(connectionApi.getArticles()).thenThrow(RuntimeException("Server error"))
        val result = newsRepository.getArticles()
        assert(result is Result.Error)
        assertEquals(ErrorType.SERVER_ERROR, (result as Result.Error).message)
    }
}
