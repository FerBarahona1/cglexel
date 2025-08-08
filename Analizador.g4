grammar Analizador;
// options 
// {
//     tokenVocab = AnalizadorLexico;
// }

// Reglas del parser
programa
    // nuevo
    : (clase | funcion)* 'Iniciar' declaracion_constantes sentencias 'Finalizar'
    ;

clase
    : 'Clase' IDENTIFICADOR cuerpo_clase 'Finalizar'
    ;

cuerpo_clase
    : '{' miembros_clase '}'
    ;

miembros_clase
    : miembro_clase+
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
    | 'Variable' lista_variables ':' tipo '[' expresion ']' ';'
    | 'Variable' visibilidad lista_variables ':' tipo ';'
    | 'Variable' lista_variables ':' IDENTIFICADOR ';'
    | 'Arreglo' IDENTIFICADOR ':' tipo '[' expresion ']' ';'
    ;

instancia_clase: IDENTIFICADOR '=' 'Nuevo' IDENTIFICADOR '(' ')' ';';

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
    | 'vacio'
    ;

sentencias
    : sentencia+
    ;

sentencia
    : 'Mostrar' expresion ';'
    | 'Leer' lista_variables ';'
    | 'Si' expresion 'Entonces' sentencias 'Finalizar'
    | 'Si' expresion 'Entonces' sentencias 'Sino' sentencias 'Finalizar'
    | 'Selector' expresion '{' casos '}' 'Finalizar'
    | 'Mientras' expresion 'Hacer' sentencias 'Finalizar'
    | 'Mientras' expresion sentencias 'Finalizar'
    | 'Para' IDENTIFICADOR '=' expresion ';' expresion ';' incremento sentencias 'Finalizar'
    | 'Este' '.' IDENTIFICADOR '=' expresion ';'
    | 'Este' '.' IDENTIFICADOR ('+=' | '-=' | '*=' | '/=') expresion ';'  // NUEVO
    | 'Retornar' expresion ';'
    | declaracion
    | incremento ';'
    | instancia_clase
    | expresion ';'
    | IDENTIFICADOR '=' expresion ';'
    | IDENTIFICADOR '[' expresion ']' '=' expresion ';'
    | IDENTIFICADOR '.' llamado_funcion ';'
    | asignacion_propiedad // NUEVO
    ;

// NUEVO
asignacion_propiedad: IDENTIFICADOR '.' IDENTIFICADOR '=' expresion ';';


llamado_funcion
    : IDENTIFICADOR '(' (expresion (',' expresion)*)? ')'
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
    | IDENTIFICADOR ':' tipo '[' expresion ']'
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
    | IDCONSTANTE
    | IDENTIFICADOR
    | 'no' expresion
    | expresion ('+' | '-' | '*' | '/' | '%') expresion
    | expresion ('==' | '!=' | '>' | '<' | '>=' | '<=') expresion
    | expresion ('y' | 'o') expresion
    | '(' expresion ')'
    | IDENTIFICADOR '[' expresion ']'
    | IDENTIFICADOR '[' expresion ']' '[' expresion ']'
    | llamado_funcion
    | IDENTIFICADOR '.' IDENTIFICADOR
    | IDENTIFICADOR '.' llamado_funcion
    | 'Este' '.' llamado_funcion
    | 'Este' '.' IDENTIFICADOR
    ;

incremento
    : IDENTIFICADOR ('+=' | '-=' | '*=' | '/=') expresion
    | IDENTIFICADOR ('++'| '--')
    ;

funcion
    : 'Funcion' IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

metodo
    : 'Metodo' visibilidad IDENTIFICADOR '(' definicion_parametros ')' ':' tipo_retorno sentencias 'Finalizar'
    ;

// Tokens del lexer
IDCONSTANTE: [A-Z_][A-Z0-9_]*;
NUMERO: [0-9]+ ('.' [0-9]+)?;
CADENA: '"' (~[\\"] | '\\' .)* '"';
IDENTIFICADOR: [a-zA-Z_][a-zA-Z0-9_]*;

// Comentarios
COMENTARIO_LINEA: '~' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '~/' .*? '/~' -> skip;

// Espacios en blanco
WS: [ \t\r\n]+ -> skip;