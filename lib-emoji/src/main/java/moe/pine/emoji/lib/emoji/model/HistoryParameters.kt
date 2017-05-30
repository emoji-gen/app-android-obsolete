package moe.pine.emoji.lib.emoji.model

import com.google.gson.annotations.SerializedName

/**
 * Model for history parameters
 * Created by pine on 2017/05/30.
 */
class HistoryParameters(
        @SerializedName("align") val align: String,
        @SerializedName("back_color") val backColor: String,
        @SerializedName("color") val color: String,
        @SerializedName("font") val font: String,
        @SerializedName("size_fixed") val sizeFixed: Boolean,
        @SerializedName("stretch") val stretch: Boolean,
        @SerializedName("text") val text: String
)