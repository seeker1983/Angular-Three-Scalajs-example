package three

import canvas.PolygonCanvas

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import js.Dynamic.global

object ThreeCanvas {

    def render(height:Float, wireFrame:js.Any) = {
        val vert = PolygonCanvas.getPolygon.vertexArray()
        val vert2 = vert.map( x => x/height)

        global.renderPolygon(vert2, 1, wireFrame, 700, 700, "ThreeCanvas")
    }

}


