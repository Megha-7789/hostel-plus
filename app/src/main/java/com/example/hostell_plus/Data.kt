package com.example.hostell_plus

data class StudentData(
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val registrationNumber: String,
    val fatherName: String,
    val motherName: String,
    val roomNumber: Int,
    val year: Int,
    val parentphone: String,
    val dob: String,
    val password: String
     // Add the image URI property
){


    class Student(name: String, email: String, year: Int,phone: String, address: String, registrationNumber: String, fatherName: String, motherName: String, roomNumber: Int,parentphone: String) {

        val roomNumber: Any = TODO()
        val motherName: Any
        val fatherName: Any
        val registrationNumber: Any
        val address: Any
        val phone: Any
        val name: Any
        val email: Any = TODO()
        val year:Any
        val parentphone:Any
        val dob :Any
        val password :Any

    }
}

class Data {

}
