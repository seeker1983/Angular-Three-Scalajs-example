package canvas

import canvas.PolygonCanvas._
import org.scalajs.dom
import org.scalajs.dom.extensions._

abstract class BaseCanvas(val w:Int,val h:Int, canvasId:String)
{
  val canvas = dom.document.getElementById(canvasId).cast[dom.HTMLCanvasElement]

  def view = canvas.getBoundingClientRect()
  def canvasX(x: Float):Float = x - view.left.toFloat
  def canvasY(y: Float):Float = y - view.top.toFloat

  def init:Unit = {
    canvas.width = w
    canvas.height = h
  }

}
