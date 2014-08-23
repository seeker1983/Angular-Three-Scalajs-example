package three

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

object THREE extends js.Object
{
  @JSName ("THREE.Vector3")
  class Vector3(var x:js.Number, var y:js.Number, var z:js.Number) extends js.Object

  @JSName ("THREE.Scene")
  class Scene extends js.Object
  {
       def add(obj: js.Object):Unit = ???
  }

  class Camera extends js.Object

  @JSName ("THREE.PerspectiveCamera")
  class PerspectiveCamera(a:js.Number,b:js.Number,c:js.Number,d:js.Number) extends Camera
  {
    var position:Vector3 = _
  }

  @JSName ("THREE.WebGLRenderer")
  class WebGLRenderer(params:js.Dynamic) extends js.Object
  {
    def render(scene:Scene, camera:Camera) = ???
    var domElement : js.Dynamic = _
  }

  class SimpleWebGLRenderer() extends WebGLRenderer(null)

  class Geometry extends js.Object

  @JSName ("THREE.BoxGeometry")
  class BoxGeometry(a:js.Number,b:js.Number,c:js.Number) extends Geometry

  @JSName ("THREE.PolyhedronGeometry")
  class PolyhedronGeometry(vertices:Array[Float], faces:Array[Int]) extends Geometry

  class Material extends js.Object

  @JSName ("THREE.MeshBasicMaterial")
  class MeshBasicMaterial(params:js.Dynamic) extends Material

  class SimpleMeshBasicMaterial() extends MeshBasicMaterial(null)

  @JSName ("THREE.Mesh")
  class Mesh(geometry:Geometry, material:Material) extends js.Object

}



