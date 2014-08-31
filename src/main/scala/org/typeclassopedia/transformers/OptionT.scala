package org.typeclassopedia.transformers

import org.typeclassopedia._

/**
 * The type OptionT[M[_], A] is a monad transformer that constructs an Option[A] inside the monad M.
 */
case class OptionT[M[_] : Monad, A](run: M[Option[A]]) {

  private val monadM = implicitly[Monad[M]]

  /**
   * Apply a function to the Option[A] contained by `run`
   */
  private def mapO[B](f: Option[A] ⇒ B): M[B] = monadM.map(run, f)

  def map[B](f: A ⇒ B): OptionT[M, B] = OptionT(mapO(_.map(f)))

  def flatMap[B](f: A => OptionT[M, B]): OptionT[M, B] = OptionT(monadM.flatMap(run, (o: Option[A]) ⇒ o match {
    case None ⇒ monadM.pure(Option.empty[B])
    case Some(x) ⇒ f(x).run
  }))

  // useful methods found on Option that let OptionT have an Option-like API

  def isDefined: M[Boolean] = mapO(_.isDefined)

  def isEmpty: M[Boolean] = mapO(_.isEmpty)

  def getOrElse(default: ⇒ A): M[A] = mapO(_.getOrElse(default))

  // etc.
}