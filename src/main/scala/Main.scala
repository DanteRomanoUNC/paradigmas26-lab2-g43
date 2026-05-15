// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================

object Main {
  def main(args: Array[String]): Unit = {

    // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------
    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    // ------------------------------------------------------------------
    // Paso 2: Descargar posts
    // ------------------------------------------------------------------
    val subscriptions = FileIO.readSubscriptions()

    val allDetectedEntities: List[NamedEntity] = subscriptions.flatMap { url =>
      println(s"Descargando posts de: $url\n")
      val json   = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)

      // ------------------------------------------------------------------
      // Paso 3: Detectar entidades y mostrar resultados por post
      // ------------------------------------------------------------------
      titles.flatMap { title =>
        val entities = Analyzer.detectEntities(title, dictionary)
        println(Formatters.formatNERResult(title, entities))
        entities
      }
    }

    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------
    val counts = Analyzer.countByType(allDetectedEntities)
    println(Formatters.formatEntityStats(counts))
  }
}
