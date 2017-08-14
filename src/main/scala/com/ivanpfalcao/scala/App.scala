package com.ivanpfalcao.scala

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf



object App{

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Nasa Hosts Application").setMaster("local");
    val sc = new SparkContext(conf)

    println("Configuration File: " + args(0))
    val confFile = new ConfigurationFile(args(0))


    //val filePath = "/home/cloudera/nasa/nasaHosts"
    //val inputPath   = "/home/cloudera/nasa/testesNasaHosts"
    //val outputPath  = "file:/home/cloudera/nasa/nasaResults"

    val inputPath   = confFile.getInputFilePath
    val outputPath  = confFile.getOutputFilePath


    val numberOfDistinctHostsPath = outputPath + "/numberOfDistinctHostsPath"
    val top404URLPath             = outputPath + "/top404URL"
    val numberOf404Path           = outputPath + "/numberOf404"
    val error404ByDayPath         = outputPath + "/error404ByDayPath"
    val totalNumberOfBytePath     = outputPath + "/totalNumberOfBytePath"


    val nasaHosts = new AnalyseNasaHosts(sc,inputPath)

    //Executes question 1
    val numberOfDistinctHostsRDD  = nasaHosts.getNumberOfDistinctHosts

    //Executes question 2
    val numberOf404RDD            = nasaHosts.getNumberOf404

    //Executes question 3
    val top404URLRDD              = nasaHosts.getTopURLOf404(5)

    //Executes question 4
    val error404ByDayRDD          = nasaHosts.get404ByDay

    //Executes question 5
    val totalNumberOfBytesRDD     = nasaHosts.getTotalOfBytes



    //Saves question 1
    numberOfDistinctHostsRDD.saveAsTextFile(numberOfDistinctHostsPath)

    //Saves question 2
    numberOf404RDD.saveAsTextFile(numberOf404Path)

    //Saves question 3
    top404URLRDD.saveAsTextFile(top404URLPath)

    //Saves question 4
    error404ByDayRDD.saveAsTextFile(error404ByDayPath)

    //Saves question 5
    totalNumberOfBytesRDD.saveAsTextFile(totalNumberOfBytePath)





    //val printRDD = nasaHosts.printValuesOfRDD
  }

}
