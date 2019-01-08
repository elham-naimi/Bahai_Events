package com.elna.holyday



import android.util.JsonReader
import com.elna.holyday.datetime.DateTime
import com.google.gson.JsonParser
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import org.apache.commons.io.IOUtils
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


    var list = ArrayList<HolyDay>()
    @BeforeAll
    fun initHolyDays(){

        System.out.print(ExampleUnitTest::class.java.getResourceAsStream("holyday.json"))
        ExampleUnitTest::class.java!!.getClassLoader().javaClass
        val stream = ExampleUnitTest::class.java!!.getResourceAsStream("holyday.json")



        //var stream : InputStream = getSystemResource("holyday.json").openStream()


        val reader = JsonReader(InputStreamReader(stream, "UTF-8"))
        val result = IOUtils.toString(stream, StandardCharsets.UTF_8)
        val jsonParser = JsonParser()
        val jsonObject = jsonParser.parse(
                InputStreamReader(stream, "UTF-8")) as JSONObject
        var array  : JSONArray = jsonObject.get("years") as JSONArray
        for(i in 0 until array.length()){
            var jsonObject =  array.get(i) as JSONObject
            var data : JSONArray = jsonObject.get("holyDays") as JSONArray

            for (i in 0 until data.length()) {
                var jsonObject =  data[i] as JSONObject

                    val formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss")
                    val date: LocalDateTime = LocalDateTime.parse((jsonObject.get("holyDayWhen") as CharSequence?), formatter)
                    list.add(HolyDay((jsonObject).get("holyDayName") as String, date))
                }
            }

        }


    @Test
    fun getUpcomingHolyDayIndex() {

        val car = mockkClass(DateTime::class)
        every { car.getCurrentDateTime() } returns LocalDateTime.now()


        var list : ArrayList<HolyDay>

       // list.add()
      //  Presenter.getUpcomingHolyDayIndex()
        assertEquals(4, 2 + 2)
    }
}
