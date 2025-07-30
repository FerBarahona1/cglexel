grammar Analizador;

options 
{
    tokenVocab = AnalizadorLexico;
}

// Reglas del parser
programa
    : clases 'Iniciar' declaracion_constantes sentencias 'Finalizar'
    ;

clases
    : clase*
    ;

clase
    : 'Clase' IDENTIFICADOR cuerpo_clase 'Finalizar'
    ;

cuerpo_clase
    : '{' miembros_clase '}'
    ;

miembros_clase
    : miembro_clase*
    ;

miembro_clase
    : declaracion
    | metodo
    ;

declaracion_constantes
    : constante*
    ;

constante
    : 'Constante' tipo IDCONSTANTE ':' expresion ';'
    ;

declaracion
    : 'Variable' lista_variables ':' tipo ';'
    | 'Variable' lista_variables ':' tipo '[' NUMERO ']' ';'
    | 'Variable' visibilidad lista_variables ':' tipo ';'
    | 'Variable' lista_variables ':' IDENTIFICADOR ';'
    | 'Arreglo' IDENTIFICADOR ':' tipo '[' NUMERO ']' ';'
    ;

variable
    : IDENTIFICADOR
    | IDENTIFICADOR '=' expresion
    | IDENTIFICADOR '[' expresion ']'
    | IDENTIFICADOR '[' expresion ']' '[' expresion ']'
    ;

lista_variables
    : variable
    | lista_variables ',' variable
    ;

visibilidad
    : 'Publico'
    | 'Privado'
    ;

tipo
    : 'numero'
    | 'texto'
    | 'logico'
    ;

tipo_retorno
    : tipo
    | 'nulo'
    ;

sentencias
    : sentencia+
    ;

sentencia
    : 'Mostrar' expresion ';'
    | 'Leer' lista_variables ';'
    | declaracion
    | IDENTIFICADOR '=' expresion ';'
    | IDENTIFICADOR '[' expresion ']' '=' expresion ';'
    | 'Si' condicion 'Entonces' sentencias 'Finalizar'
    | 'Si' condicion 'Entonces' sentencias 'Sino' sentencias 'Finalizar'
    | 'Selector' expresion '{' casos '}' 'Finalizar'
    | 'Mientras' condicion 'Hacer' sentencias 'Finalizar'
    | 'Mientras' condicion sentencias 'Finalizar'
    | 'Para' IDENTIFICADOR '=' NUMERO ';' condicion ';' incremento sentencias 'Finalizar'
    | 'Retornar' expresion ';'
    | incremento ';'
    | funcion
    | 'Este' '.' IDENTIFICADOR '=' expresion ';'
    ;

parametros
    :
    | parametros_lista
    ;

definicion_parametros
    :
    | lista_definicion_parametros
    ;

lista_definicion_parametros
    : tipo IDENTIFICADOR
    | IDENTIFICADOR ':' tipo
    | lista_definicion_parametros ',' tipo IDENTIFICADOR
    | lista_definicion_parametros ',' IDENTIFICADOR ':' tipo
    | IDENTIFICADOR ':' tipo '[' NUMERO ']'
    ;

parametros_lista
    : expresion
    | parametros_lista ',' expresion
    ;

casos
    : 'Caso' expresion ':' sentencias 'Detener' ';'
    | casos 'Caso' expresion ':' sentencias 'Detener' ';'
    | casos 'Otro' ':' sentencias 'Detener' ';'
    ;

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
    | 'Nuevo' IDENTIFICADOR '(' parametros ')'
    | expresion '.' IDENTIFICADOR
    | expresion '.' llamado_funcion
    | 'Este' '.' llamado_funcion
    | 'Este' '.' IDENTIFICADOR
    ;

incremento
    : IDENTIFICADOR ('+=' | '-=' | '*=' | '/=') NUMERO
    | IDENTIFICADOR ('++'| '--')
    ;

condicion
    : expresion ('==' | '!=' | '>' | '<' | '>=' | '<=') expresion
    | condicion ('y' | 'o') condicion
    | expresion
    ;

llamado_funcion
    : IDENTIFICADOR '(' parametros ')'
    ;

funcion
    : 'Funcion' IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

metodo
    : 'Metodo' visibilidad IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

// Tokens del lexer
NUMERO: [0-9]+ ('.' [0-9]+)?;
CADENA: '"' (~[\\"] | '\\' .)* '"';
IDCONSTANTE: [A-Z_][A-Z0-9_]*;
IDENTIFICADOR: [a-zA-Z_][a-zA-Z0-9_]*;

// Comentarios
COMENTARIO_LINEA: '~' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '~/' .*? '/~' -> skip;

// Espacios en blanco
WS: [ \t\r\n]+ -> skip;