package com.saitawngpha.realmexample.models

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 17/02/2024.
 */

// Teacher 1-to-1 Address
// Teacher 1-to-many Course
// Students many-to-many Course

class Address: EmbeddedRealmObject {
   // @PrimaryKey var _id: ObjectId = ObjectId()
    var fullName: String = ""
    var street: String = ""
    var houseNumber: Int = 0
    var zip: Int = 0
    var city: String = ""
    var teacher: Teacher? = null
}

