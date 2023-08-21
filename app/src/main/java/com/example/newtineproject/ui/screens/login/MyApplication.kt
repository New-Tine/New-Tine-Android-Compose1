package com.example.newtineproject.ui.screens.login

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Kakao SDK 초기화
        KakaoSdk.init(this, "ec39346ca6cad4e61337e0ce30b08b97")
    }
}