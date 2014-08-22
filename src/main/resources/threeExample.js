function CreateGeometry(horizontalVertices, height)
{
    var numVertex = horizontalVertices.length/2;

    var vertices = [];
    /* Top vertexes */
    for(var i=0; i < numVertex; i++)
    {
        vertices.push(0, horizontalVertices[2*i], horizontalVertices[2*i + 1]);
    }
    /* Bottom vertexes */
    for(var i=0; i < numVertex; i++)
    {
        vertices.push(height, horizontalVertices[2*i], horizontalVertices[2*i + 1]);
    }
    var faces = [];

    /* Top and bottom faces */
    for(var i=0; i < numVertex-2; i++)
    {
        addFace3(0, i + 1, i + 2)
        addFace3(numVertex, numVertex + i + 1, numVertex + i + 2)
    }

    /* Side faces */
    for(var i=0; i < numVertex-1; i++)
    {
        addFace4(i, numVertex + i, numVertex + i + 1, i + 1)
    }

    /* The last (n-1 -> 0) side face */
    addFace4(numVertex - 1,	2*numVertex - 1,	numVertex, 0)

    function addFace3(a, b, c)
    {
        faces.push(a, b, c)
        faces.push(a, c, b)
    }
    function addFace4(a, b, c, d)
    {
        faces.push(a, b, c)
        faces.push(a, c, d)
        faces.push(a, c, b)
        faces.push(a, d, c)
    }

    return new THREE.PolyhedronGeometry(vertices, faces);
}

function renderPlain(horizontalVertices, height, wireFrame, w, h, canvasId)
{
    var scene = new THREE.Scene();
    var camera = new THREE.PerspectiveCamera(75, w/h, 0.1, 1000);
    var renderer = new THREE.WebGLRenderer({
        canvas: document.getElementById(canvasId),
        alpha: true
    });
    renderer.setSize(w, h);

    camera.position.z = 2;

    var geometry = CreateGeometry(horizontalVertices, height);
    var material = new THREE.MeshBasicMaterial({
        wireframe: wireFrame,
        color: "blue"
        });
    var cube = new THREE.Mesh(geometry, material);
    scene.add(cube);

    cube.rotation.x =  0.1;
    cube.rotation.y =  0.1;
    cube.rotation.z =  0.1;

    var render = function () {
        requestAnimationFrame(render);
        if(rotate.X)
            cube.rotation.x += 0.1;
        if(rotate.Y)
            cube.rotation.y += 0.1;
        if(rotate.Z)
            cube.rotation.z += 0.1;
        renderer.render(scene, camera);
    };

    render();

    this.cube = cube
}

document.onkeydown = checkKey;

function checkKey(e) {
    e = e || window.event;

    if (e.keyCode == '38') {
        scene.cube.rotation.y += 0.1;
        e.preventDefault();

        // up arrow
    }
    else if (e.keyCode == '40') {
        scene.cube.rotation.y -= 0.1;
        e.preventDefault();

        // down arrow
    }
    else if (e.keyCode == '37') {
        scene.cube.rotation.x += 0.1;
        e.preventDefault();

        // left arrow
    }
    else if (e.keyCode == '39') {
        scene.cube.rotation.x -= 0.1;
        e.preventDefault();

        // right arrow
    }
    else if (e.keyCode == 'Q'.charCodeAt(0)) {
        scene.cube.rotation.z += 0.1;
        e.preventDefault();

        // right arrow
    }
    else if (e.keyCode == 'E'.charCodeAt(0)) {
        scene.cube.rotation.z -= 0.1;
        e.preventDefault();

        // right arrow
    }
    else if (e.keyCode == ' '.charCodeAt(0)) {
        rotation = ! rotation;

        // right arrow
    }
}


function renderPolygon(horizontalVertices, height, wireFrame, w, h, targetId)
{
    scene = new renderPlain(horizontalVertices, height, wireFrame, w, h, targetId)
    return scene
}


function ThreeExample()
{
    horizontalVertices =
        [
            1, 0,
            0, 1,
            1, 2,
            3, 2,
            4, 1,
            3, 0
        ]

    scene = new renderPlain(horizontalVertices, 2, 700, 700, "ThreeCanvas")
}

BigTest = new function() {
    this.test = function (value){
        console.log(value+100)
    }
}
function test (value) {
    console.log(value + 100)
}