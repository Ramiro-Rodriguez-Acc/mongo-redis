db = db.getSiblingDB('rutas_db');

const ruta1 = {
    _id: 1,
    nombre: "Ruta 1",
    origen: "Mendoza",
    destino: "Cordoba",
    distancia: 1200,
    intersecciones: []
};

const ruta2 = {
    _id: 2,
    nombre: "Ruta 2",
    origen: "Cordoba",
    destino: "Buenos Aires",
    distancia: 900,
    intersecciones: []
};

const ruta3 = {
    _id: 3,
    nombre: "Ruta 3",
    origen: "Santa Fe",
    destino: "Mendoza",
    distancia: 1800,
    intersecciones: []
};

const ruta4 = {
    _id: 4,
    nombre: "Ruta 4",
    origen: "Salta",
    destino: "Neuquen",
    distancia: 1300,
    intersecciones: []
};

const ruta5 = {
    _id: 5,
    nombre: "Ruta 5",
    origen: "La Pampa",
    destino: "Santiago del Estero",
    distancia: 1500,
    intersecciones: []
};

db.rutas.insertMany([ruta1, ruta2, ruta3, ruta4, ruta5]);

db.rutas.updateOne(
    { _id: ruta1._id },
    { $set: { intersecciones: [{ _id: ruta2._id, nombre: ruta2.nombre, km: 75 },
                {  _id: ruta3._id, nombre: ruta3.nombre, km: 350 }] } }
);

db.rutas.updateOne(
    { _id: ruta2._id },
    { $set: { intersecciones: [{ _id: ruta4._id, nombre: ruta4.nombre, km: 800 },
                {  _id: ruta1._id, nombre: ruta1.nombre, km: 550 }] } });

db.rutas.updateOne(
    { _id: ruta3._id },
    { $set: { intersecciones: [{ _id: ruta1._id, nombre: ruta1.nombre, km: 175 },
                {  _id: ruta5._id, nombre: ruta5.nombre, km: 250 }] } });

db.rutas.updateOne(
    { _id: ruta4._id },
    { $set: { intersecciones: [{ _id: ruta1._id, nombre: ruta1.nombre, km: 480 }] } });

db.rutas.updateOne(
    { _id: ruta5._id },
    { $set: { intersecciones: [{  _id: ruta3._id, nombre: ruta3.nombre, km: 375 }] } });

db.tipos_incidente.insertMany([
    { _id: "FOTOMULTA", gravedad: 1 },
    { _id: "CONTROL_POLICIAL", gravedad: 2 },
    { _id: "ACCIDENTE", gravedad: 3 },
    { _id: "BACHE", gravedad: 1 },
    { _id: "INCENDIO", gravedad: 3 },
    { _id: "NEBLINA", gravedad: 1 },
    { _id: "ANIMALES", gravedad: 2 },
    { _id: "PIQUETE", gravedad: 2 }
]);