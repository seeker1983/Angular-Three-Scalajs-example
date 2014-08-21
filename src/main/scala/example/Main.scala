package example

import canvas.PolygonCanvas
import panel.AngularPanel
import scala.scalajs.js.JSApp

object Main extends JSApp{
  def main(): Unit = {
  	AngularPanel.init
    PolygonCanvas.init
  }

}
