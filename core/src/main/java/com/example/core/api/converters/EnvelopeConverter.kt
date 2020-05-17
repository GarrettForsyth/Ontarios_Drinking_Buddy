package com.example.core.api.converters

import com.example.core.api.LCBOApiResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

/**
 * Unwraps the LCBO API's response and forwards only the results,
 * disregarding the meta data.
 */
internal class EnvelopeConverter<T>(
    private val delegate: Converter<ResponseBody, LCBOApiResponse<T>>
) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(responseBody: ResponseBody): T {
        val envelope = delegate.convert(responseBody)!!
        return envelope.result
    }
}
