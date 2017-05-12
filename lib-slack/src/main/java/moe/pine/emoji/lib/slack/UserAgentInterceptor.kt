package moe.pine.emoji.lib.slack

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for the User-Agent header
 * Created by pine on 4/4/17.
 */

internal class UserAgentInterceptor(val userAgent: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request = chain?.request()
        val newRequest = request?.newBuilder()
                ?.header("User-Agent", this.userAgent)
                ?.build()
        return chain?.proceed(newRequest)
    }
}