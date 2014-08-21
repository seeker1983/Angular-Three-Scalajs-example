package storage

import scala.scalajs.js
import js.Dynamic.global

object localStorage {
  def saveString(key:String, s:String):Unit = {
    global.localStorage.setItem(key, s)
  }

  def loadString(key:String):String = {
    "" + global.localStorage.getItem(key)
  }

  def load(key:String):Dynamic = {
    global.localStorage.getItem(key)
  }


  def saveNumber(key:String, value:Number):Unit = { saveString(key, "" + value) }
  def saveNumber(key:String, value:js.Dynamic):Unit = saveNumber(key, ("" + value).toFloat)

  def loadInt(key:String, default: Int = 0):Int= {
    val item = Option(global.localStorage.getItem(key))
    ("" + item.getOrElse(default)).toInt
  }

  def loadFloat(key:String, default: Float = 0):Float = {
    val item = Option(global.localStorage.getItem(key))
    ("" + item.getOrElse(default)).toFloat
  }


  /* TODO: rewrite as generics
  def loadNumber[U <: js.Number](key:String):Option[U]= {
    val num = Option(global.localStorage.getItem(key))
    num
  }
  */

}
