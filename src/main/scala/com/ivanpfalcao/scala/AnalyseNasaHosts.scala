package com.ivanpfalcao.scala

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

//This Class Analyses the information of the Nasa Hosts files
class AnalyseNasaHosts (sc: SparkContext, hostFilesPath: String) {
  val nasaFilesRDD = sc.wholeTextFiles(hostFilesPath, 2).cache();


  nasaFilesRDD.map { case (filename : String, values: String) => filename}


  val nasaHostsRDD = nasaFilesRDD.flatMap { case (filename : String, values: String) => values.split("\n") }
                              .filter(line => line.contains(" - - "))
                              .map(
                                  line => List(
                                    // [0] URL
                                    line.split(" - - ")(0)

                                    // [1] DATE TIME - regex between brackets
                                    , "\\[([^]]+)\\]".r.findFirstMatchIn(line).toList(0)
                                            .toString.replace("[","").replace("]","")

                                    // [2] RESOURCE - regex between double quotes
                                    , """(["']).*?\1""".r.findFirstMatchIn(line).toList(0)


                                    // [3] ERROR - regex everything after quotes and spaces
                                    , "\" (.*)".r.findFirstMatchIn(line).toList(0).toString.split(" ")(1)

                                    // [4] BYTES RETURNED - regex everything after quotes and spaces
                                    , "\" (.*)".r.findFirstMatchIn(line).toList(0).toString.split(" ")(2)
                                  )
                              )



  // Return the number of distinct hosts
  def getNumberOfDistinctHosts: RDD[String] = {
    val numberOfDistinctHosts = nasaHostsRDD.map{list => (list(0))}.distinct().count()

    sc.parallelize(List("Numero de hosts distintos: " + numberOfDistinctHosts))
  }

  // Return the number of hosts with 404 error
  def getNumberOf404: RDD[String] = {
    val numberOf404 = nasaHostsRDD.filter(list => list(3).toString.contains("404")).count()

    //println("Numero de Hosts com 404: %s".format(numberOf404))

    sc.parallelize(List("Numero de erros 404: " + numberOf404))
  }

  // Get the top N URL with 404
  def getTopURLOf404(numberOfErrors:Int) : RDD[(String,Int)] = {
    val numberOf404List = nasaHostsRDD.filter(list => list(3).toString.contains("404"))
                                    .map{ list => (list(0).toString,1)}.reduceByKey(_ + _)
                                    .sortBy(_._2,false)
                                    .take(numberOfErrors)

    val numberOfTOPNRDD = sc.parallelize(numberOf404List)

    numberOfTOPNRDD
  }

  // Get the number of 404 errors grouped by day
  def get404ByDay: RDD[(String,Int)] = {
    val numberOf404ByDayRDD = nasaHostsRDD.filter(list => list(3).toString.contains("404"))
                                          .map{ list => (list(1).toString.split(":")(0),1)}
                                          .reduceByKey(_ + _)
                                          .sortBy(_._2,false)


    numberOf404ByDayRDD
  }

  // Get the total number of bytes
  def getTotalOfBytes: RDD[String] = {
    val totalOfBytes = nasaHostsRDD.filter(list => scala.util.Try(list(4).toString.toInt).isSuccess)
                                    .map(list => list(4).toString.toLong)
                                    .sum()


    sc.parallelize(List("Total de Bytes: " + totalOfBytes))
  }




}
