import java.util.*;

public class CodeGenerator extends AnalizadorBaseVisitor<String> {
    private final List<String> code = new ArrayList<>();
    private final Map<String, String> variableTypes = new HashMap<>();
    private final Map<String, String> constants = new HashMap<>();
    private final Set<String> classes = new HashSet<>();
    private int indentLevel = 0;
    private String currentVisibility = "public"; // Por defecto
    // Agregar estas variables como campos de la clase
    private List<String> publicMembers = new ArrayList<>();
    private List<String> privateMembers = new ArrayList<>();
    private boolean insideClass = false;
    
    private void addCode(String line) {
        String indent = "    ".repeat(indentLevel);
        code.add(indent + line);
    }
    
    @Override
    public String visitPrograma(AnalizadorParser.ProgramaContext ctx) {
        // Headers de C++
        code.add("#include <iostream>");
        code.add("#include <string>");
        code.add("#include <vector>");
        code.add("using namespace std;");
        code.add("");

        // Visitar funciones globales después de las clases
        for (AnalizadorParser.FuncionContext funcionCtx : ctx.funcion()) {
            visit(funcionCtx);
        }
        
        // Visitar clases
        for (AnalizadorParser.ClaseContext claseCtx : ctx.clase()) {
            visit(claseCtx);
        }
        
        // Declarar constantes globales
        if (ctx.declaracion_constantes() != null) {
            visit(ctx.declaracion_constantes());
        }
        
        // Función main
        code.add("int main() {");
        indentLevel++;
        
        // Visitar sentencias del programa principal
        if (ctx.sentencias() != null) {
            visit(ctx.sentencias());
        }
        
        addCode("return 0;");
        indentLevel--;
        code.add("}");
        
        return String.join("\n", code);
    }
    
    @Override
    public String visitClase(AnalizadorParser.ClaseContext ctx) {
        String className = ctx.IDENTIFICADOR().getText();
        classes.add(className);
        
        // Limpiar listas para esta clase
        publicMembers.clear();
        privateMembers.clear();
        
        insideClass = true;

        code.add("class " + className + " {");
        indentLevel++;
        
        // Iniciar en modo público por defecto
        addCode("public:");
        indentLevel++;
        
        // Constructor por defecto
        addCode(className + "() {}");
        code.add("");
        
        // Visitar miembros de la clase para recolectarlos
        if (ctx.cuerpo_clase() != null) {
            visit(ctx.cuerpo_clase());
        }
        
        // Agregar miembros públicos
        for (String member : publicMembers) {
            code.add(getIndent() + member);
        }
        
        // Si hay miembros privados, cambiar visibilidad y agregarlos
        if (!privateMembers.isEmpty()) {
            indentLevel--;
            addCode("private:");
            indentLevel++;
            
            for (String member : privateMembers) {
                code.add(getIndent() + member);
            }
        }
        
        indentLevel--;
        code.add("};");
        code.add("");
        
        insideClass = false;

        return null;
    }
    
    @Override
    public String visitCuerpo_clase(AnalizadorParser.Cuerpo_claseContext ctx) {
        if (ctx.miembros_clase() != null) {
            visit(ctx.miembros_clase());
        }
        return null;
    }
    
    @Override
    public String visitMiembros_clase(AnalizadorParser.Miembros_claseContext ctx) {
        for (AnalizadorParser.Miembro_claseContext miembro : ctx.miembro_clase()) {
            visit(miembro);
        }
        return null;
    }
    
    @Override
    public String visitMiembro_clase(AnalizadorParser.Miembro_claseContext ctx) {
        if (ctx.declaracion() != null) {
            return visit(ctx.declaracion());
        } else if (ctx.metodo() != null) {
            return visit(ctx.metodo());
        }
        return null;
    }
    
    @Override
    public String visitDeclaracion_constantes(AnalizadorParser.Declaracion_constantesContext ctx) {
        if (ctx.constante() != null) {
            for (AnalizadorParser.ConstanteContext constante : ctx.constante()) {
                visit(constante);
            }
        }
        return null;
    }
    
    @Override
    public String visitConstante(AnalizadorParser.ConstanteContext ctx) {
        String tipo = visit(ctx.tipo());
        String nombre = ctx.IDCONSTANTE().getText();
        String valor = visit(ctx.expresion());
        
        constants.put(nombre, valor);
        addCode("const " + tipo + " " + nombre + " = " + valor + ";");
        
        return null;
    }
    
    @Override
    public String visitDeclaracion(AnalizadorParser.DeclaracionContext ctx) {
        String text = ctx.getText();
        String tipo = null;
        String visibility = "public"; // Por defecto público
        
        // Verificar visibilidad
        if (ctx.visibilidad() != null) {
            visibility = visit(ctx.visibilidad());
        }
        
        // List<String> membersToAdd = visibility.equals("public") ? publicMembers : privateMembers;
        
        // 'Arreglo' IDENTIFICADOR ':' tipo '[' expresion ']' ';'
        if (text.startsWith("Arreglo")) {
            tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            // String size = ctx.NUMERO().getText();
            String size = visit(ctx.expresion());
            variableTypes.put(nombre, tipo);
            // membersToAdd.add(tipo + " " + nombre + "[" + size + "];");
            
            String declaration = tipo + " " + nombre + "[(int)" + size + "];";
        
            if (insideClass) {
                // Dentro de una clase, agregar a las listas de miembros
                List<String> membersToAdd = visibility.equals("public") ? publicMembers : privateMembers;
                membersToAdd.add(declaration);
            } else {
                // Fuera de clase, agregar directamente al código
                addCode(declaration);
            }
        // 'Variable' lista_variables ':' tipo ';'
        // 'Variable' lista_variables ':' tipo '[' NUMERO ']' ';'
        // 'Variable' visibilidad lista_variables ':' tipo ';'
        // 'Variable' lista_variables ':' IDENTIFICADOR ';'
        } else {
            // Determinar el tipo
            if (ctx.tipo() != null) {
                tipo = visit(ctx.tipo());
            } else if (ctx.IDENTIFICADOR() != null) {
                tipo = ctx.IDENTIFICADOR().getText(); // Tipo de clase personalizada
            }
            
            // Procesar lista de variables
            if (ctx.lista_variables() != null) {
                List<String> variables = extractVariableNames(ctx.lista_variables());
                
                for (String var : variables) {
                    variableTypes.put(var, tipo);
                    
                    String declaration;
                    if (ctx.expresion() != null) {
                        // Es un arreglo: Variable lista_variables ':' tipo '[' expresion ']' ';'
                        declaration = tipo + " " + var + "[(int)" + visit(ctx.expresion()) + "];";
                    } else {
                        declaration = tipo + " " + var + ";";
                    }

                    if (insideClass) {
                        // Dentro de una clase, agregar a las listas de miembros
                        List<String> membersToAdd = visibility.equals("public") ? publicMembers : privateMembers;
                        membersToAdd.add(declaration);
                    } else {
                        // Fuera de clase, agregar directamente al código
                        addCode(declaration);
                    }
                }
            }
        }
        
        return null;
    }
    
    private List<String> extractVariableNames(AnalizadorParser.Lista_variablesContext ctx) {
        List<String> names = new ArrayList<>();
        
        if (ctx.variable() != null) {
            String varName = extractVariableName(ctx.variable());
            if (varName != null) {
                names.add(varName);
            }
        }
        
        // Si hay más variables en la lista recursiva
        if (ctx.lista_variables() != null) {
            names.addAll(extractVariableNames(ctx.lista_variables()));
        }
        
        return names;
    }
    
    private String extractVariableName(AnalizadorParser.VariableContext ctx) {
        // variable: IDENTIFICADOR | IDENTIFICADOR '=' expresion | IDENTIFICADOR '[' expresion ']' | IDENTIFICADOR '[' expresion ']' '[' expresion ']'
        if (ctx.IDENTIFICADOR() != null) {
            return ctx.IDENTIFICADOR().getText();
        }
        return null;
    }
    
    @Override
    public String visitVisibilidad(AnalizadorParser.VisibilidadContext ctx) {
        if (ctx.getText().equalsIgnoreCase("Publico")) return "public";
        else if (ctx.getText().equalsIgnoreCase("Privado")) return "private";
        return "--unknown--"; // Valor por defecto
    }

    
    @Override
    public String visitTipo(AnalizadorParser.TipoContext ctx) {
        switch (ctx.getText()) {
            case "numero":
                return "double";
            case "texto":
                return "string";
            case "logico":
                return "bool";
            default:
                return "int";
        }
    }
    
    @Override
    public String visitTipo_retorno(AnalizadorParser.Tipo_retornoContext ctx) {
        if (ctx.getText().equals("vacio")) {
            return "void";
        } else if (ctx.tipo() != null) {
            return visit(ctx.tipo());
        }
        return "void";
    }
    
    @Override
    public String visitSentencias(AnalizadorParser.SentenciasContext ctx) {
        for (AnalizadorParser.SentenciaContext sentencia : ctx.sentencia()) {
            visit(sentencia);
        }
        return null;
    }
    
    @Override
    public String visitSentencia(AnalizadorParser.SentenciaContext ctx) {
        String text = ctx.getText();

        // 'Mostrar' expresion ';'
        if (text.startsWith("Mostrar")) {
            String expr = visit(ctx.expresion(0));
            addCode("cout << " + expr + " << endl;");

        // 'Para' IDENTIFICADOR '=' expresion ';' condicion ';' incremento sentencias 'Finalizar'
        } else if (text.startsWith("Para")) {
            String var = ctx.IDENTIFICADOR().getText();
            String init = visit(ctx.expresion(0));
            String expression = visit(ctx.expresion(1));
            String increment = visit(ctx.incremento());
            
            addCode("for (int " + var + " = " + init + "; " + expression + "; " + increment + ") {");
            indentLevel++;
            visit(ctx.sentencias(0));
            indentLevel--;
            addCode("}");
        
        // 'Si' expresion 'Entonces' sentencias 'Finalizar'
        // 'Si' expresion 'Entonces' sentencias 'Sino' sentencias 'Finalizar'
        } else if (text.startsWith("Si")) {
            String expression = visit(ctx.expresion(0));
            
            addCode("if (" + expression + ") {");
            indentLevel++;
            
            visit(ctx.sentencias(0));
            
            indentLevel--;
            if (ctx.sentencias().size() > 1) {
                addCode("} else {");
                indentLevel++;
                visit(ctx.sentencias(1));
                indentLevel--;
            }
            addCode("}");

        // 'Este' '.' IDENTIFICADOR '=' expresion ';'
        } else if (text.startsWith("Este") && ctx.IDENTIFICADOR() != null && ctx.expresion().size() == 1 && text.contains("=") && !text.contains("+=") && !text.contains("-=") && !text.contains("*=") && !text.contains("/=")) {
            String member = ctx.IDENTIFICADOR().getText();
            String value = visit(ctx.expresion(0));
            addCode("this->" + member + " = " + value + ";");
        
        // 'Este' '.' IDENTIFICADOR ('+=' | '-=' | '*=' | '/=') expresion ';' - NEW CASE
        } else if (text.startsWith("Este") && ctx.IDENTIFICADOR() != null && ctx.expresion().size() == 1 && (text.contains("+=") || text.contains("-=") || text.contains("*=") || text.contains("/="))) {
            String member = ctx.IDENTIFICADOR().getText();
            String value = visit(ctx.expresion(0));
            String operator = "";
            
            if (text.contains("+=")) operator = " += ";
            else if (text.contains("-=")) operator = " -= ";
            else if (text.contains("*=")) operator = " *= ";
            else if (text.contains("/=")) operator = " /= ";
            
            addCode("this->" + member + operator + value + ";");
        
        // 'Selector' expresion '{' casos '}' 'Finalizar'
        } else if (text.startsWith("Selector")) {
            String expr = visit(ctx.expresion(0));
            addCode("switch ((int)(" + expr + ")) {");
            visit(ctx.casos());
            addCode("}"); // cierre del switch

        // 'Leer' lista_variables ';'
        } else if (text.startsWith("Leer")) {
            if (ctx.lista_variables() != null) {
                List<String> vars = extractVariableNames(ctx.lista_variables());
                for (String var : vars) {
                    addCode("cin >> " + var + ";");
                }
            }
            
        // declaracion
        } else if (ctx.declaracion() != null) {
            visit(ctx.declaracion());

        // IDENTIFICADOR '.' llamado_funcion (llamada a método como sentencia)
        } else if (ctx.llamado_funcion() != null && ctx.IDENTIFICADOR() != null && 
                text.contains(".") && ctx.expresion().isEmpty()) {
            String obj = ctx.IDENTIFICADOR().getText();
            String method = visit(ctx.llamado_funcion());
            addCode(obj + "." + method + ";");

        // IDENTIFICADOR '[' expresion ']' '=' expresion ';' (asignación a array)
        } else if (ctx.IDENTIFICADOR() != null && ctx.expresion().size() == 2 && 
                text.matches(".*\\[.*\\]\\s*=.*")) {
            String var = ctx.IDENTIFICADOR().getText();
            String index = visit(ctx.expresion(0));
            String value = visit(ctx.expresion(1));
            addCode(var + "[(int)" + index + "] = " + value + ";");
            
        // IDENTIFICADOR '=' expresion ';' (asignación simple - DEBE IR DESPUÉS de array)
        } else if (ctx.IDENTIFICADOR() != null && ctx.expresion().size() == 1 && 
                text.matches("\\w+\\s*=.*") && !text.startsWith("Este")) {
            String var = ctx.IDENTIFICADOR().getText();
            String value = visit(ctx.expresion(0));
            addCode(var + " = " + value + ";");
            
        // 'Mientras' condicion 'Hacer' sentencias 'Finalizar'
        // 'Mientras' condicion sentencias 'Finalizar'  
        } else if (text.startsWith("Mientras")) {
            String expression = visit(ctx.expresion(0));
            
            addCode("while (" + expression + ") {");
            indentLevel++;
            visit(ctx.sentencias(0));
            indentLevel--;
            addCode("}");
            
        // 'Retornar' expresion ';'
        } else if (text.startsWith("Retornar")) {
            String expr = visit(ctx.expresion(0));
            addCode("return " + expr + ";");
            
        // incremento ';'
        } else if (ctx.incremento() != null) {
            String inc = visit(ctx.incremento());
            addCode(inc + ";");
        
        // Sentencia de instancia de Clase
        } else if (ctx.instancia_clase() != null) {
            visit(ctx.instancia_clase());
            
        // expresion ';' (expresión como sentencia - para casos como llamadas a función que devuelven valor)
        } else if (ctx.expresion().size() == 1 && ctx.IDENTIFICADOR() == null && 
                ctx.llamado_funcion() == null) {
            String expr = visit(ctx.expresion(0));
            addCode(expr + ";");

        // asignacion_propiedad ~(NUEVO)~
        } else if (ctx.asignacion_propiedad() != null) {
            addCode(visit(ctx.asignacion_propiedad()));
        
        } else {
            // Debug unhandled cases
            System.err.println("Unhandled sentencia: " + text);
        }

        return null;
    }

    @Override
    public String visitExpresion(AnalizadorParser.ExpresionContext ctx) {
        String text = ctx.getText();
        
        // DEBUG: Imprimir información del contexto
        // for (int i = 0; i < ctx.getChildCount(); i++) {
        // }
        
        // NUMERO
        if (ctx.NUMERO() != null) {
            return ctx.NUMERO().getText();
        }
        
        // 'texto'
        if (text.equals("texto")) {
            return "\"\"";
        }
        
        // 'Verdadero'
        if (text.equals("Verdadero")) {
            return "true";
        }
        
        // 'Falso' 
        if (text.equals("Falso")) {
            return "false";
        }
        
        // CADENA
        if (ctx.CADENA() != null) {
            return ctx.CADENA().getText();
        }
        
        // '(' expresion ')'
        if (ctx.getChildCount() == 3 && 
            ctx.getChild(0).getText().equals("(") && 
            ctx.getChild(2).getText().equals(")")) {
            return "(" + visit(ctx.expresion(0)) + ")";
        }
        
        // IDENTIFICADOR '[' expresion ']' '[' expresion ']' (arreglo 2D)
        if (ctx.getChildCount() == 6 && 
            ctx.getChild(1).getText().equals("[") && 
            ctx.getChild(3).getText().equals("]") &&
            ctx.getChild(4).getText().equals("[") && 
            ctx.getChild(5).getText().equals("]")) {
            String var = ctx.getChild(0).getText();
            String index1 = visit(ctx.expresion(0));
            String index2 = visit(ctx.expresion(1));
            return var + "[(int)" + index1 + "][" + index2 + "]";
        }
        
        // IDENTIFICADOR '[' expresion ']' (arreglo 1D)
        if (ctx.getChildCount() == 4 && 
            ctx.getChild(1).getText().equals("[") && 
            ctx.getChild(3).getText().equals("]")) {
            String var = ctx.getChild(0).getText();
            String index = visit(ctx.expresion(0));
            String result = var + "[(int)" + index + "]";
            return result;
        }
        
        // 'no' expresion
        if (ctx.getChildCount() == 2 && ctx.getChild(0).getText().equals("no")) {
            String expr = visit(ctx.expresion(0));
            return "!" + expr;
        }

        // expresion ('==' | '!=' | '>' | '<' | '>=' | '<=') expresion - OPERADORES RELACIONALES
        if (ctx.expresion().size() == 2 && ctx.getChildCount() == 3) {
            String op = ctx.getChild(1).getText();
            if (op.equals("==") || op.equals("!=") || op.equals(">") || 
                op.equals("<") || op.equals(">=") || op.equals("<=")) {
                String left = visit(ctx.expresion(0));
                String right = visit(ctx.expresion(1));
                return "(" + left + " " + op + " " + right + ")";
            }
        }
        
        // expresion ('y' | 'o') expresion - OPERADORES LÓGICOS
        if (ctx.expresion().size() == 2 && ctx.getChildCount() == 3) {
            String op = ctx.getChild(1).getText();
            if (op.equals("y") || op.equals("o")) {
                String left = visit(ctx.expresion(0));
                String right = visit(ctx.expresion(1));
                String cppOp = op.equals("y") ? "&&" : "||";
                return "(" + left + " " + cppOp + " " + right + ")";
            }
        }
        
        // expresion ('+' | '-' | '*' | '/' | '%') expresion
        if (ctx.getChildCount() == 3 && ctx.expresion().size() == 2) {
            String left = visit(ctx.expresion(0));
            String right = visit(ctx.expresion(1));
            String op = ctx.getChild(1).getText();
            
            
            // Si es concatenación de strings, no usar paréntesis extra
            if (op.equals("+") && (isStringExpression(ctx.expresion(0)) || isStringExpression(ctx.expresion(1)))) {
                String result = left + " " + op + " " + right;
                return result;
            } else {
                String result = "(" + left + " " + op + " " + right + ")";
                return result;
            }
        }
        
        // 'Este' '.' llamado_funcion (método de this)
        if (ctx.getChildCount() == 3 && 
            ctx.getChild(0).getText().equals("Este") && 
            ctx.getChild(1).getText().equals(".") &&
            ctx.llamado_funcion() != null) {
            String method = visit(ctx.llamado_funcion());
            return "this->" + method;
        }
        
        // 'Este' '.' IDENTIFICADOR (miembro de this)
        if (ctx.getChildCount() == 3 && 
            ctx.getChild(0).getText().equals("Este") && 
            ctx.getChild(1).getText().equals(".")) {
            return "this->" + ctx.getChild(2).getText();
        }


        // IDENTIFICADOR '.' IDENTIFICADOR (acceso a miembro)
        if (ctx.getChildCount() == 3 && 
            ctx.getChild(1).getText().equals(".") &&
            ctx.llamado_funcion() == null) {
            String obj = ctx.getChild(0).getText();
            String member = ctx.getChild(2).getText();
            return obj + "." + member;
        }
        
        // IDENTIFICADOR '.' llamado_funcion (método de objeto)
        if (ctx.getChildCount() == 3 && 
            ctx.getChild(1).getText().equals(".") &&
            ctx.llamado_funcion() != null &&
            !ctx.getChild(0).getText().equals("Este")) {
            String obj = ctx.getChild(0).getText();
            String method = visit(ctx.llamado_funcion());
            return obj + "." + method;
        }
        
        
        // llamado_funcion (función independiente)
        if (ctx.getChildCount() == 1 && ctx.llamado_funcion() != null) {
            return visit(ctx.llamado_funcion());
        }
        
        // IDCONSTANTE (variable simple)
        if (ctx.getChildCount() == 1 && ctx.IDCONSTANTE() != null) {
            return ctx.IDCONSTANTE().getText();
        }

        // IDENTIFICADOR (variable simple)
        if (ctx.getChildCount() == 1 && ctx.IDENTIFICADOR() != null && !ctx.IDENTIFICADOR().isEmpty()) {
            return ctx.IDENTIFICADOR(0).getText();
        }
        
        return text;
    }
    
    // Método auxiliar para extraer el operador binario
    private String extractBinaryOperator(AnalizadorParser.ExpresionContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            String childText = ctx.getChild(i).getText();
            if (childText.equals("+") || childText.equals("-") || childText.equals("*") || 
                childText.equals("/") || childText.equals("%")) {
                return childText;
            }
        }
        return "+"; // Default fallback
    }
    
    
    @Override
    public String visitIncremento(AnalizadorParser.IncrementoContext ctx) {
        String var = ctx.IDENTIFICADOR().getText();
        String text = ctx.getText();
        
        if (text.contains("++")) return var + "++";
        if (text.contains("--")) return var + "--";
        if (text.contains("+=")) return var + " += " + visit(ctx.expresion());
        if (text.contains("-=")) return var + " -= " + visit(ctx.expresion());
        if (text.contains("*=")) return var + " *= " + visit(ctx.expresion());
        if (text.contains("/=")) return var + " /= " + visit(ctx.expresion());
        
        return var + "++";
    }
    
    @Override
    public String visitLlamado_funcion(AnalizadorParser.Llamado_funcionContext ctx) {
        StringBuilder resultado = new StringBuilder();
        
        // Obtener el nombre de la función
        String nombreFuncion = ctx.IDENTIFICADOR().getText();
        resultado.append(nombreFuncion);
        
        // Agregar paréntesis de apertura
        resultado.append("(");
        
        // Procesar los parámetros (expresiones)
        if (ctx.expresion() != null && !ctx.expresion().isEmpty()) {
            // Visitar la primera expresión
            resultado.append(visit(ctx.expresion(0)));
            
            // Visitar las expresiones restantes, agregando comas
            for (int i = 1; i < ctx.expresion().size(); i++) {
                resultado.append(", ");
                resultado.append(visit(ctx.expresion(i)));
            }
        }
        
        // Agregar paréntesis de cierre y punto y coma
        resultado.append(")");
        
        return resultado.toString();
    }
    
    // @Override
    // public String visitParametros(AnalizadorParser.ParametrosContext ctx) {
    //     if (ctx.parametros_lista() != null) {
    //         return visit(ctx.parametros_lista());
    //     }
    //     return "";
    // }
    
    // @Override
    // public String visitParametros_lista(AnalizadorParser.Parametros_listaContext ctx) {
    //     List<String> params = new ArrayList<>();
        
    //     // Verificar si hay expresiones en la lista de parámetros
    //     if (ctx.expresion() != null) {
    //         params.add(visit(ctx.expresion()));
    //     }
        
    //     // Si hay más parámetros en la lista recursiva
    //     if (ctx.parametros_lista() != null) {
    //         String moreParams = visit(ctx.parametros_lista());
    //         if (!moreParams.isEmpty()) {
    //             params.add(moreParams);
    //         }
    //     }
        
    //     return String.join(", ", params);
    // }
    
    @Override
    public String visitFuncion(AnalizadorParser.FuncionContext ctx) {
        String returnType = visit(ctx.tipo_retorno());
        String funcName = ctx.IDENTIFICADOR().getText();
        String params = "";
        
        if (ctx.definicion_parametros() != null) {
            params = visit(ctx.definicion_parametros());
        }
        
        addCode(returnType + " " + funcName + "(" + params + ") {");
        indentLevel++;
        
        visit(ctx.sentencias());
        
        indentLevel--;
        addCode("}");
        
        return null;
    }

    // Método isStringExpression corregido
    private boolean isStringExpression(AnalizadorParser.ExpresionContext ctx) {
        if (ctx.CADENA() != null) {
            return true;
        }
        
        if (ctx.getText().equals("texto")) {
            return true;
        }
        
        // Verificar si es una variable de tipo string
        if (ctx.IDENTIFICADOR() != null && !ctx.IDENTIFICADOR().isEmpty()) {
            String varName = ctx.IDENTIFICADOR(0).getText();
            String type = variableTypes.get(varName);
            return "string".equals(type) || "texto".equals(type);
        }
        
        return false;
    }
    
    @Override
    public String visitDefinicion_parametros(AnalizadorParser.Definicion_parametrosContext ctx) {
        if (ctx.lista_definicion_parametros() != null) {
            return visit(ctx.lista_definicion_parametros());
        }
        return "";
    }
    
    @Override
    public String visitLista_definicion_parametros(AnalizadorParser.Lista_definicion_parametrosContext ctx) {
        List<String> params = new ArrayList<>();
        String text = ctx.getText();
        
        // tipo IDENTIFICADOR
        if (ctx.tipo() != null && ctx.IDENTIFICADOR() != null && !text.contains(":") && !text.contains(",")) {
            String tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            params.add(tipo + " " + nombre);
        }
        
        // IDENTIFICADOR ':' tipo
        else if (ctx.IDENTIFICADOR() != null && ctx.tipo() != null && text.contains(":") && !text.contains(",")) {
            String tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            params.add(tipo + " " + nombre);
        }
        
        // IDENTIFICADOR ':' tipo '[' expresion ']'
        else if (ctx.IDENTIFICADOR() != null && ctx.tipo() != null && ctx.expresion() != null) {
            String tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            String size = visit(ctx.expresion());
            params.add(tipo + " " + nombre + "[(int)" + size + "]");
        }
        
        // lista_definicion_parametros ',' tipo IDENTIFICADOR
        else if (ctx.lista_definicion_parametros() != null && ctx.tipo() != null && ctx.IDENTIFICADOR() != null && !text.contains(":")) {
            String prevParams = visit(ctx.lista_definicion_parametros());
            String tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            return prevParams + ", " + tipo + " " + nombre;
        }
        
        // lista_definicion_parametros ',' IDENTIFICADOR ':' tipo
        else if (ctx.lista_definicion_parametros() != null && ctx.IDENTIFICADOR() != null && ctx.tipo() != null && text.contains(":")) {
            String prevParams = visit(ctx.lista_definicion_parametros());
            String tipo = visit(ctx.tipo());
            String nombre = ctx.IDENTIFICADOR().getText();
            return prevParams + ", " + tipo + " " + nombre;
        }
        
        return String.join(", ", params);
    }
    
    @Override
    public String visitMetodo(AnalizadorParser.MetodoContext ctx) {
        String visibility = visit(ctx.visibilidad());  // "public" o "private"
        String returnType = visit(ctx.tipo_retorno()); // e.g. "string", "void"
        String methodName = ctx.IDENTIFICADOR().getText();
        String params = "";

        if (ctx.definicion_parametros() != null) {
            params = visit(ctx.definicion_parametros());  // e.g. "string nombre, int edad"
        }

        // Construir el método completo como string
        StringBuilder methodBuilder = new StringBuilder();
        methodBuilder.append(returnType).append(" ").append(methodName).append("(").append(params).append(") {");
        
        // Capturar el índice donde estamos para poder remover después
        int initialSize = code.size();
        
        if (ctx.sentencias() != null) {
            int originalIndent = indentLevel;
            indentLevel++; // Aumentar indentación para el cuerpo del método
            visit(ctx.sentencias());
            indentLevel = originalIndent; // Restaurar
        }
        
        // Extraer las líneas que se agregaron para el cuerpo del método
        for (int i = initialSize; i < code.size(); i++) {
            String line = code.get(i);
            if (!line.trim().isEmpty()) {
                methodBuilder.append("\n            ").append(line.trim()); // Agregar indentación extra para el cuerpo
            }
        }
        methodBuilder.append("\n        }\n");
        
        // Remover las líneas que se agregaron temporalmente
        while (code.size() > initialSize) {
            code.remove(code.size() - 1);
        }
        
        // Agregar a la lista correspondiente según visibilidad
        List<String> membersToAdd = visibility.equals("public") ? publicMembers : privateMembers;
        membersToAdd.add(methodBuilder.toString());
        
        return null;
    }

    // Método auxiliar para obtener la indentación actual
    private String getIndent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }

    @Override
    public String visitInstancia_clase(AnalizadorParser.Instancia_claseContext ctx) {
        //      Tipo de Dato                 Identificador            
        addCode(ctx.IDENTIFICADOR(1).getText() + " " + ctx.IDENTIFICADOR(0).getText() + ";\n");
        return null;
    }

    @Override
    public String visitCasos(AnalizadorParser.CasosContext ctx) {
        if (ctx.getChild(0).getText().equals("Caso")) {
            String val = visit(ctx.expresion());
            addCode("case " + val + ": {");
            visit(ctx.sentencias());
            addCode("break;\n}");
        } else if (ctx.getChild(1).getText().equals("Caso")) {
            visit(ctx.casos()); // casos anteriores
            String val = visit(ctx.expresion());
            addCode("case " + val + ": {");
            visit(ctx.sentencias());
            addCode("break;\n}");
        } else if (ctx.getChild(1).getText().equals("Otro")) {
            visit(ctx.casos()); // casos anteriores
            addCode("default: {");
            visit(ctx.sentencias());
            addCode("break;\n}");
        }
        return "";
    }

    @Override
    public String visitAsignacion_propiedad(AnalizadorParser.Asignacion_propiedadContext ctx) {
        String objeto = ctx.IDENTIFICADOR(0).getText();       // c
        String propiedad = ctx.IDENTIFICADOR(1).getText();    // id
        String valor = visit(ctx.expresion());                // i + 1000
        return objeto + "." + propiedad + " = " + valor + ";";
    }
}