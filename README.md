# Json Codecs
Ваша задача реализовать json парсер, используя тайпклассы.
Программа должна уметь парсить из/в Json:
* строки
* целые числа
* числа с плавающей запятой
* листы
* кастомные пользовательские типы
### Json
`Json` может быть отображен нулом, строкой, числами, массивом или json объектом
```scala=
sealed trait Json
object Json {
  final case object JsonNull extends Json
  final case class JsonString(value: String) extends Json
  final case class JsonInt(value: Int) extends Json
  final case class JsonDouble(value: Double) extends Json
  final case class JsonArray(value: List[Json]) extends Json
  final case class JsonObject(value: Map[String, Json]) extends Json
}
```
Для каждого Json реализовать свой инстанс Show, для отображения его в виде строки как у привычного json

### JsonWriter[A]
Этот тайпкласс может принимать значение типа `А` и трансформировать его в `Json` объект. 
Также у него есть summoner и синтаксическое расширение для вызова `toJson` прямо на объекте типа `А`

Если для нужного типа B нет `JsonWriter[B]`, но при этом есть для типа `A :> B`, то должен использоваться `JsonWriter[A]`, который сериализует только те поля, который определены в объекте `A` 
### JsonReader
Этот тайпкласс может принимать `Json` и возвращать объект типа `Right(А)`, сконструированный из `Json`. Либо первую встреченную ошибку в `Left`.

Для списка объектов можно ограничиться только `JsonReader[List[A]]`, другие коллекции уметь парсить не нужно.

Ридер должен обладать подробным трекингом полей с ошибками: 
- Если ридер встречает ошибку в листе сложных элементов (кейс классов), то в ошибке должен содержаться порядковый номер этого элемента и название поля c ошибкой. 
```scala=
case class Student(name: String, age: Int)
JsonReader[List[Student]].read(JsonArray(List(JsonObject(Map("name" -> "Vasya"))))) // выдаст ошибку вида AbsentField("[0].age")
```
- Если объект является вложенным в другой объект, то название родительского поля, в котором содержится объект с ошибкой, должно быть добавлено в описание ошибки
```scala=
case Address(street: String, country: String)
case class Student(name: String, address: Address)
JsonReader[Student].read(JsonObject(Map("name" -> "Vasya", "address" -> JsonObject("street" -> "street")))) 
  // например выдаст ошибку вида AbsentField("address.country")
```

Текстовка ошибок в примерах выше не финальная, вы можете скорректировать для удоства. Главное должно быть понятно, где именно произошла ошибка при вложенности.


Как вариант, в задание предложено подобное адт ошибок:
* `WrongType` - `JsonReader` нашел поле с нужным именем, но оно оказалось неподходящего типа для конструирования типа `А`.
* `AbsentField` - `JsonReader` не нашел нужного поля в исходном `Json`
* Вы можете дополнять/модифицировать адт ошибок, если считаете предложенное недостаточным. Главное не забудьте отразить новые ошибки в тестах.

Весь функционал покрыть тестами: все возможные типы Json, парсинг и ошибки.
