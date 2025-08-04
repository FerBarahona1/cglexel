import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String inputFile = "ejercicio.xl"; // Archivo de entrada con extensión .lxl para Lexel
        String outputFile = "salida.cpp";   // Archivo de salida en C++
        
        try {
            // Leer archivo de entrada
            CharStream input = CharStreams.fromFileName(inputFile);

            // Crear lexer usando el lexer generado de tu gramática
            AnalizadorLexer lexer = new AnalizadorLexer(input);
            
            // Crear stream de tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            // Crear parser usando el parser generado de tu gramática
            AnalizadorParser parser = new AnalizadorParser(tokens);

            // Verificar errores de sintaxis
            if (parser.getNumberOfSyntaxErrors() > 0) {
                System.err.println("Errores de sintaxis encontrados. No se puede generar código.");
                return;
            }

            // Obtener el árbol sintáctico comenzando desde la regla 'programa'
            ParseTree tree = parser.programa();

            // Crear el generador de código
            CodeGenerator generator = new CodeGenerator();
            
            // Generar código C++
            String cppCode = generator.visit(tree);

            // Escribir código C++ al archivo de salida
            try (PrintWriter writer = new PrintWriter(outputFile)) { 
                writer.println(cppCode); 
            }

            System.out.println("Código C++ generado exitosamente en: " + outputFile);
            System.out.println("Archivo de entrada: " + inputFile);
            
            
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + inputFile);
            System.err.println("Asegúrate de que el archivo existe y es accesible.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error durante la compilación:");
            e.printStackTrace();
        }
    }
}