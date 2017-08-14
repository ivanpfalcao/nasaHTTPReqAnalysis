# nasaHTTPReqAnalysis

Realiza a análise dos arquivos de requisição do servidor WWW do NASA Kennedy Space Center
(link dos datasets: http://ita.ee.lbl.gov/html/contrib/NASA-HTTP.html)

Pré-requisitos:
- Distribuição Linux compatível com Apache Spark;
- Java JDK 8 ou superior;
- Apache Spark v1.6.0 ou superior;
- Apesar de não ser obrigatório, recomenda-se a execução em um ambiente Cloudera compatível (CDH 5 ou superior).


Execução:
- No diretório build, editar o arquivo nasa.conf;
- Configurar os diretórios de entrada e saída dos arquivos (inputPath e outputPath, respectivamente);
- Executar o bash nasaHTTPReqAnalysis.sh (bash nasaHTTPReqAnalysis.sh).
