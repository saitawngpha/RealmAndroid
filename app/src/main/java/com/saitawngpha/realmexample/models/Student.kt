package com.saitawngpha.realmexample.models

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 17/02/2024.
 */
class Student: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    val enrollCourses: RealmResults<Course> by backlinks(Course::enrollStudents)
}