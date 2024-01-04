package com.fabscorp.kotlinmvvmexample

class PersonRepository {
    fun loging(email: String, passw: String): Boolean {
        return  (email != "" && passw != "")
    }
}