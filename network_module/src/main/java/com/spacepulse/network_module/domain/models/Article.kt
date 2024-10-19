package com.spacepulse.network_module.domain.models

data class Article(

    var id: Long? = null,
    var title: String? = null,
    var summary: String? = null,
    var imageUrl: String? = null,
    var newsSite: String? = null,
)
