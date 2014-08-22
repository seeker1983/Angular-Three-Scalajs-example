package three

import canvas._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import js.Dynamic.global


object ThreeCanvas extends BaseCanvas(700,700,"ThreeCanvas") with CanvasEventListener
{
      init()

      override def init() = {
        super.init

        Seq("pointerdown", "pointermove", "pointerup").foreach {
          s => canvas.addEventListener(s, (e: dom.Event) => event(e))
        }
      }

    def render(height:Float, wireFrame:js.Any) : Unit =
    {
      /* TODO: REMOVE */
      ThreeExample.render()
      return
      /* ------------ */

      val vertexArray = PolygonCanvas.getPolygon.vertexArray().map( x => x/height)

        global.renderPolygon(vertexArray, 1, wireFrame, 700, 700, "ThreeCanvas")
    }

    def update:Unit = {

    }

}


