package three

import canvas._
import geometry.Polyhedron
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import js.Dynamic.global

import scala.language.dynamics

class Rotate(val X: Boolean, val Y: Boolean, val Z: Boolean)

object Rotate{
  def apply(X: Boolean, Y: Boolean, Z: Boolean) = new Rotate(X, Y, Z)
//  def apply(r: js.Object) = new Rotate((r.X : js.Boolean),(r.Y:Boolean),(r.Z:Boolean))

}

object ThreeCanvas extends BaseCanvas(700,700,"ThreeCanvas") with CanvasEventListener
{
      var rotate: Rotate = new Rotate(true, true, false)

      init()

      override def init() = {
        super.init

        Seq("pointerdown", "pointermove", "pointerup").foreach {
          s => canvas.addEventListener(s, (e: dom.Event) => event(e))
        }
        dom.setInterval(() => update(), 25)
      }

  def render(height:Float, wireFrame:js.Any) : Unit =
  {
/*    val vertexArray = PolygonCanvas.getPolygon.vertexArray().map( x => x/height)

    global.renderPolygon(vertexArray, 1, wireFrame, 700, 700, "ThreeCanvas")*/

    val scene = new THREE.Scene()
    val camera = new THREE.PerspectiveCamera(75, 1, 0.1, 1000);

    val renderer = new THREE.WebGLRenderer(js.Dynamic.literal(
      //      alpha = true,
      canvas = global.document.getElementById("ThreeCanvas")
    ));

    //    val geometry = new THREE.BoxGeometry(1,1,1);
    val geometry = Polyhedron.fromPlain(PolygonCanvas.polygon, height);

    val material = new THREE.MeshBasicMaterial(js.Dynamic.literal(
      color = 0x00ff00,
      wireframe = wireFrame
    ));

    camera.position.z = 2;

    val cube = new THREE.Mesh(geometry, material);

    /*    scene.add(cube);
        renderer.render(scene, camera);*/


    global._scene = scene
    global._cube = cube
    global._camera = camera
    global._renderer = renderer

    global._scene.add(global._cube)
    global._renderer.render(global._scene, global._camera);
    dom.setInterval(() => renderFrame(), 250)
  }

  def renderFrame():Unit  = {
    //global._requestAnimationFrame(renderFrame);
    if(rotate.X)
      global._cube.rotation.x = global._cube.rotation.x + 0.1;
    if(rotate.Y)
      global._cube.rotation.y = global._cube.rotation.x + 0.1;
    if(rotate.Z)
      global._cube.rotation.z = global._cube.rotation.x + 0.1;
      global._renderer.render(global._scene, global._camera);
  };

  def update(): Unit =
  {
      //println(1)
/*    if(global._renderer != null)
      global._renderer.render(global._scene, global._camera)*/
  }

}


