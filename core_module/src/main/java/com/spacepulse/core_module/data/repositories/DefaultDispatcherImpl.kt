package com.spacepulse.core_module.data.repositories

import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

//dispatchers
class DefaultDispatcherImpl : DefaultDispatcherRepository {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}