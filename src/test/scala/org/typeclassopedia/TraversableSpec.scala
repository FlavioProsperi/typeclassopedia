package org.typeclassopedia

import Typeclassopedia._
import org.scalatest._

class TraversableSpec extends FlatSpec {

  "A Traversable" should "support traverse" in assert(1.some.traverse(v ⇒ List(v, v + 1)) === List(1.some, 2.some))

  it should "support sequence" in assert(List(1.some).sequenceA === List(1).some)

  it should "support mapM" in assert(1.some.mapM(v => List(v)) === List(1.some))

  it should "preserve order" in assert(List(1, 2, 3).traverse(Option(_)) === Some(List(1, 2, 3)))

  it should "traverse through option effect" in {
    val fn = (x: Int) ⇒ if (x < 3) Some(x) else None
    assert(List(1, 2, 3).traverse(fn) === none[List[Int]])
  }

  it should "not blow the stack" in {
    val s = List.range(0, 32 * 1024).traverse(x ⇒ x.some)
    assert(s.map(_.take(3)) === Some(List(0, 1, 2)))
  }
}