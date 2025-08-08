# LEXEL - Lenguaje de Programación

<div align="center">
  <img src="logo.jpg" alt="LEXEL Logo" width="300">
</div>

LEXEL es un lenguaje de programación moderno con sintaxis en español, diseñado para ser intuitivo y fácil de aprender. ¡No necesitas conocimientos previos de compiladores o herramientas complejas!

## 🚀 ¿Qué es LEXEL?

LEXEL es un lenguaje diseñado para ser:
- **Fácil de aprender:** Sintaxis completamente en español
- **Intuitivo:** Palabras clave familiares como `Mostrar`, `Leer`, `Si-Sino`
- **Rápido:** Se compila a código C++ optimizado
- **Simple:** Un solo comando ejecuta todo el proceso
- **Potente:** Programación orientada a objetos, funciones, y más

## 📋 ¿Qué necesitas instalar?

Solo necesitas 3 cosas básicas:

### 1. **Editor de código**
- Visual Studio Code (recomendado)
- O cualquier editor con terminal integrada

### 2. **Java JDK 24**
- Descarga desde: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- O usa OpenJDK desde tu gestor de paquetes

### 3. **Compilador C++ (g++)**
- **Windows:** Instala MinGW o usar Git Bash
- **Ubuntu/Linux:** `sudo apt install g++`
- **macOS:** `xcode-select --install`

## 📦 Instalación de LEXEL

### Paso 1: Descargar LEXEL

git clone https://github.com/FerBarahona1/cglexel.git
cd cglexel


### Paso 2: ¡Listo para usar!
No necesitas compilar nada más. Todo viene precompilado y listo.

## 🎯 Tu primer programa en LEXEL

### Paso 1: Crear tu archivo
1. Abre tu editor de código (Visual Studio Code)
2. Crea un archivo llamado `ejercicio.xl` en la carpeta del proyecto
3. Escribe tu código LEXEL (ver ejemplos abajo)

### Paso 2: Ejecutar tu programa
Abre la terminal en la carpeta del proyecto y ejecuta:


./ejecutar.sh


¡Eso es todo! El script automáticamente:
- ✅ Compila tu código LEXEL
- ✅ Genera el código C++
- ✅ Compila el ejecutable
- ✅ Ejecuta tu programa

## 💡 Ejemplo básico

Crea un archivo "ejercicio.xl" con este contenido:


Iniciar

    Mostrar "¡Hola mundo!";
   
Finalizar


Luego ejecuta:

./ejecutar.sh


## 🛠️ Más ejemplos de código LEXEL

### Variables y tipos de datos

Iniciar
    ~ Números -Esto es un comentario
    Variable edad : numero;
    Variable precio : numero;
    edad = 25;
    precio = 19.99;
    
    ~ Texto
    Variable nombre : texto;
    nombre = "Juan";
    
    ~ Valores lógicos
    Variable activo : logico;
    activo = Verdadero;
    
    ~ Constantes
    Constante numero MAX_USUARIOS : 100;
    Constante texto EMPRESA : "Mi Empresa";
    
    Mostrar "Nombre: ";
    Mostrar nombre;
    Mostrar "Edad: ";
    Mostrar edad;
    Mostrar "Activo: ";
    Mostrar activo;
Finalizar


### Operaciones matemáticas

Iniciar
    Variable a, b : numero;
    a = 10;
    b = 5;
    
    Variable suma, resta, multiplicacion, division : numero;
    suma = a + b;
    resta = a - b;
    multiplicacion = a * b;
    division = a / b;
    
    Mostrar "Suma: ";
    Mostrar suma;
    Mostrar "Resta: ";
    Mostrar resta;
    Mostrar "Multiplicación: ";
    Mostrar multiplicacion;
    Mostrar "División: ";
    Mostrar division;
Finalizar


### Entrada de datos del usuario

Iniciar
    Variable nombre : texto;
    Variable edad : numero;
    
    Mostrar "Ingresa tu nombre: ";
    Leer nombre;
    
    Mostrar "Ingresa tu edad: ";
    Leer edad;
    
    Mostrar "Hola ";
    Mostrar nombre;
    Mostrar ", tienes ";
    Mostrar edad;
    Mostrar " años";
Finalizar


### Estructuras de control

Iniciar
    Variable numero : numero;
    numero = 15;
    
    ~ Condicional Si-Sino
    Si numero > 10 Entonces
        Mostrar "El número es mayor que 10";
    Sino
        Mostrar "El número es menor o igual que 10";
    Finalizar
    
    ~ Selector (Switch)
    Selector numero {
        Caso 10 :
            Mostrar "Es exactamente 10";
            Detener;
        Caso 15 :
            Mostrar "Es exactamente 15";
            Detener;
        Otro :
            Mostrar "Es otro número";
            Detener;
    }
    Finalizar


### Bucles

Iniciar
    ~ Bucle Mientras (While)
    Variable contador : numero;
    contador = 0;
    Mientras contador < 5
        Mostrar "Contador: ";
        Mostrar contador;
        contador = contador + 1;
    Finalizar
    
    ~ Bucle Para (For)
    Variable i : numero;
    Para i = 1 Hasta 5 Hacer
        Mostrar "Iteración: ";
        Mostrar i;
    Finalizar
Finalizar


### Funciones

~ Función que calcula el factorial
Funcion factorial(n : numero) : numero
    Variable resultado : numero;
    Variable i : numero;
    resultado = 1;
    
    Para i = 1 Hasta n Hacer
        resultado = resultado * i;
    Finalizar
    
    Retornar resultado;
Finalizar

~ Función que no retorna valor
Funcion saludar(nombre : texto) : vacio
    Mostrar "¡Hola, ";
    Mostrar nombre;
    Mostrar "!";
Finalizar

Iniciar
    Variable num : numero;
    Variable fact : numero;
    
    num = 5;
    fact = factorial(num);
    
    Mostrar "Factorial de ";
    Mostrar num;
    Mostrar " es: ";
    Mostrar fact;
    
    saludar("María");
Finalizar


### Programación Orientada a Objetos (Clases)

Clase Persona
{
    Variable Privado nombre : texto;
    Variable Privado edad : numero;
    
    Metodo Publico inicializar(n : texto, e : numero) : vacio
        Este.nombre = n;
        Este.edad = e;
    Finalizar
    
    Metodo Publico mostrarInfo() : vacio
        Mostrar "Nombre: ";
        Mostrar Este.nombre;
        Mostrar "Edad: ";
        Mostrar Este.edad;
    Finalizar
    
    Metodo Publico cumplirAnios() : vacio
        Este.edad = Este.edad + 1;
        Mostrar Este.nombre;
        Mostrar " ahora tiene ";
        Mostrar Este.edad;
        Mostrar " años";
    Finalizar
}
Finalizar

Iniciar
    persona1 = Nuevo Persona();
    persona1.inicializar("Ana", 25);
    persona1.mostrarInfo();
    persona1.cumplirAnios();
    
    persona2 = Nuevo Persona();
    persona2.inicializar("Carlos", 30);
    persona2.mostrarInfo();
Finalizar


## 🎯 Ejemplo completo: Sistema de Gestión de Estudiantes


~ Sistema de Gestion Simplificado
~ Ejemplo que cubre las principales reglas de la gramatica Lexel
Clase Estudiante
{
    Variable Privado nombre : texto;
    Variable Privado promedio : numero;
    Variable Publico activo : logico;
    
    Metodo Publico establecerNombre(nom : texto) : vacio
        Este.nombre = nom;
        Este.activo = Verdadero;
        Mostrar "Estudiante registrado";
    Finalizar
    
    Metodo Publico calcularPromedio(nota1 : numero, nota2 : numero) : numero
        Este.promedio = (nota1 + nota2) / 2;
        Retornar Este.promedio;
    Finalizar
    
    Metodo Publico estaAprobado() : logico
        Si Este.promedio >= 65 Entonces
            Retornar Verdadero;
        Sino
            Retornar Falso;
        Finalizar
    Finalizar
}
Finalizar

Funcion validarNota(nota : numero) : logico
    Si nota >= 0 y nota <= 100 Entonces
        Retornar Verdadero;
    Sino
        Retornar Falso;
    Finalizar
Finalizar

Funcion mostrarMenu() : vacio
    Mostrar "1. Registrar estudiante";
    Mostrar "2. Calcular promedio";
    Mostrar "3. Verificar estado";
    Mostrar "4. Salir";
Finalizar

Iniciar
    Constante numero NOTA_APROBACION : 65;
    Constante texto SISTEMA : "Sistema de Gestion de Estudiantes";
    
    Variable nombre : texto;
    Variable opcion : numero;
    Variable continuar : logico;
    Variable nota1, nota2 : numero;
    
    estudiante1 = Nuevo Estudiante();
    
    Mostrar SISTEMA;
    continuar = Verdadero;
    Mientras continuar
        mostrarMenu();
        Leer opcion;
        
        Selector opcion {
            Caso 1 :
                Mostrar "Ingrese nombre: ";
                Leer nombre;
                estudiante1.establecerNombre(nombre);
                Detener;
                
            Caso 2 :
                Mostrar "Ingrese primera nota: ";
                Leer nota1;
                Mostrar "Ingrese segunda nota: ";
                Leer nota2;
                
                Si validarNota(nota1) y validarNota(nota2) Entonces
                    Variable promedio : numero;
                    promedio = estudiante1.calcularPromedio(nota1, nota2);
                    Mostrar "Promedio: ";
                    Mostrar promedio;
                Sino
                    Mostrar "Notas invalidas";
                Finalizar
                Detener;
                
            Caso 3 :
                Si estudiante1.estaAprobado() Entonces
                    Mostrar "APROBADO";
                Sino
                    Mostrar "REPROBADO";
                Finalizar
                Detener;
                
            Caso 4 :
                continuar = Falso;
                Detener;
                
            Otro :
                Mostrar "Opcion invalida";
                Detener;
        }
        Finalizar
    Finalizar
    
    Si estudiante1.activo Entonces
        Mostrar "Sistema finalizado correctamente";
    Finalizar
Finalizar


## 🚨 Solución de problemas comunes

### ❌ Error: "java no se reconoce como comando"
**Solución:** Java no está instalado o no está en el PATH
- Instala JDK 24
- Verifica con: `java -version`

### ❌ Error: "g++ no se reconoce como comando"
**Solución:** g++ no está instalado
- **Windows:** Instala MinGW o usa Git Bash
- **Linux:** `sudo apt install g++`
- **macOS:** `xcode-select --install`

### ❌ Error: "No such file ejercicio.xl"
**Solución:** 
- Asegúrate de que tu archivo se llame exactamente "ejercicio.xl"
- Debe estar en la misma carpeta que `run.sh`

### ❌ El programa no hace nada
**Solución:** 
- Revisa que tu código tenga la estructura: `Iniciar ... Finalizar`
- Verifica que uses `Mostrar` para mostrar resultados


## 🎯 Flujo de trabajo típico

1. **Escribir código:** Edita "ejercicio.xl" con tu código LEXEL
2. **Ejecutar:** `./run.sh` en la terminal
3. **Ver resultado:** Tu programa se ejecuta automáticamente
4. **Repetir:** Modifica el código y vuelve a ejecutar



## 📄 Licencia

Este proyecto fue desarrollado por estudiantes de la UNAH en la clase de Diseño de Compiladores.

## 📄 Autores 

- Fernanda Sarahi Betancourth
- Fredy Edgardo Castellon
- Olman Fernando Moreno

<div align="center">
  <b>¡Disfruta programando en LEXEL! 🚀</b>
</div>