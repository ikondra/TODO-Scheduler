package model

import play.api.libs.json.{JsValue, Json}


object Task {

  var nextId: Int = 0

  def cleanString(input: String, maxLength: Int = 100): String = {
    var output = input
      .replace("&", "&amp;")
      .replace("<", "&lt;")
      .replace(">", "&gt;")
    if (output.length > maxLength) {
      output = output.slice(0, maxLength) + "..."
    }
    output
  }

  def apply(title: String, description: String, date: String): Task = {
    val thisId = nextId
    nextId += 1
    new Task(cleanString(title), cleanString(description, 1000), thisId.toString, date)
  }


}

class Task(val title: String, val description: String, var id: String, var dateTime: String) {

  def asJsValue(): JsValue ={
    val taskMap: Map[String, JsValue] = Map(
      "title" -> Json.toJson(title),
      "description" -> Json.toJson(description),
      "id" -> Json.toJson(id),
      "dateTime" -> Json.toJson(dateTime)
    )
    Json.toJson(taskMap)
  }

}
