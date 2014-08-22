package canvas

import geometry.{CanvasCoord, Polygon}

import scala.scalajs.js
import scala.scalajs.js.{JSON, JSApp}
import scala.scalajs.js.Dynamic.{global => g, _}
import org.scalajs.dom.extensions._
import org.scalajs.dom
import scala.collection
import storage.localStorage

object PolygonCanvas extends BaseCanvas(300, 300, "polygon") with CanvasEventListener{

  var dragOn:Boolean = false
  var dragVertex:Int = -1
  var polygon:Polygon = Polygon.empty
  val ctx = canvas.getContext("2d").cast[dom.CanvasRenderingContext2D]

  var delta:CanvasCoord = CanvasCoord(0,0)

  override def init:Unit = {
    super.init

    Seq("pointerdown", "pointermove", "pointerup").foreach{
      s => canvas.addEventListener(s, (e: dom.Event) => event(e))
     }

    dom.setInterval(() => update(), 25)
  }

  def reset(vertices:Int) : Unit = {
    polygon = Polygon(vertices)

    savePolygon
    render
  }

  def render() : Unit = {

    /* Background */
    var gradient=ctx.createLinearGradient(0,0,0,300);
    gradient.addColorStop(0,"#777777");
    gradient.addColorStop(1,"#7777ff");
    ctx.fillStyle=gradient;

    ctx.beginPath();
    ctx.moveTo(0,0);
    ctx.lineTo(0,w);
    ctx.lineTo(h,w);
    ctx.lineTo(h,0);
    ctx.fill();

    /* Polygon */
    gradient=ctx.createLinearGradient(0,0,0,300);
    gradient.addColorStop(0,"#777777");
    gradient.addColorStop(1,"#77ff77");
    ctx.fillStyle=gradient;

    ctx.beginPath();


    ctx.moveTo(polygon.getVertex(polygon.vertices-1).x,polygon.getVertex(polygon.vertices-1).y);

    polygon.foreach(p => ctx.lineTo(p.x, p.y))


    ctx.stroke();
    ctx.fill();

    polygon.foreach(drawVertex(_))
    if(dragOn)
      drawVertex(polygon.getVertex(dragVertex), "blue")

  }

  def drawVertex(center: CanvasCoord, fillColor:String = "green", strokeColor:String ="white")
  {
      ctx.beginPath();
      ctx.arc(center.x, center.y, 8, 0, 2 * Math.PI, false);
      ctx.fillStyle = fillColor;
      ctx.fill();
      ctx.lineWidth = 5;
      ctx.strokeStyle =  strokeColor;
      ctx.stroke();
  }

  def isCloseToVertex(x: Float, y: Float): Boolean = {
    var found = false
    for(i <- 0 to polygon.vertices - 1 ) {
      if (math.abs(polygon.getVertex(i).x - canvasX(x)) + math.abs(polygon.getVertex(i).y - canvasY(y)) < 20)
        {
          found = true
          dragVertex = i
          delta = polygon.getVertex(i) - CanvasCoord(canvasX(x), canvasY(y))
        }
    }
    found
  }


  def savePolygon() = {
    dom.localStorage.setItem("polygon", polygon.serialize())
  }

  def loadPolygon():Boolean = {
    var polygonData = localStorage.load("polygon")
    if(polygonData != null)
    {
      polygon.unserialize("" + polygonData)
      render()
      true
    }
    else
       false
  }

  def getPolygon:Polygon = { polygon}

  def update():Unit = {
    touches.foreach( event => event match {
      case Touch.Up(x, y) => dragOn = false; render
      case Touch.Down(x, y) if (isCloseToVertex(x, y)) => dragOn = true
      case Touch.Move(x, y) if (dragOn) => {
        polygon.setVertex(dragVertex, CanvasCoord(canvasX(x), canvasY(y)) + delta)

        savePolygon
        render
//        g.localStorage.setItem("polygon", "Smith");
      }
      case _ => ()
    })

    touches.clear()
  }

}
