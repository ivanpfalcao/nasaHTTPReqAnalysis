spark-submit \
  --class com.ivanpfalcao.scala.App \
  --master local[*] \
  --executor-memory 4G \
  --driver-memory 3g \
  --total-executor-cores 1 \
  ./nasaHTTPReqAnalysis.jar \
  "./nasa.conf"
  
