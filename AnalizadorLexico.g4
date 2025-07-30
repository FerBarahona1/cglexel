lexer grammar AnalizadorLexico;

// Palabras reservadas
CLASE: 'Clase';
INICIAR: 'Iniciar';
FINALIZAR: 'Finalizar';
CONSTANTE: 'Constante';
VARIABLE: 'Variable';
ARREGLO: 'Arreglo';
PUBLICO: 'Publico';
PRIVADO: 'Privado';
NUMERO_TIPO: 'numero';
TEXTO_TIPO: 'texto';
LOGICO_TIPO: 'logico';
NULO_TIPO: 'nulo';
MOSTRAR: 'Mostrar';
LEER: 'Leer';
SI: 'Si';
ENTONCES: 'Entonces';
SINO: 'Sino';
SELECTOR: 'Selector';
CASO: 'Caso';
OTRO: 'Otro';
DETENER: 'Detener';
MIENTRAS: 'Mientras';
HACER: 'Hacer';
PARA: 'Para';
RETORNAR: 'Retornar';
FUNCION: 'Funcion';
METODO: 'Metodo';
NUEVO: 'Nuevo';
ESTE: 'Este';
VERDADERO: 'Verdadero';
FALSO: 'Falso';
NO: 'no';
Y: 'y';
O: 'o';

// Operadores
SUMA: '+';
RESTA: '-';
MULTIPLICACION: '*';
DIVISION: '/';
MODULO: '%';
ASIGNACION: '=';
INCREMENTO: '++';
DECREMENTO: '--';
SUMA_ASIGNACION: '+=';
RESTA_ASIGNACION: '-=';
MULTIPLICACION_ASIGNACION: '*=';
DIVISION_ASIGNACION: '/=';

// Operadores de comparación
IGUAL: '==';
DIFERENTE: '!=';
MAYOR: '>';
MENOR: '<';
MAYOR_IGUAL: '>=';
MENOR_IGUAL: '<=';

// Delimitadores
PUNTO_COMA: ';';
COMA: ',';
PUNTO: '.';
DOS_PUNTOS: ':';
PARENTESIS_IZQ: '(';
PARENTESIS_DER: ')';
LLAVE_IZQ: '{';
LLAVE_DER: '}';
CORCHETE_IZQ: '[';
CORCHETE_DER: ']';

// Tokens de identificadores y literales
NUMERO: [0-9]+ ('.' [0-9]+)?;
CADENA: '"' (~[\\"] | '\\' .)* '"';
IDCONSTANTE: [A-Z_][A-Z0-9_]*;
IDENTIFICADOR: [a-zA-Z_][a-zA-Z0-9_]*;

// Comentarios
COMENTARIO_LINEA: '~' ~[\r\n]* -> skip;
COMENTARIO_BLOQUE: '~/' .*? '/~' -> skip;

// Espacios en blanco
WS: [ \t\r\n]+ -> skip;