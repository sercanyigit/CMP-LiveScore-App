package com.sercan.yigit.cmp_livescore_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform