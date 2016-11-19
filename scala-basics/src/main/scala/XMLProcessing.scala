import scala.xml.XML

/**
  * Created by govind.bhone on 11/19/2016.
  */
object XMLProcessing extends App {
  val weather =
    <rss>
      <channel>
        <title>Yahoo! Weather - Boulder, CO</title>
        <item>
          <title>Conditions for Boulder, CO at 2:54 pm MST</title>
          <forecast day="Thu" date="10 Nov 2011" low="37" high="58" text="Partly Cloudy"
                    code="29"/>
        </item>
      </channel>
    </rss>

  val weather1 = XML.loadString(
    """
      |<rss>
      |  <channel>
      |    <title>Yahoo! Weather - Boulder, CO</title>
      |    <item>
      |     <title>Conditions for Boulder, CO at 2:54 pm MST</title>
      |     <forecast day="Thu" date="10 Nov 2011" low="37" high="58" text="Partly Cloudy"
      |               code="29" />
      |    </item>
      |  </channel>
      |</rss>""".stripMargin)

  val forecast = weather \ "channel" \ "item" \ "forecast"
  println(forecast)
  val forecast1 = weather1 \ "channel" \ "item" \ "forecast"
  println(forecast1)

  val day = weather \ "channel" \ "item" \ "forecast" \ "@day"
  val date = weather \ "channel" \ "item" \ "forecast" \ "@date"

  println(day)
  println(date)

  val dayText = (forecast \ "@day").text
  println(dayText)

  val dayMultipleSelector = weather \\ "forecast" \ "@day"
  println(dayMultipleSelector)
}
