// Generated from Analizador.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AnalizadorParser}.
 */
public interface AnalizadorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(AnalizadorParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(AnalizadorParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#clase}.
	 * @param ctx the parse tree
	 */
	void enterClase(AnalizadorParser.ClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#clase}.
	 * @param ctx the parse tree
	 */
	void exitClase(AnalizadorParser.ClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#cuerpo_clase}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_clase(AnalizadorParser.Cuerpo_claseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#cuerpo_clase}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_clase(AnalizadorParser.Cuerpo_claseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#miembros_clase}.
	 * @param ctx the parse tree
	 */
	void enterMiembros_clase(AnalizadorParser.Miembros_claseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#miembros_clase}.
	 * @param ctx the parse tree
	 */
	void exitMiembros_clase(AnalizadorParser.Miembros_claseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void enterMiembro_clase(AnalizadorParser.Miembro_claseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void exitMiembro_clase(AnalizadorParser.Miembro_claseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#declaracion_constantes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_constantes(AnalizadorParser.Declaracion_constantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#declaracion_constantes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_constantes(AnalizadorParser.Declaracion_constantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#constante}.
	 * @param ctx the parse tree
	 */
	void enterConstante(AnalizadorParser.ConstanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#constante}.
	 * @param ctx the parse tree
	 */
	void exitConstante(AnalizadorParser.ConstanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(AnalizadorParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(AnalizadorParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#instancia_clase}.
	 * @param ctx the parse tree
	 */
	void enterInstancia_clase(AnalizadorParser.Instancia_claseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#instancia_clase}.
	 * @param ctx the parse tree
	 */
	void exitInstancia_clase(AnalizadorParser.Instancia_claseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(AnalizadorParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(AnalizadorParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#lista_variables}.
	 * @param ctx the parse tree
	 */
	void enterLista_variables(AnalizadorParser.Lista_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#lista_variables}.
	 * @param ctx the parse tree
	 */
	void exitLista_variables(AnalizadorParser.Lista_variablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#visibilidad}.
	 * @param ctx the parse tree
	 */
	void enterVisibilidad(AnalizadorParser.VisibilidadContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#visibilidad}.
	 * @param ctx the parse tree
	 */
	void exitVisibilidad(AnalizadorParser.VisibilidadContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AnalizadorParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AnalizadorParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#tipo_retorno}.
	 * @param ctx the parse tree
	 */
	void enterTipo_retorno(AnalizadorParser.Tipo_retornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#tipo_retorno}.
	 * @param ctx the parse tree
	 */
	void exitTipo_retorno(AnalizadorParser.Tipo_retornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#sentencias}.
	 * @param ctx the parse tree
	 */
	void enterSentencias(AnalizadorParser.SentenciasContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#sentencias}.
	 * @param ctx the parse tree
	 */
	void exitSentencias(AnalizadorParser.SentenciasContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void enterSentencia(AnalizadorParser.SentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#sentencia}.
	 * @param ctx the parse tree
	 */
	void exitSentencia(AnalizadorParser.SentenciaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#asignacion_propiedad}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion_propiedad(AnalizadorParser.Asignacion_propiedadContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#asignacion_propiedad}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion_propiedad(AnalizadorParser.Asignacion_propiedadContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#llamado_funcion}.
	 * @param ctx the parse tree
	 */
	void enterLlamado_funcion(AnalizadorParser.Llamado_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#llamado_funcion}.
	 * @param ctx the parse tree
	 */
	void exitLlamado_funcion(AnalizadorParser.Llamado_funcionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#definicion_parametros}.
	 * @param ctx the parse tree
	 */
	void enterDefinicion_parametros(AnalizadorParser.Definicion_parametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#definicion_parametros}.
	 * @param ctx the parse tree
	 */
	void exitDefinicion_parametros(AnalizadorParser.Definicion_parametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#lista_definicion_parametros}.
	 * @param ctx the parse tree
	 */
	void enterLista_definicion_parametros(AnalizadorParser.Lista_definicion_parametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#lista_definicion_parametros}.
	 * @param ctx the parse tree
	 */
	void exitLista_definicion_parametros(AnalizadorParser.Lista_definicion_parametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#casos}.
	 * @param ctx the parse tree
	 */
	void enterCasos(AnalizadorParser.CasosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#casos}.
	 * @param ctx the parse tree
	 */
	void exitCasos(AnalizadorParser.CasosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(AnalizadorParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(AnalizadorParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#incremento}.
	 * @param ctx the parse tree
	 */
	void enterIncremento(AnalizadorParser.IncrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#incremento}.
	 * @param ctx the parse tree
	 */
	void exitIncremento(AnalizadorParser.IncrementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#funcion}.
	 * @param ctx the parse tree
	 */
	void enterFuncion(AnalizadorParser.FuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#funcion}.
	 * @param ctx the parse tree
	 */
	void exitFuncion(AnalizadorParser.FuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AnalizadorParser#metodo}.
	 * @param ctx the parse tree
	 */
	void enterMetodo(AnalizadorParser.MetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AnalizadorParser#metodo}.
	 * @param ctx the parse tree
	 */
	void exitMetodo(AnalizadorParser.MetodoContext ctx);
}