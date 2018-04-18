package utils

import com.pigovskyi.models.{And, Eq, In, Term}
import com.pigovskyi.utils.TemplateExtractor
import org.scalatest.{FlatSpec, MustMatchers}

class TemplateExtractorSpec extends FlatSpec with MustMatchers {
  "TemplateExtractor" should "extract template" in {
    TemplateExtractor.extractTemplate({
      case And(In("category.id", ids), Eq("product.id", Some(id))) =>
        s"$id: ${ids.mkString}"
    }) mustBe
      And(In("category.id", Seq.empty), Eq("product.id", None))
  }
}
