package com.saitawngpha.realmexample.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 17/02/2024.
 */
class Course: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var teacher: Teacher? = null
    var enrollStudents: RealmList<Student> = realmListOf()
}