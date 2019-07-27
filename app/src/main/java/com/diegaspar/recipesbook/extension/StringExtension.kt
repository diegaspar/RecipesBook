package com.diegaspar.recipesbook.extension

fun String.contains(stringList: List<String>): Boolean {
    for (string in stringList) {
        if ((this.contains(string, true))) {
            return true
        }
    }
    return false
}