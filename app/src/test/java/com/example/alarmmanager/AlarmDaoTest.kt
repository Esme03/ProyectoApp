package com.example.alarmmanager


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.alarmmanager.Dao.AlarmDao
import com.example.alarmmanager.database.AlarmDatabase
import com.example.alarmmanager.model.Alarm
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class AlarmDaoTest {

    private lateinit var db: AlarmDatabase
    private lateinit var alarmDao: AlarmDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AlarmDatabase::class.java).build()
        alarmDao = db.alarmDao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndRetrieveAlarm() = runBlocking {
        val alarm = Alarm(id = 1, name = "Morning Alarm", hour = 6, minute = 30, state = "AM", checked = true)
        alarmDao.insertAlarm(alarm)

        val retrievedAlarm = alarmDao.getAlarmById(1)
        assertEquals(alarm.name, retrievedAlarm?.name)
        assertEquals(alarm.hour, retrievedAlarm?.hour)
        assertEquals(alarm.minute, retrievedAlarm?.minute)
    }

    @Test
    fun testDeleteAlarm() = runBlocking {
        val alarm = Alarm(id = 2, name = "Night Alarm", hour = 10, minute = 0, state = "PM", checked = false)
        alarmDao.insertAlarm(alarm)

        alarmDao.deleteAlarm(alarm)
        val retrievedAlarm = alarmDao.getAlarmById(2)
        assertNull(retrievedAlarm)
    }
}
