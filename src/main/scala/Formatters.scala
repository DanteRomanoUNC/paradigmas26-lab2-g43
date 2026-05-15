// =====================================================================
// Ejercicios 4 y 5: Formateo de resultados
// =====================================================================

/**
 * Responsable de convertir los resultados del análisis a texto para mostrar.
 */
object Formatters {

  /**
   * Formatea el análisis NER de un post individual.
   *
   * @param postTitle título del post analizado
   * @param entities  entidades detectadas en ese post
   * @return bloque de texto con el título y las entidades encontradas
   *
   * TODO (Ejercicio 4): Implementar este método.
   *
   *   Usar el método describe de cada entidad para generar la salida.
   *   No es necesario hacer match sobre el tipo concreto de cada entidad:
   *   describe ya funciona correctamente para cualquier subtipo (polimorfismo).
   *
   *   Ejemplo de salida esperada:
   *
   *     Post: "Scala 3 released at EPFL by Martin Odersky"
   *     Entidades detectadas:
   *       [ProgrammingLanguage] Scala
   *       [University] EPFL
   *       [Person] Martin Odersky
   *
   *   Si no se detectaron entidades, mostrar un mensaje indicándolo.
   */
  def formatNERResult(postTitle: String, entities: List[NamedEntity]): String = {
    val cuerpo = entities match {
      case Nil => "  (sin entidades detectadas)"  // lista vacia (mas expresivo que usar if(entities.isEmpty)-else)
      case _   =>                                 // lista con al menos 1 elemento
        "Entidades detectadas:\n" +
        entities.map(e => s"  ${e.describe}").mkString("\n")
    }

    s"""Post: "$postTitle"
      |$cuerpo""".stripMargin
  }

  /**
   * Formatea un resumen de estadísticas de entidades por tipo.
   *
   * @param counts mapa de entityType → cantidad
   * @return texto con las estadísticas ordenadas por cantidad (de mayor a menor)
   *
   * TODO (Ejercicio 5): Implementar este método.
   *
   *   Ejemplo de salida esperada:
   *
   *     === Estadísticas de entidades ===
   *     Person: 5
   *     ProgrammingLanguage: 3
   *     Organization: 2
   *     University: 2
   */
  def formatEntityStats(counts: Map[String, Int]): String = {
    val lineas = counts.toList                                      // convierte a lista de tuplas
                      .sortBy { case (tipo, cantidad) => -cantidad }// ordena por cantidad de mayor a menor (negativo para orden descendente)
                      .map { case (tipo, cantidad) => s"$tipo: $cantidad" }// transforma cada tupla a una linea de texto
                      .mkString("\n")                               // une todas las lineas con salto de linea

    s"=== Estadísticas de entidades ===\n$lineas"
  }
}
