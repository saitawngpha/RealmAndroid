package com.saitawngpha.realmexample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saitawngpha.realmexample.models.Address
import com.saitawngpha.realmexample.models.Course
import com.saitawngpha.realmexample.models.Student
import com.saitawngpha.realmexample.models.Teacher
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 17/02/2024.
 */
class MainViewModel: ViewModel() {
    private val realm = App.realm

    /// "enrollStudents.name == $0",
    //            "Mong Pha"
    /// "enrollStudents.@count >= 2"

    val courses = realm
        .query<Course>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    var courseDetails: Course? by mutableStateOf(null)
        private set
    init {
        //createSampleEntries()
    }

    fun showCourseDetails(course: Course) {
        courseDetails = course
    }

    fun hideCourseDetails() {
        courseDetails = null
    }

    private fun createSampleEntries() {
        viewModelScope.launch {
            realm.write {
                val address1 = Address().apply{
                    fullName = "Tawng Pha"
                    street = "Mong Tai Street"
                    houseNumber = 123
                    zip = 12345
                    city = "Taunggyi"
                }

                val address2 = Address().apply{
                    fullName = "Ying Hom Pha"
                    street = "Mong Tai Street"
                    houseNumber = 124
                    zip = 12345
                    city = "Taunggyi"
                }

                val course1 = Course().apply {
                    name = "Kotlin Programming Made Easy"
                }

                val course2 = Course().apply {
                    name = "Flutter Programming Made Easy"
                }

                val course3 = Course().apply {
                    name = "SwiftUI Programming Made Easy"
                }

                val course4 = Course().apply {
                    name = "Python Programming Made Easy"
                }

                val teacher1 = Teacher().apply {
                    address = address1
                    course = realmListOf(course1, course2)
                }

                val teacher2 = Teacher().apply {
                    address = address2
                    course = realmListOf(course3, course4)
                }

                course1.teacher = teacher1
                course2.teacher = teacher1
                course3.teacher = teacher2
                course4.teacher = teacher2

                address1.teacher = teacher1
                address2.teacher = teacher2

                val student1 = Student().apply {
                    name = "Mork Pha"
                }

                val student2 = Student().apply {
                    name = "Mao Pha"
                }

                val student3 = Student().apply {
                    name = "Mong Pha"
                }

                course1.enrollStudents.add(student1)
                course2.enrollStudents.add(student2)
                course3.enrollStudents.addAll(listOf(student1,student2,student3))
                course4.enrollStudents.add(student3)

                copyToRealm(teacher1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(teacher2, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(course1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course3, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course4, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(student1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student3, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }

    fun deleteCourse() {
        viewModelScope.launch {
            realm.write {
                val course = courseDetails ?: return@write
                val latestCourse = findLatest(course) ?: return@write
                delete(latestCourse)

                courseDetails = null
            }
        }
    }
}