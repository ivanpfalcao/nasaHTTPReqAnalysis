spark-submit \
  --class com.ivanpfalcao.scala.App \
  --master local[*] \
  --executor-memory 4G \
  --driver-memory 3g \
  --total-executor-cores 1 \
  ./nasaHTTPReqAnalysis-1.0-SNAPSHOT.jar \
  "/root/IdeaProjects/nasaHTTPReqAnalysis/conf/nasa.conf"
