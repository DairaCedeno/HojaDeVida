package com.example.medicoapp
import android.app.Application
import com.example.medicoapp.data.AppContainer
import com.example.medicoapp.data.AppDataContainer
class MedicoCitaApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
