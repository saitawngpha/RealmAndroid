package com.saitawngpha.realmexample

import android.app.Application
import com.saitawngpha.realmexample.models.Address
import com.saitawngpha.realmexample.models.Course
import com.saitawngpha.realmexample.models.Student
import com.saitawngpha.realmexample.models.Teacher
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 17/02/2024.
 */
class App: Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()

        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Address::class,
                    Teacher::class,
                    Student::class,
                    Course::class,
                )
            )
        )
    }
}