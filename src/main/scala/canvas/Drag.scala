package canvas

import org.scalajs.dom

import scala.scalajs.js
import scala.collection.mutable

/**
 * Facade for mouse events on dom
 */
object PointerEvent extends js.Object {
  var MSPOINTER_TYPE_PEN: js.Number = _
  var MSPOINTER_TYPE_MOUSE: js.Number = _
  var MSPOINTER_TYPE_TOUCH: js.Number = _
}

class PointerEvent extends dom.MouseEvent {
  var width: js.Number = _
  var rotation: js.Number = _
  var pressure: js.Number = _
  var pointerType: js.Any = _
  var isPrimary: js.Boolean = _
  var tiltY: js.Number = _
  var height: js.Number = _
  var intermediatePoints: js.Any = _
  var currentPoint: js.Any = _
  var tiltX: js.Number = _
  var hwTimestamp: js.Number = _
  var pointerId: js.Number = _
  def initPointerEvent(typeArg: js.String, canBubbleArg: js.Boolean, cancelableArg: js.Boolean,
                       viewArg: dom.Window, detailArg: js.Number, screenXArg: js.Number,
                       screenYArg: js.Number, clientXArg: js.Number, clientYArg: js.Number,
                       ctrlKeyArg: js.Boolean, altKeyArg: js.Boolean, shiftKeyArg: js.Boolean,
                       metaKeyArg: js.Boolean, buttonArg: js.Number, relatedTargetArg: dom.EventTarget,
                       offsetXArg: js.Number, offsetYArg: js.Number, widthArg: js.Number,
                       heightArg: js.Number, pressure: js.Number, rotation: js.Number,
                       tiltX: js.Number, tiltY: js.Number, pointerIdArg: js.Number, pointerType: js.Any,
                       hwTimestampArg: js.Number, isPrimary: js.Boolean): Unit = ???
  def getCurrentPoint(element: dom.Element): Unit = ???
  def getIntermediatePoints(element: dom.Element): Unit = ???
  var MSPOINTER_TYPE_PEN: js.Number = _
  var MSPOINTER_TYPE_MOUSE: js.Number = _
  var MSPOINTER_TYPE_TOUCH: js.Number = _
}

object Touch{
  case class Down(x: Int, y: Int) extends Touch
  case class Move(x: Int, y: Int) extends Touch
  case class Up(x: Int, y: Int) extends Touch
}

sealed trait Touch

trait CanvasEventListener {
  val keys = mutable.Set.empty[Int]
  val touches = mutable.Buffer.empty[Touch]

  def event(e: dom.Event): Unit = {
    (e, e.`type`.toString) match{
      case (e: dom.KeyboardEvent, "keydown") =>  keys.add(e.keyCode.toInt)
      case (e: dom.KeyboardEvent, "keyup") =>  keys.remove(e.keyCode.toInt)
      case (e: PointerEvent, "pointerdown") => touches += Touch.Down(e.clientX, e.clientY)
      case (e: PointerEvent, "pointermove") => touches += Touch.Move(e.clientX, e.clientY)
      case (e: PointerEvent, "pointerup" | "pointerout" | "pointerleave") => touches += Touch.Up(e.clientX, e.clientY)
      case _ => println("Unknown event " + e.`type`)
    }
  }

  def update(): Unit
}
