# LEXEL - Lenguaje de Programación

<div align="center">
  <img src="logo.jpg" alt="Lexel Logo" width="300">
</div>

LEXEL es un lenguaje de programación de alto nivel con sintaxis en español, diseñado para ser intuitivo y fácil de aprender. Este proyecto incluye un compilador completo que traduce código LEXEL a C++ utilizando ANTLR para el análisis sintáctico.

## 🚀 Características

- **Sintaxis en español**: Palabras clave y estructuras familiares en español
- **Tipos de datos**: Variables numéricas y lógicas
- **Estructuras de control**: Bucles `Mientras`, `Para`, y condicionales `Si-Sino`
- **Entrada/Salida**: Comandos `Mostrar` y `Leer` para interacción con el usuario
- **Compilación a C++**: Genera código C++ optimizado que se compila con GCC

## 📋 Requisitos

- **JaDK 24** - Para ejecutar ANTLR y el compilador
- **ANTLR 4** - Para el análisis sintáctico
- **GCC** - Para compilar el código C++ generado

### Instalación de dependencias

```bash
# Instalar ANTLR (en sistemas Unix/Linux)
curl -O https://www.antlr.org/download/antlr-4.x.x-complete.jar
# Mover a directorio apropiado y configurar CLASSPATH

# En Ubuntu/Debian
sudo apt install gcc default-jdk

# En macOS con Homebrew
brew install gcc openjdk antlr
```

## 🛠️ Instalación y Uso

### Paso 1: Clonar el repositorio
```bash
git clone https://github.com/FerBarahona1/cglexel.git
cd cglexel
```

### Paso 2: Compilar el analizador con ANTLR
```bash
antlr4 Analizador.g4 -visitor
javac *.java
```

### Paso 3: Crear tu programa LEXEL
Crea un archivo con extensión `.xl` (por ejemplo, `ejemplo.xl`):

```lexel
Iniciar
    Mostrar "Hola, mundo desde LEXEL!";
Finalizar
```

### Paso 4: Compilar y ejecutar
```bash
# Compilar LEXEL a C++
java Main

# Compilar C++ a ejecutable
g++ salida.cpp -o program.exe

# Ejecutar el programa
./program.exe


## 🏗️ Arquitectura del Compilador

### Componentes principales

1. **Analizador.g4** - Gramática ANTLR que define la sintaxis de LEXEL
2. **Main.java** - Punto de entrada principal del compilador
3. **CodeGenerator** - Generador de código C++ a partir del AST
4. **Visitor Pattern** - Implementación del patrón visitante para recorrer el AST

### Flujo de compilación

```
archivo.xl → ANTLR → AST → CodeGenerator → salida.cpp → GCC → ejecutable
```

## 🔧 CodeGenerator con ANTLR

El CodeGenerator utiliza el patrón Visitor de ANTLR para recorrer el Árbol de Sintaxis Abstracta (AST) y generar código C++ equivalente.

### Implementación del Visitor

```java
public class LexelCodeGenerator extends AnalyzerBaseVisitor<String> {
    
    @Override
    public String visitPrograma(AnalyzerParser.ProgramaContext ctx) {
        // Generar código C++ para el programa principal
    }
    
    @Override 
    public String visitDeclaracion(AnalyzerParser.DeclaracionContext ctx) {
        // Manejar declaraciones de variables
    }
    
    // Más métodos visit para cada regla de la gramática...
}
```

### Generación de código

El generador traduce cada construcción de LEXEL a su equivalente en C++:

- `Mostrar` → `cout`
- `Leer` → `cin`
- `Variable nombre : numero` → `double nombre`
- `Variable nombre : logico` → `bool nombre`

