package com.xjk.base.net.http.converter

import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.xjk.base.BuildConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter

/**
 * 返回值data为空或者data直接为常量的转换.
 * Create by YangYang on 2018/11/7 11:53.
 */
class AbnormalResponseBodyConverter<T> constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    private val keyData = "result"
    private val keyReason = "reason"
    private val keyCode = "code"

    override fun convert(value: ResponseBody): T {
        if (value.contentLength() > Int.MAX_VALUE) {
            // 超出String字符串的长度
            value.use {
                val jsonReader = gson.newJsonReader(it.charStream())
                return adapter.read(jsonReader)
            }
        }

        var resStr = String(value.bytes())
        val resJsonOb = JSONObject(resStr)
        if (resStr.isNotEmpty()
            && resStr.startsWith("{")
            && resJsonOb.has(keyCode)
            && resJsonOb.has(keyReason)
            && (!resJsonOb.has(keyData) || resJsonOb.getString(keyData) == "null")
        ) {
            val code = resJsonOb.getInt(keyCode)
            val reason = resJsonOb.getString(keyReason)
            try {
                // 尝试转换成为any
                val nullObjectJson = GsonUtils.toJson(NullToAnyTemp(code, reason))
                adapter.fromJson(nullObjectJson)
                resStr = nullObjectJson
            } catch (e: Exception) {
                // 尝试转换成list
                try {
                    val nullListJson = GsonUtils.toJson(NullToListTemp(code, reason))
                    adapter.fromJson(nullListJson)
                    resStr = nullListJson
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (BuildConfig.DEBUG) {
                        Log.e("BodyConverter", "data is not list and object")
                    }
                }
            }
        }
        return adapter.fromJson(resStr)
    }
}