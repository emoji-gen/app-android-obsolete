package moe.pine.emoji.lib.slack

import android.support.test.runner.AndroidJUnit4
import okhttp3.*
import org.jsoup.Jsoup
import org.jsoup.nodes.FormElement
import org.junit.Test
import org.junit.runner.RunWith
import java.net.CookieHandler
import java.net.CookieManager
import kotlin.test.assertEquals
import java.net.CookiePolicy.ACCEPT_ORIGINAL_SERVER




/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"

        val cookieManager = CookieManager(null, ACCEPT_ORIGINAL_SERVER)
        CookieHandler.setDefault(cookieManager)
        val cookieJar = JavaNetCookieJar(cookieManager)
        val client = OkHttpClient().newBuilder()
                .cookieJar(cookieJar)
                .build()

        val request = Request.Builder()
                .addHeader("User-Agent", userAgent)
                .url("https://prismrhythm.slack.com/customize/emoji")
                .build()
        val response = client.newCall(request).execute()
        val body = response.body().string()
        val doc = Jsoup.parse(body)
        val form = doc.getElementById("signin_form") as FormElement
        val formData = form.formData()

        val formBodyBuilder = FormBody.Builder()
        formData.forEach {
            when (it.key()) {
                "email" -> formBodyBuilder.add(it.key(), "")
                "password" -> formBodyBuilder.add(it.key(), "")
                else -> formBodyBuilder.add(it.key(), it.value())
            }
        }

        val request2 = Request.Builder()
                .addHeader("User-Agent", userAgent)
                .url("https://prismrhythm.slack.com/")
                .post(formBodyBuilder.build())
                .build()
        val response2 = client.newCall(request2).execute()

        val request3 = Request.Builder()
                .addHeader("User-Agent", userAgent)
                .url("https://prismrhythm.slack.com/checkcookie?redir=https%3A%2F%2Fprismrhythm.slack.com%2Fcustomize%2Femoji&amp;nojsmode=1")
                .build()
        val response3 = client.newCall(request3).execute()

        assertEquals(
                "",
                response3.body().string() + "\n"
        )

        //assertEquals("", form.outerHtml())
    }
}
