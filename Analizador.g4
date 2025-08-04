grammar Analizador;

options 
{
    tokenVocab = AnalizadorLexico;
}

// Punto de entrada del programa
programa
    : clases 'Iniciar' declaracion_constantes sentencias 'Finalizar'
    ;

// Colección de clases
clases
    : clase*
    ;

// Definición de clase
clase
    : 'Clase' IDENTIFICADOR cuerpo_clase 'Finalizar'
    ;

// Contenido de una clase
cuerpo_clase
    : '{' miembros_clase '}'
    ;

// Miembros dentro de la clase
miembros_clase
    : miembro_clase+
    ;

// Elemento individual de la clase
miembro_clase
    : declaracion
    | metodo
    ;

// Sección de constantes globales
declaracion_constantes
    : constante*
    ;

// Definición de constante
constante
    : 'Constante' tipo IDCONSTANTE ':' expresion ';'
    ;

// Declaración de variables
declaracion
    : 'Variable' lista_variables ':' tipo ';'
    | 'Variable' lista_variables ':' tipo '[' NUMERO ']' ';'
    | 'Variable' visibilidad lista_variables ':' tipo ';'
    | 'Variable' lista_variables ':' IDENTIFICADOR ';'
    | 'Arreglo' IDENTIFICADOR ':' tipo '[' NUMERO ']' ';'
    ;

// Definición de variable individual
variable
    : IDENTIFICADOR
    | IDENTIFICADOR '=' expresion
    | IDENTIFICADOR '[' expresion ']'
    | IDENTIFICADOR '[' expresion ']' '[' expresion ']'
    ;

// Lista de múltiples variables
lista_variables
    : variable
    | lista_variables ',' variable
    ;

// Modificadores de acceso
visibilidad
    : 'Publico'
    | 'Privado'
    ;

// Tipos de datos primitivos
tipo
    : 'numero'
    | 'texto'
    | 'logico'
    ;

// Tipos de retorno para funciones
tipo_retorno
    : tipo
    | 'vacio'
    ;

// Bloque de sentencias
sentencias
    : sentencia+
    ;

// Sentencia individual
sentencia
    : 'Mostrar' expresion ';'
    | 'Leer' lista_variables ';'
    | declaracion
    | IDENTIFICADOR '=' expresion ';'
    | IDENTIFICADOR '[' expresion ']' '=' expresion ';'
    | sentencia_si
    | sentencia_selector
    | sentencia_mientras
    | sentencia_para
    | 'Retornar' expresion ';'
    | incremento ';'
    | funcion
    | asignacion_propiedad
    ;

// Condicional if/if-else
sentencia_si
    : 'Si' condicion 'Entonces' sentencias 'Finalizar'
    | 'Si' condicion 'Entonces' sentencias 'Sino' sentencias 'Finalizar'
    ;

// Estructura switch/case
sentencia_selector
    : 'Selector' expresion '{' casos '}' 'Finalizar'
    ;

// Bucle while
sentencia_mientras
    : 'Mientras' condicion 'Hacer' sentencias 'Finalizar'
    | 'Mientras' condicion sentencias 'Finalizar'
    ;

// Bucle for
sentencia_para
    : 'Para' IDENTIFICADOR '=' NUMERO ';' condicion ';' incremento sentencias 'Finalizar'
    ;

// Asignación a propiedad del objeto actual
asignacion_propiedad
    : 'Este' '.' IDENTIFICADOR '=' expresion ';'
    ;

// Instanciación de objetos
creacion_objeto
    : 'Nuevo' IDENTIFICADOR '(' parametros ')'
    ;

// Lista de argumentos en llamadas
parametros
    :
    | parametros_lista
    ;

// Definición de parámetros formales
definicion_parametros
    :
    | lista_definicion_parametros
    ;

// Lista de parámetros con tipos
lista_definicion_parametros
    : tipo IDENTIFICADOR
    | IDENTIFICADOR ':' tipo
    | lista_definicion_parametros ',' tipo IDENTIFICADOR
    | lista_definicion_parametros ',' IDENTIFICADOR ':' tipo
    | IDENTIFICADOR ':' tipo '[' NUMERO ']'
    ;

// Argumentos actuales
parametros_lista
    : expresion
    | parametros_lista ',' expresion
    ;

// Casos del switch
casos
    : 'Caso' expresion ':' sentencias 'Detener' ';'
    | casos 'Caso' expresion ':' sentencias 'Detener' ';'
    | casos 'Otro' ':' sentencias 'Detener' ';'
    ;

// Expresión general
expresion
    : NUMERO
    | 'texto'
    | 'Verdadero'
    | 'Falso'
    | CADENA
    | IDENTIFICADOR
    | 'no' expresion
    | expresion ('+' | '-' | '*' | '/' | '%') expresion
    | '(' expresion ')'
    | IDENTIFICADOR '[' expresion ']'
    | IDENTIFICADOR '[' expresion ']' '[' expresion ']'
    | llamado_funcion
    | creacion_objeto
    | expresion '.' IDENTIFICADOR
    | expresion '.' llamado_funcion
    | 'Este' '.' llamado_funcion
    | 'Este' '.' IDENTIFICADOR
    ;

// Operadores de incremento/decremento
incremento
    : IDENTIFICADOR ('+=' | '-=' | '*=' | '/=') NUMERO
    | IDENTIFICADOR ('++'| '--')
    ;

// Expresiones condicionales
condicion
    : expresion ('==' | '!=' | '>' | '<' | '>=' | '<=') expresion
    | condicion ('y' | 'o') condicion
    | expresion
    ;

// Invocación de función
llamado_funcion
    : IDENTIFICADOR '(' parametros ')'
    ;

// Definición de función
funcion
    : 'Funcion' IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

// Definición de método de clase
metodo
    : 'Metodo' visibilidad IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

NUMERO: [0-9]+ ('.' [0-9]+)?;
CADENA: '"' (~[\\"] | '\\' .)* '"';
IDCONSTANTE: [A-Z_][A-Z0-9_]*;
IDENTIFICADOR: [a-zA-Z_][a-zA-Z0-9_]*;

COMENTARIO_LINEA: '~' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '~/' .*? '/~' -> skip;
WS: [ \t\r\n]+ -> skip;