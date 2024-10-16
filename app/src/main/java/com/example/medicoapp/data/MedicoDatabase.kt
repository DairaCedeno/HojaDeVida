package com.example.medicoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.medicoapp.data.Cita

import java.util.concurrent.Executors
@Database(entities = [Medico::class, Cita::class], version = 1, exportSchema = false)
abstract class MedicoDatabase : RoomDatabase() {
    abstract fun medicoDao(): MedicoDao
    abstract fun citaDao(): CitaDao
    companion object {
        @Volatile
        private var instance: MedicoDatabase? = null
        fun getDatabase(context: Context): MedicoDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MedicoDatabase::class.java, "medico_database")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadExecutor().execute {
                                instance?.let {
                                    for (medico in DataGeneratorMedico.getMedicos()) {
                                        it.medicoDao().insertMedico(medico)
                                    }
                                    for (cita in DataGeneratorMedico.getCitas()) {
                                        it.citaDao().insertCita(cita)
                                    }
                                }
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
class DataGeneratorMedico {
    companion object {
        fun getMedicos(): List<Medico> {
            return listOf(
                Medico(1, "Dr. Elias Pérez", "Cardiología", ""),
                Medico(2, "Dr. Jose Vargas", "Pediatría", ""),
                Medico(3, "Dra. Daira Cedeño", "Medicina General", ""),
                Medico(4, "Dra. Elizabeth Pazmiño", "Veterinaria", "").
            )
        }fun getCitas(): List<Cita> {
            return listOf(
                Cita(1, 3, "2024-10-20", "Chequeo general"),
                Cita(2, 1, "2024-10-21", "Consulta de corazón"),
                Cita(3, 2, "2024-10-22", "Consulta de niño"),
                Cita(4, 4, "2024-10-23", "Revisión periodica")
            )
        }
    }
}
