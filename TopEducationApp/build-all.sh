#!/bin/bash

# Lista de carpetas donde se encuentran los Dockerfiles
carpetas=("config-service" "eureka-service" "gateway-service" "installment-service" "score-service" "student-service" "filemanager-service" "adminoffice-service")

# Fronted folder = top-education-frontend

# Directorio base donde se encuentra el script
directorio_base=$(pwd)

# Iterar sobre cada carpeta y ejecutar el comando 'docker build'
for carpeta in "${carpetas[@]}"; do
    # Cambiar al directorio de la carpeta actual
    cd "$directorio_base/$carpeta" || exit

    # Obtener el nombre de la imagen (removiendo caracteres especiales y espacios)
    nombre_imagen="tem-$carpeta"

    # Construir la imagen Docker en la carpeta actual
    docker build -t $nombre_imagen .

    # Volver al directorio base
    cd "$directorio_base" || exit

    echo "--------------------------------------------------"
done