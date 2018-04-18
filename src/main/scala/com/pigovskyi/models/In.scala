package com.pigovskyi.models

case class In(
    name: String,
    values: Seq[String]
) extends Term
