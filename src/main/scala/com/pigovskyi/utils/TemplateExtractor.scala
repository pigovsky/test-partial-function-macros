package com.pigovskyi.utils

import com.pigovskyi.models.{And, Eq, In, Term}

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object TemplateExtractor {

  def extractTemplate(function: PartialFunction[Term, String]): Term =
    macro extractTemplateImpl

  def extractTemplateImpl(c: blackbox.Context)(
      function: c.Expr[PartialFunction[Term, String]]): c.Expr[Term] = {
    import c.universe._
    import compat._

    def handle(tree: Tree) =
      tree match {
        case Apply(constructor @ TypeTree(), args) =>
          val SingleType(tpe, sym) = constructor.original.tpe
          val typeSym = TypeRef(tpe, sym, List.empty)
          if (typeSym =:= typeOf[And]) {
            println(s"`And` detected with ${showRaw(args)}")
          } else {
            println(
              s" ${showRaw(typeSym)} != ${showRaw(typeOf[And])} , args ${showRaw(args)}")
          }
      }

    function.tree
      .collect {
        case CaseDef(applyTree, _, _) =>
          applyTree
      }
      .headOption
      .foreach {
        handle
      }

    val res = reify(And(In("category.id", Seq.empty), Eq("product.id", None)))
    println(s"\n\n expected ${showRaw(res)} \n\n")

    reify(Eq("", None))
  }

}
