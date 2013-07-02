package org.typeclassopedia

import Typeclassopedia._
import org.scalatest._

class FunctorSpec extends FlatSpec with MustMatchers {

  val inc: Int ⇒ Int = 1 + _
  val double: Int ⇒ Int = 2 * _

  "A functor" should "map stuff" in { Blub(1).map(double) must be === Blub(2) }
  it should "obey the identity law" in { Blub(1).map(identity) must be === Blub(1) }
  it should "obey the composition law" in { Blub(1).map(inc andThen double) must be === Blub(1).map(inc).map(double) }
}