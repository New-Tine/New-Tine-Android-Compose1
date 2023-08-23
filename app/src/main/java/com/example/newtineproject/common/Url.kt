package com.example.newtineproject.common

object Url {

    private const val BASE_URL = "http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080/"

    const val HOME_NEWS = "$BASE_URL/news/"

    const val NEWS_RANKING = "http://ec2-52-78-221-52.ap-northeast-2.compute.amazonaws.com:8080/news/ranking"


    private const val ARTICLES = "http://ec2-52-78-221-52.ap-northeast-2.compute.amazonaws.com:8080/news/category"

    const val ARTICLES_IT = "$ARTICLES/IT"
    const val ARTICLES_POLITICS = "$ARTICLES/정치"
    const val ARTICLES_ECONOMY = "$ARTICLES/경제"
    const val ARTICLES_INDUSTRY = "$ARTICLES/산업"
    const val ARTICLES_SOCIETY = "$ARTICLES/사회"
    const val ARTICLES_CULTURE = "$ARTICLES/문화"
    const val ARTICLES_SPORTS = "$ARTICLES/스포츠"

    private const val EACH_ARTICLE = "http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080/news"
    const val EACH_ARTICLE_NUM = "$EACH_ARTICLE/{}"
    //const val EACH_ARTICLE_NUM = "$EACH_ARTICLE/${newsId}"
}