package codecs

//Это задание на доп баллы

trait Codecs[A] extends JsonWriter[A] with JsonReader[A]

object Codecs {
  // Summon Codecs if it exists in implicit scope
  def apply[A]: Codecs[A] = ???
}
