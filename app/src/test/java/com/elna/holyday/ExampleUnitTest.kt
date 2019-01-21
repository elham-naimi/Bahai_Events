package com.elna.holyday



import android.util.JsonReader
import com.elna.holyday.model.Event
import com.elna.holyday.util.DateTime
import com.google.gson.JsonParser
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.json.JSONArray
import org.json.JSONObject

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.nio.charset.StandardCharsets
import java.io.InputStreamReader


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ExampleUnitTest {


    var list = ArrayList<Event>()
    @BeforeAll
    fun initHolyDays(){
        System.out.print(ExampleUnitTest::class.java.getResourceAsStream("holyday.json"))
        ExampleUnitTest::class.java!!.getClassLoader().javaClass
        val stream = ExampleUnitTest::class.java!!.getResourceAsStream("holyday.json")
    }


    @Test
    fun getUpcomingHolyDayIndex() {
        val car = mockkClass(DateTime::class)
        every { car.getCurrentDateTime() } returns LocalDateTime.now()
        assertEquals(4, 2 + 2)
    }
}
