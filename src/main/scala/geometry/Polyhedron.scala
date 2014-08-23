package geometry

import three.THREE
import three.THREE.PolyhedronGeometry

object Polyhedron
{
  def fromPlain(polygon:Polygon, height:Float):PolyhedronGeometry = {
    var vertexes:List[Float] = List[Float]()
    val numVertex = polygon.vertices
    for( i <- 0 to numVertex - 1)
      {
        vertexes = vertexes ::: List[Float](0, polygon.getVertex(i).x, polygon.getVertex(i).y)
      }

    for( i <- 0 to numVertex - 1)
      {
        vertexes = vertexes ::: List[Float](height, polygon.getVertex(i).x, polygon.getVertex(i).y)
      }

    var faces = List[Int]()

    for( i <- 0 to numVertex - 2)
    {
      faces = faces ::: List[Int](0, i + 1, i + 2)
      faces = faces ::: List[Int](numVertex, numVertex + i + 1, numVertex + i + 2)
      faces = faces ::: List[Int](0, i + 2, i + 1)
      faces = faces ::: List[Int](numVertex, numVertex + i + 2, numVertex + i + 1)
    }

    /* Side faces */
    for( i <- 0 to numVertex - 1)
    {
      faces = faces ::: List[Int](i, numVertex + i, numVertex + i + 1)
      faces = faces ::: List[Int](i, numVertex + i + 1, i + 1)
      faces = faces ::: List[Int](i, numVertex + i + 1, numVertex + i)
      faces = faces ::: List[Int](i, i + 1, numVertex + i + 1)
    }

      faces = faces ::: List[Int](numVertex - 1, 2*numVertex - 1, 0)
      faces = faces ::: List[Int](numVertex - 1, 0, 2*numVertex - 1)
      faces = faces ::: List[Int](numVertex - 1, numVertex, 0)
      faces = faces ::: List[Int](numVertex - 1, 0, numVertex)

    new PolyhedronGeometry(vertexes.toArray, faces.toArray)
  }
}
