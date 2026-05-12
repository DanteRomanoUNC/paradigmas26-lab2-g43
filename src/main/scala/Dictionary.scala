// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================
import scala.io.Source
/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    val file = Source.fromFile(filePath)
    val allEntities = file.getLines().filterNot(entity => entity.startsWith("#")).map(entity => entityType match {
      case "Person" => new Person(entity)
      case "Organization" => new Organization(entity)
      case "University" => new University(entity)
      case "Place" => new Place(entity)
      case "Technology" => new Technology(entity)
      case "ProgrammingLanguage" => new ProgrammingLanguage(entity)
    }).toList
    file.close()
    allEntities
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   * TODO (Ejercicio 2): Implementar este método.
   *
   */
  def loadAll(): List[NamedEntity] = {
    val files = List(
      ("data/people.txt", "Person"),
      ("data/universities.txt", "University"),
      ("data/organizations.txt", "Organization"),
      ("data/places.txt", "Place"),
      ("data/languages.txt", "ProgrammingLanguage")
    )
    files.flatMap{case (path,entityType) => loadFromFile(path, entityType)}
  }
}
