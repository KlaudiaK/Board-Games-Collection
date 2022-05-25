package com.klaudiak.gamescollector


import org.junit.Test

import org.junit.Assert.*
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
/*
class SimpleXmlTest {
    val xmlToParse = """
        <user id="313730" name="rahdo" termsofuse="https://boardgamegeek.com/xmlapi/termsofuse">
<firstname value="Richard"/>
<lastname value="Ham"/>
<avatarlink value="https://cf.geekdo-static.com/avatars/avatar_id21557.jpg"/>
<yearregistered value="2010"/>
<lastlogin value="2022-05-22"/>
<stateorprovince value="Washington"/>
<country value="United States"/>
<webaddress value="http://www.rahdo.com"/>
<xboxaccount value=""/>
<wiiaccount value=""/>
<psnaccount value=""/>
<battlenetaccount value=""/>
<steamaccount value=""/>
<traderating value="488"/>
<marketrating value="51"/>
</user>
    """.trimIndent()

    @Test
    fun testXMLParse() {
        val serializer: Serializer = Persister()
        val dataFetch = serializer.read(UserExistResponses::class.java, xmlToParse)
        Assert.assertEquals(dataFetch.name, "rahdo")
        //assertEquals(dataFetch.REC.first().name, "SELLER4_MOBILEPHONE")
    }
}

 */