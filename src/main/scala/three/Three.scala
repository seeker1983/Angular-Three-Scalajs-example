package three

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.Dynamic._
import scala.scalajs.js.annotation.JSName

object THREE extends js.Object {
  @JSName ("THREE.Scene")
  class Scene extends js.Object
  {
       def add(obj: THREE.Mesh) = ???
  }

  class Camera extends js.Object
  {
       object position(var x:js.Number, var y:js.Number, var z:js.Number)
  }

  @JSName ("THREE.PerspectiveCamera")
  class PerspectiveCamera(a:js.Number,b:js.Number,c:js.Number,d:js.Number) extends Camera

  @JSName ("THREE.WebGLRenderer")
  class WebGLRenderer(params:js.Dynamic = ) extends js.Object
  {
    def render(scene:THREE.Scene, camera:THREE.camera)
  }

  @JSName ("THREE.BoxGeometry")
  class BoxGeometry(a:js.Number,b:js.Number,c:js.Number) extends js.Object

  @JSName ("THREE.MeshBasicMaterial")
  class MeshBasicMaterial(params:js.Dynamic) extends js.Object

  @JSName ("THREE.Mesh")
  class Mesh(geometry:js.Dynamic, material:js.Dynamic) extends js.Object

}




object ThreeExample
{
  def render() =
  {
    val scene = new THREE.Scene()
    val camera = new THREE.PerspectiveCamera(75, 1, 0.1, 1000);

    val renderer = new THREE.WebGLRenderer();
    dom.document.body.appendChild(renderer.domElement);

    val geometry = new THREE.BoxGeometry(1,1,1);

    val material = new THREE.MeshBasicMaterial(js.Dynamic.literal(
      color = 0x00ff00
    ));

    val cube = new THREE.Mesh(geometry, material);
    scene.add(cube);

    camera.position.z = 2;

    renderer.render(scene, camera);

    //StdGlobalScope.test(150)*/


//    var scene = new THREE.Scene();
//    var camera = new THREE.PerspectiveCamera(75, 1, 0.1, 1000);
//
//    var renderer = new THREE.WebGLRenderer();
//    document.body.appendChild(renderer.domElement);
//
//    var geometry = new THREE.BoxGeometry(1,1,1);
//    var material = new THREE.MeshBasicMaterial({color: 0x00ff00});
//    var cube = new THREE.Mesh(geometry, material);
//    scene.add(cube);
//
//    camera.position.z = 2;
//
//    renderer.render(scene, camera);
  }
}

object StdGlobalScope extends js.GlobalScope {
  def test(value: js.Number): Unit = ???
}