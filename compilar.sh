#!/bin/bash


echo "🚀 Compilando y ejecutando LEXEL..."

# Verificar archivo fuente
if [ ! -f "ejercicio.xl" ]; then
    echo "❌ Error: Archivo 'ejercicio.xl' no encontrado"
    exit 1
fi

# Compilar LEXEL -> C++ -> Ejecutable -> Ejecutar
if java Main && g++ salida.cpp -o programa.exe && ./programa.exe; then
    echo "✅ Completado exitosamente"
else
    echo "❌ Error en el proceso"
    exit 1
fi