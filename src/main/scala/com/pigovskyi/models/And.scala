package com.pigovskyi.models

case class And(
    left: Term,
    right: Term
) extends Term
