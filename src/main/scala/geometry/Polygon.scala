package geometry

case class CanvasCoord(x: Float, y: Float) {
  def -(p: CanvasCoord): CanvasCoord = {
    CanvasCoord(x - p.x, y - p.y)
  }
  def +(p: CanvasCoord): CanvasCoord = {
    CanvasCoord(x + p.x, y + p.y)
  }
}


class Polygon(_vertices:Int)
{
    var vertices:Int = _vertices
    val R = 125f
    private var polygon = new Array[CanvasCoord](vertices)

    for( i <- 0 to vertices - 1 )
      {
        val x = 150f + R*math.cos(2*math.Pi/vertices*i).toFloat
        val y = 150f + R*math.sin(2*math.Pi/vertices*i).toFloat
        polygon(i) = new CanvasCoord(x, y)
      }

    def dump():Unit = {
        for ( x <- polygon ) {
           println( " " + x.x + "," + x.y )
        }
      }

    def foreach(f:CanvasCoord => Unit): Unit = polygon.foreach(f)

    def getVertex(index:Int):CanvasCoord = {
        polygon(index)
      }
    def setVertex(index:Int, coord:CanvasCoord):Unit = {
        polygon(index) = coord
      }

    def unserialize(s: String):Unit = {
        val a = s.split(",")
        vertices = a(0).toInt

        polygon = new Array[CanvasCoord](vertices)
        for(i <- 0 to vertices - 1)
        {
          polygon(i) = new CanvasCoord(a(1+2*i).toFloat,a(1+2*i + 1).toFloat)
        }
    }

  def serialize(): String = {
    var s:String = "" + vertices
    polygon.foreach(v => s+="," + v.x +"," + v.y)
    s
  }

  def vertexArray(): Array[Float] = {
    var a = new Array[Float](2*vertices)
    for(i <- 0 to vertices - 1)
    {
      a(2*i) = getVertex(i).x
      a(2*i+1) = getVertex(i).y
    }
    a
  }

}

object Polygon {
  def apply(vertices:Int) = new Polygon(vertices)
  def apply(s:String) = {
    //new Polygon(s)
  }

  def empty() = Polygon(0)
}

