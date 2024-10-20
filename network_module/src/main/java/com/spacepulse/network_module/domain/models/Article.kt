package com.spacepulse.network_module.domain.models

data class Article(

    var id: Long = 1L,
    var title: String = "",
    var summary: String = "",
    var imageUrl: String = "",
    var newsSite: String = "",
)
