// Generated from Analizador.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AnalizadorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnalizadorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AnalizadorParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClase(AnalizadorParser.ClaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#cuerpo_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_clase(AnalizadorParser.Cuerpo_claseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#miembros_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembros_clase(AnalizadorParser.Miembros_claseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#miembro_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembro_clase(AnalizadorParser.Miembro_claseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#declaracion_constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion_constantes(AnalizadorParser.Declaracion_constantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstante(AnalizadorParser.ConstanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#declaracion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracion(AnalizadorParser.DeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#instancia_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstancia_clase(AnalizadorParser.Instancia_claseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(AnalizadorParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#lista_variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_variables(AnalizadorParser.Lista_variablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#visibilidad}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilidad(AnalizadorParser.VisibilidadContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(AnalizadorParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#tipo_retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_retorno(AnalizadorParser.Tipo_retornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#sentencias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencias(AnalizadorParser.SentenciasContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#sentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentencia(AnalizadorParser.SentenciaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#asignacion_propiedad}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion_propiedad(AnalizadorParser.Asignacion_propiedadContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#llamado_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamado_funcion(AnalizadorParser.Llamado_funcionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#definicion_parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicion_parametros(AnalizadorParser.Definicion_parametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#lista_definicion_parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_definicion_parametros(AnalizadorParser.Lista_definicion_parametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#casos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasos(AnalizadorParser.CasosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpresion(AnalizadorParser.ExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#incremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIncremento(AnalizadorParser.IncrementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncion(AnalizadorParser.FuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalizadorParser#metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetodo(AnalizadorParser.MetodoContext ctx);
}