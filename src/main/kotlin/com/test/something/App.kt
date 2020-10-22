package com.test.something

import com.test.misc.decodeString
import com.test.misc.encodeAsString
import com.dslplatform.json.CompiledJson
import com.dslplatform.json.DslJson

@CompiledJson(discriminator = "@type")
interface Base2

@CompiledJson(name = "one")
data class One(val value: String): Base2

@CompiledJson(name = "two")
data class Two(val value: Long): Base2


fun main(args: Array<String>) {
    val mapper = DslJson<Any>()

    val testList: List<Base2> = listOf(One("message"), Two(1917))
    val jsonList = mapper.encodeAsString(testList)
    val decodedList = mapper.decodeString<List<Base2>>(jsonList)
    println(decodedList)

    val hi = InterfaceTest.HasInterface()
    hi.x = 505
    hi.i = InterfaceTest.IsIfaceDefault1(-123)
    hi.c = InterfaceTest.IsIfaceDefault2(2)
    hi.ii = listOf(InterfaceTest.IsIfaceDefault1(1))

    val jsonInter = mapper.encodeAsString(hi)
    val decodedInter = mapper.decodeString<InterfaceTest.HasInterface>(jsonInter)

    println(decodedInter)
}
