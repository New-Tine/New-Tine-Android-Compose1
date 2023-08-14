package com.example.newtineproject.domain.model.mypage

data class UserProfile(
    var userName: String,
    var userCointPoints: Int,
    var daysOfUsing: Int,
    var interestKeyword: MutableList<() -> String>,
    var userImage: Int

)




