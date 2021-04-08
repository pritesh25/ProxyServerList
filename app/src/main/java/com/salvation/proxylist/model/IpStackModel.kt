package com.salvation.proxylist.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class IpStackModel(
    @SerializedName("city") val city: String?, // Jakarta
    @SerializedName("continent_code") val continentCode: String?, // AS
    @SerializedName("continent_name") val continentName: String?, // Asia
    @SerializedName("country_code") val countryCode: String?, // ID
    @SerializedName("country_name") val countryName: String?, // Indonesia
    @SerializedName("ip") val ip: String?, // 36.66.216.82
    @SerializedName("latitude") val latitude: Double?, // -6.173799991607666
    @SerializedName("location") val location: Location?,
    @SerializedName("longitude") val longitude: Double?, // 106.82669830322266
    @SerializedName("region_code") val regionCode: String?, // JK
    @SerializedName("region_name") val regionName: String?, // Jakarta
    @SerializedName("type") val type: String?, // ipv4
    @SerializedName("zip") val zip: String? // 10110
) {
    @Keep
    data class Location(
        @SerializedName("calling_code") val callingCode: String?, // 62
        @SerializedName("capital") val capital: String?, // Jakarta
        @SerializedName("country_flag") val countryFlag: String?, // http://assets.ipstack.com/flags/id.svg
        @SerializedName("country_flag_emoji") val countryFlagEmoji: String?, // 🇮🇩
        @SerializedName("country_flag_emoji_unicode") val countryFlagEmojiUnicode: String?, // U+1F1EE U+1F1E9
        @SerializedName("geoname_id") val geonameId: Int?, // 1642911
        @SerializedName("is_eu") val isEu: Boolean?, // false
        @SerializedName("languages") val languages: List<Language?>?
    ) {
        @Keep
        data class Language(
            @SerializedName("code") val code: String?, // id
            @SerializedName("name") val name: String?, // Indonesian
            @SerializedName("native") val native: String? // Bahasa Indonesia
        )
    }
}