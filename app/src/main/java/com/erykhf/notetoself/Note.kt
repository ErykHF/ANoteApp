package com.erykhf.notetoself

import org.json.JSONException
import org.json.JSONObject

class Note {

    var title: String? = null
    var description: String? = null
    var idea: Boolean = false
    var todo: Boolean = false
    var important: Boolean = false

    private val JSON_TITLE = "title"
    private val JSON_DESCRIPTION = "description"
    private val JSON_IDEA = "idea"
    private val JSON_TODO = "todo"
    private val JSON_IMPORTANT = "important"

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {

        jo.apply {
            title = getString(JSON_TITLE)
            description = getString(JSON_DESCRIPTION)
            idea = getBoolean(JSON_IDEA)
            todo = getBoolean(JSON_TODO)
            important = getBoolean(JSON_IMPORTANT)
        }
    }

    constructor() {


    }

    @Throws(JSONException::class)
    fun convertToJson(): JSONObject{
        val jo = JSONObject()

        jo.apply {
            put(JSON_TITLE, title)
            put(JSON_DESCRIPTION, description)
            put(JSON_IDEA, idea)
            put(JSON_TODO, todo)
            put(JSON_IMPORTANT, important)
        }

        return  jo

    }
}