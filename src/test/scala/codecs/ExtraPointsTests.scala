package codecs

import codecs.Json._
import codecs.JsonReader._
import codecs.JsonWriter._
import codecs.Person._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

// class ExtraPointsTests extends AnyFlatSpec with Matchers {
//   "student" should "be parsed as person if there is no codec for student in scope" in {
//     case class StudentTest(name: String, age: Int, university: University) extends Person
//     StudentTest("Max", 21, University("Inno", "Inno", "Russia", 214)).toJson shouldEqual JsonObject(
//       Map("name" -> JsonString("Max"), "age" -> JsonInt(21))
//     )
//   }

//   "manager json" should "return list of errors if json is invalid" in {
//     val errors = List(
//       WrongType("age"),
//       AbsentField("salary"),
//       AbsentField("employees: (0, age)")
//     ) // You can implement your own error adt, just change test to show it in action
//     JsonObject(
//       Map(
//         "name" -> JsonString("Max"),
//         "age" -> JsonDouble(30),
//         "employees" -> JsonArray(
//           List(
//             JsonObject(
//               Map(
//                 "name" -> JsonString("Andy"),
//                 "salary" -> JsonDouble(40000)
//               )
//             )
//           )
//         )
//       )
//     ).as[Manager] shouldEqual Left(errors)
//   }
// }
