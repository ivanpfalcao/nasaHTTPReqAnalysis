spark-submit \
  --class com.ivanpfalcao.scala.App \
  --master local[1] \
  --executor-memory 2G \
  --total-executor-cores 1 \
  ./nasaHTTPReqAnalysis.jar \
