import java.util.UUID

import examples.BigSealed
import examples.BigSealed._
import json.CustomProtocol._
import safeid.Id
import safeid.examples.DeviceId
import spray.json._

object PerformanceMain extends App {

  def medianCalculator(seq: Seq[Double]): Double = {
    val sortedSeq = seq.sortWith(_ < _)
    if (seq.size % 2 == 1) sortedSeq(sortedSeq.size / 2)
    else {
      val (up, down) = sortedSeq.splitAt(seq.size / 2)
      (up.last + down.head) / 2
    }
  }

  def stopTime[T](prefix: String)(f: => T): (Double, T) = {
    val t0 = System.nanoTime()
    val result = f
    val t1 = System.nanoTime()
    val elapsed = (t1 - t0) / 1000000000.0
    //println(s"$prefix Elapsed time (in sec): $elapsed")
    (elapsed, result)
  }

  def multipleRuns[T](n: Int)(f: => T): Seq[Double] = {
    (1 to n).map { i =>
      val (time, _) = stopTime(s"Running $i ...:")(f)
      time
    }
  }

  def medianOfMultipleRuns[T](n: Int)(f: => T): Double = {
    val times = multipleRuns[T](n)(f)
    medianCalculator(times)
  }

  val uuids = List.fill(100_000)(UUID.randomUUID())
  val runs = 20

  {
    val strs = uuids.map(_.toString)
    print("BASELINE ...: ")
    val median = medianOfMultipleRuns(runs) {
      strs.map(UUID.fromString).map(_.toJson).map(_.convertTo[UUID])
    }
    println(median)
  }

  {
    print("USING MEMOIZED ...: ")
    //implicit val f: IdFactory[DeviceId] = Id.factory[DeviceId]
    val median = medianOfMultipleRuns(runs) {
      uuids.map(Id.unsafe[DeviceId]).map(_.toJson).map(_.convertTo[DeviceId])
    }
    println(median)
  }

  {
    print("USING AUTO ...: ")
    val median = medianOfMultipleRuns(runs) {
      uuids.map(Id.unsafe[DeviceId]).map(_.toJson).map(_.convertTo[DeviceId])
    }
    println(median)
  }

  val ls = List.fill(500_000)("10")
  
  {
    val times = multipleRuns(3) {
      ls.collect {
        case BigSealed(A) => A
        case BigSealed(B) => B
        case BigSealed(C) => C
        case BigSealed(D) => D
        case BigSealed(E) => E
        case BigSealed(F) => F
        case BigSealed(G) => G
        case BigSealed(H) => H
        case BigSealed(I) => I
        case BigSealed(J) => J
        case BigSealed(K) => K
        case BigSealed(L) => L
        case BigSealed(M) => M
        case BigSealed(N) => N
        case BigSealed(O) => O
        case BigSealed(other) => other
        case other => other
      }
    }
    println(s"Median Time: ${medianCalculator(times)}")
  }
}
