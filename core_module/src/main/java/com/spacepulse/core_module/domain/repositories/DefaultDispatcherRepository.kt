package com.spacepulse.core_module.domain.repositories

import kotlinx.coroutines.CoroutineDispatcher

interface DefaultDispatcherRepository {

    val io : CoroutineDispatcher //io dispatcher
    val main : CoroutineDispatcher // main dispatcher
    val default : CoroutineDispatcher // default dispatcher
    val unconfined : CoroutineDispatcher //unconfined dispatcher
}