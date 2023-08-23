package com.example.newtineproject.data.remote.article_detail_service

import com.example.newtineproject.common.Url
import com.example.newtineproject.data.remote.dto.article_detail.ArticleDetail
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleDetailServiceImpl(
    private val client: HttpClient
) : ArticleDetailService {

    override suspend fun getEachArticleDetail(): ArticleDetail {
        return client.get(Url.EACH_ARTICLE_NUM).body()
    }

}
