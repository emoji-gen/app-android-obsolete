package moe.pine.emoji.lib.emoji

import java.io.IOException

/**
 * ApiCallback interface for ApiClient
 * Created by pine on May 7, 2017.
 */
interface ApiCallback<T> {
    fun onFailure(e: IOException?) = Unit
    fun onResponse(response: T) = Unit
}