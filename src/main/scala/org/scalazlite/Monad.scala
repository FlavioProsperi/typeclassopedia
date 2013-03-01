package org.scalazlite

/**
 *   The concept of a Monad
 */
trait Monad[M[_]] extends Applicative[M] {
  def flatMap[A, B](ma: M[A], f: A ⇒ M[B]): M[B]
}

/**
 *   Implicits to help working with Monads.
 *   This is imported by ScalazLite so that all you need to import is ScalazLite._
 */
trait Monads {

  // enrich a value of M[T] with operations 
  implicit class MonadOps[M[_]: Monad, T](value: M[T]) {
    def flatMap[B](f: T ⇒ M[B]): M[B] = implicitly[Monad[M]].flatMap(value, f)
    // lets add Haskell's 'bind' 
    def >>=[B](f: T ⇒ M[B]): M[B] = flatMap(f)
  }
}