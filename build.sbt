scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "io.spray" %%  "spray-json" % "1.3.5",
  "eu.timepit" %% "refined" % "0.9.13"
//  "eu.timepit" %% "refined-shapeless" % "0.9.13"
)
