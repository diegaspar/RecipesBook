package com.diegaspar.recipesbook.utils

import java.io.File

class JsonUtils {

    companion object {

        fun getJSON(path: String): String {
            // Load the JSON response
            val uri = this.javaClass.classLoader.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }
    }
}