package panel

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.angular._
import js.Dynamic.global
import org.scalajs.dom
import storage.localStorage
import three.ThreeCanvas

import canvas.PolygonCanvas

object AngularPanel {
    def saveVertices(value:Int):Unit = { localStorage.saveNumber("vertices", value) }
    def loadVertices():Int = {
      localStorage.loadInt("vertices", 6)
    }

    def init() : Unit = {
        val module = angular.module("AngularThree", Array.empty[String])

        module.controller(
          "SimpleController",
          Array(
            "$scope", { scope: Scope with js.Dynamic =>
              scope.height = localStorage.loadFloat("height", 20)

              scope.saveHeight = { () =>
                localStorage.saveNumber("height", scope.height)
              }: js.Function

              scope.SetVertices = { (value:Int) =>
                if(value >=3)
                  {
                  saveVertices(value)
                  scope.vertices = value
                  PolygonCanvas.reset(value)
                  }
              }: js.Function

              if(PolygonCanvas.loadPolygon())
                scope.vertices = PolygonCanvas.polygon.vertices
              else
                scope.SetVertices(loadVertices())

              scope.Hide = { () =>
                dom.document.getElementById("ThreeHolder").removeChild(dom.document.getElementById("ThreeCanvas"));
              }: js.Function
              scope.setRotation = { () =>
                global.rotate=scope.rotate;
              }: js.Function
              scope.Render = { () =>
                ThreeCanvas.render(("" + scope.height).toFloat, scope.wireFrame)
              }: js.Function
            }: js.Function
          )
        )

    }
}
