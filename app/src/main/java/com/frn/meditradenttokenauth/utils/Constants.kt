package com.frn.meditradenttokenauth.utils

object Constants {

    const val FORGET_PASSWORD_ARGUMENT_KEY = "email"

    const val MEDITRADENT_DATABASE = "meditradent_database"
    const val REPORT_DATABASE_TABLE = "tbl_report"
    const val DENTAL_SECTION_DATABASE_TABLE = "tbl_dental_section"

    const val LAST_ON_BOARDING_PAGE = 2

    const val PREFERENCES_NAME = "meditradent_preferences"
    const val PREF_KEY_ON_BOARDING = "on_boarding_completed"
    const val PREF_KEY_ACCESS_TOKEN = "access_token"
    const val PREF_KEY_REFRESH_TOKEN = "refresh_token"


    const val BASE_URL = "http://65.108.50.11:8500"

    enum class INVALID_PASSWORD{
        EMPTY,SIMPLE ,CAPITAL , COUNT ,MATCH
    }

    const val TOOTH_BRUSH_PERCENTAGE_UNIQUE_VALUE = 6.66

}