antlr4 Analizador.g4 AnalizadorLexico.g4

antlr4 -listener -visitor Analizador.g4

javac AnalizadorLexico*.java Analizador*.java

# Ver arbol en terminal
grun Analizador programa -tree < prueba_poo.xl

# Ver arbol en gui
grun Analizador programa -gui < prueba_poo.xl

# Ver Tokens
grun Analizador programa -tokens < prueba_poo.xl