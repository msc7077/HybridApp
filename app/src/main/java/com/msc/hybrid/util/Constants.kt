package com.msc.hybrid.util

import com.msc.hybrid.BuildConfig

enum class BuildType {
    DEV,
    PRD
}

private const val ConstDevWebUrl = "https://m.naver.com"
private const val ConstPrdWebUrl = "https://msc7077.tistory.com/"

fun getBuildType(): BuildType {
    return when (BuildConfig.FLAVOR) {
        "dev" -> {
            BuildType.DEV
        }
        "prd" -> {
            BuildType.PRD
        }
        else -> {
            BuildType.PRD
        }
    }
}

private val buildType: BuildType = getBuildType()

val ConstWebUrl =
    when (buildType) {
        BuildType.DEV -> ConstDevWebUrl
        BuildType.PRD -> ConstPrdWebUrl
        else -> ConstPrdWebUrl
    }