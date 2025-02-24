# Para levantar aplicacion, 
## Con podman:

````
podman build -f dockerfile -t rutas-mongo-redis
````

````
podman compose up
````

## Con Docker:
````
docker build -f dockerfile -t rutas-mongo-redis
````

````
docker-compose up
````

# Para Probar la applicacion puede utilizar la coleccion de postman. (tener en cuenta modificar id de incidente por uno valido)
