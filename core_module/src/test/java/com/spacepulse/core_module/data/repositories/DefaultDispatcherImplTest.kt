package com.spacepulse.core_module.data.repositories


import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class DefaultDispatcherImplTest {

    private lateinit var defaultDispatcher: DefaultDispatcherImpl


    @Before
    fun setup() {
        defaultDispatcher = DefaultDispatcherImpl()
    }


    //io dispatcher test case
    @Test
    fun ioDispatcherTestCase() = runTest {
        val ioDispatcher = defaultDispatcher.io
        assertEquals(Dispatchers.IO, ioDispatcher)
    }

    //main dispatcher test case
    @Test
    fun mainDispatcherTestCase() = runTest {
        val mainDispatcher = defaultDispatcher.main
        assertEquals(Dispatchers.Main, mainDispatcher)
    }

    //default dispatcher test case
    @Test
    fun defaultDispatcherTestCase() = runTest {
        val defaultDispatcherValue = defaultDispatcher.default
        assertEquals(Dispatchers.Default, defaultDispatcherValue)
    }

    //unconfined dispatcher test case
    @Test
    fun unconfinedDispatcherTestCase() = runTest {
        val unconfinedDispatcher = defaultDispatcher.unconfined
        assertEquals(Dispatchers.Unconfined, unconfinedDispatcher)
    }
}
