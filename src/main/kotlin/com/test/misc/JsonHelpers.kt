package com.test.misc

import com.dslplatform.json.DslJson
import com.dslplatform.json.runtime.TypeDefinition
import java.io.ByteArrayOutputStream

inline fun <reified T> jsonTypeRef(): TypeDefinition<T> = object : TypeDefinition<T>() {}

inline fun <reified T> DslJson<*>.decodeBytes(bytes: ByteArray): T =
        this.deserialize(jsonTypeRef<T>().type, bytes, bytes.size) as T

inline fun <reified T> DslJson<*>.decodeString(value: String): T =
    this.decodeBytes(value.toByteArray())

inline fun <reified T> DslJson<*>.encodeAsBytes(value: T): ByteArray =
        ByteArrayOutputStream().let {  out ->
            this.serialize(value, out)
            out.toByteArray()
        }

inline fun <reified T> DslJson<*>.encodeAsString(value: T): String =
        ByteArrayOutputStream().let {  out ->
            this.serialize(value, out)
            out.toString()
        }
