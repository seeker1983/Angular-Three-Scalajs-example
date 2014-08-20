package example

import canvas.Canvas
import panel.AngularPanel
import scala.scalajs.js.JSApp

object Main extends JSApp{
  def main(): Unit = {
  	AngularPanel.init
    Canvas.init
  }

}
