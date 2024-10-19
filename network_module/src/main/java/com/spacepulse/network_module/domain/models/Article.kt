package com.spacepulse.network_module.domain.models

import java.io.Serializable

data class Article(

    var id: Long? = null,
    var title: String? = null,
    var summary: String? = null,
    var imageUrl: String? = null,
    var newsSite: String? = null,
) : Serializable
