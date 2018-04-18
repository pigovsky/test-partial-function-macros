package com.pigovskyi.models

case class Eq(
    name: String,
    value: Option[String]
) extends Term
