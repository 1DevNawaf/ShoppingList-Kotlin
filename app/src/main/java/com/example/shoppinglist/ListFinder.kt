package com.example.shoppinglist

class ListFinder{
    companion object{
        private val _referralsList = listOf("q1w2e3r4","g5h6j7k8","wg27klo8")
        fun codeInList(code : String) : Boolean{
            return _referralsList.contains(code)
        }
    }
}

