db = db.getSiblingDB('rutas_db');

const ruta1 = {
    _id: ObjectId(),
    nombre: "Ruta 1",
    origen: "Mendoza",
    destino: "Cordoba",
    distancia: 1200,
    intersecciones: []
};

const ruta2 = {
    _id: ObjectId(),
    nombre: "Ruta 2",
    origen: "Cordoba",
    destino: "Buenos Aires",
    distancia: 900,
    intersecciones: []
};

const ruta3 = {
    _id: ObjectId(),
    nombre: "Ruta 3",
    origen: "Santa Fe",
    destino: "Mendoza",
    distancia: 1800,
    intersecciones: []
};

const ruta4 = {
    _id: ObjectId(),
    nombre: "Ruta 4",
    origen: "Salta",
    destino: "Neuquen",
    distancia: 1300,
    intersecciones: []
};

const ruta5 = {
    _id: ObjectId(),
    nombre: "Ruta 5",
    origen: "La Pampa",
    destino: "Santiago del Estero",
    distancia: 1500,
    intersecciones: []
};

db.rutas.insertMany([ruta1, ruta2, ruta3, ruta4, ruta5]);

db.rutas.updateOne(
    { _id: ruta1._id },
    { $set: { intersecciones: [{ _id: ruta2._id, nombre: ruta2.nombre, origen: ruta2.origen, destino: ruta2.destino, distancia: ruta2.distancia },
                { _id: ruta3._id, nombre: ruta3.nombre, origen: ruta3.origen, destino: ruta3.destino, distancia: ruta3.distancia }] } }
);

db.rutas.updateOne(
    { _id: ruta2._id },
    { $set: { intersecciones: [{ _id: ruta4._id, nombre: ruta4.nombre, origen: ruta4.origen, destino: ruta4.destino, distancia: ruta4.distancia },
                { _id: ruta1._id, nombre: ruta1.nombre, origen: ruta1.origen, destino: ruta1.destino, distancia: ruta1.distancia }] } }
);

db.rutas.updateOne(
    { _id: ruta3._id },
    { $set: { intersecciones: [{ _id: ruta1._id, nombre: ruta1.nombre, origen: ruta1.origen, destino: ruta1.destino, distancia: ruta1.distancia },
                { _id: ruta5._id, nombre: ruta5.nombre, origen: ruta5.origen, destino: ruta5.destino, distancia: ruta5.distancia }] } }
);

db.rutas.updateOne(
    { _id: ruta4._id },
    { $set: { intersecciones: [{ _id: ruta1._id, nombre: ruta1.nombre, origen: ruta1.origen, destino: ruta1.destino, distancia: ruta1.distancia }] } }
);

db.rutas.updateOne(
    { _id: ruta5._id },
    { $set: { intersecciones: [{ _id: ruta3._id, nombre: ruta3.nombre, origen: ruta3.origen, destino: ruta3.destino, distancia: ruta3.distancia }] } }
);