import scala.pickling._
import json._

case class CanvasCoord(x: Float, y: Float)

val a:Array[CanvasCoord]= Array(
  CanvasCoord(1,2),
  CanvasCoord(3,4),
  CanvasCoord(5,6)
  )
val s:String = a.pickle.value
val jsonPckl = new JSONPickle(s)
val newA = jsonPckl.unpickle
print(10)