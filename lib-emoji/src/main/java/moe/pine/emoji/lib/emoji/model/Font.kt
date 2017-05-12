package moe.pine.emoji.lib.emoji.model

import com.google.gson.annotations.SerializedName

/**
 * Model for font
 * Created by pine on May 7, 2017.
 */
data class Font(
        @SerializedName("name") val name: String,
        @SerializedName("key") val key: String
)