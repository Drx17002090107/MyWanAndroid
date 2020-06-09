package com.example.machenike.mywanandroid.model.weather

/**
created time：2020/4/13 17:39
created by：动感超人
Describe ：
 */
data class NowModel(
    val HeWeather6: List<HeWeather6>
)

data class HeWeather6(
    val basic: Basic,
    val now: Now,
    val status: String,
    val update: Update
)

data class Basic(
    val admin_area: String,
    val cid: String,
    val cnty: String,
    val lat: String,
    val location: String,
    val lon: String,
    val parent_city: String,
    val tz: String
)

data class Now(
    val cloud: String,
    val cond_code: String,
    val cond_txt: String,
    val fl: String,
    val hum: String,
    val pcpn: String,
    val pres: String,
    val tmp: String,
    val vis: String,
    val wind_deg: String,
    val wind_dir: String,
    val wind_sc: String,
    val wind_spd: String
)

data class Update(
    val loc: String,
    val utc: String
)