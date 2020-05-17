package com.example.core.api.converters

import com.example.core.api.LCBOApiResponse
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnvelopeConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val resultType = TypeToken.getParameterized(LCBOApiResponse::class.java, type).type
        val delegate: Converter<ResponseBody, LCBOApiResponse<Any>> =
            retrofit.nextResponseBodyConverter(this, resultType, annotations)
        return EnvelopeConverter(delegate)
    }

}
