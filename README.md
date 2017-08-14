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


Resolução:

Foi criada uma classe em Spark Scala que utiliza a função de leitura de arquivos em um determinado diretório, gerando um RDD unificado (wholeTextFiles). Com esse RDD criado, foram utilizadas algumas operações de map para separação dos campos. Como os separadores não eram padrões, utilizei algumas funções de regex para divisão dos campos. Após essa separação, gravei um RDD contendo listas de campos. Para acessar as informações, implementei os seguintes métodos:

- getNumberOfDistinctHosts: Retorna um RDD contendo o número de hosts distintos;
- getNumberOf404: Retorna um RDD contendo o número de hosts que receberam o erro 404;
- getTopURLOf404: Retorna um RDD contendo os N hosts com mais erros 404 (o N é recebido por parâmetro pelo método);
- get404ByDay: Retorna um RDD contendo o número de erros 404 agrupados por dia;
- getTotalOfBytes: Retorna um RDD contendo o número total de bytes consumidos;
