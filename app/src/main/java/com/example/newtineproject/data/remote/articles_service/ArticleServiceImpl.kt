package com.example.newtineproject.data.remote.articles_service

import com.example.newtineproject.common.Url
import com.example.newtineproject.data.remote.dto.articles.Articles
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleServiceImpl(
    private val client: HttpClient
) : ArticleService {
    override suspend fun getArticlesIt(): Articles {
        return client.get(Url.ARTICLES_IT).body()
    }

    override suspend fun getArticlesPolitics(): Articles {
        return client.get(Url.ARTICLES_POLITICS).body()
    }

    override suspend fun getArticlesEconomy(): Articles {
        return client.get(Url.ARTICLES_ECONOMY).body()
    }

    override suspend fun getArticlesIndustry(): Articles {
        return client.get(Url.ARTICLES_INDUSTRY).body()
    }

    override suspend fun getArticlesSociety(): Articles {
        return client.get(Url.ARTICLES_SOCIETY).body()
    }

    override suspend fun getArticlesCulture(): Articles {
        return client.get(Url.ARTICLES_CULTURE).body()
    }

    override suspend fun getArticlesSports(): Articles {
        return client.get(Url.ARTICLES_SPORTS).body()
    }
}