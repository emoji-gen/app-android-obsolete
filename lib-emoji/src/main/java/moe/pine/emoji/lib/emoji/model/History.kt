package moe.pine.emoji.lib.emoji.model

import com.google.gson.annotations.SerializedName

/**
 * Model for History
 * Created by pine on 2017/05/30.
 */
data class History(
        @SerializedName("emoji_url") val emojiUrl: String,
        @SerializedName("generated_at") val generatedAt: Long,
        @SerializedName("id") val id: Int,
        @SerializedName("parameters") val parameters: HistoryParameters
)