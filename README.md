# ES2_Grupo27

## Elementos do Grupo 27 e respetivo requisito
- 78116 Carolina Calheiros -> Requisito 1
- 82190 Rodrigo Esteves -> Requisito 2
- 83112 Aladje Sanha -> Requisito 3
- 83667 Diogo Afonso -> Requisito 4
- 82421 Andreia Fernandes -> Requisito 5
- 82511 Joana Moutinho -> Requisito 6

## Requisito 1
-As funcionalidades do site wordpress que não dependiam dos outros requisitos estão implementadas. Não foi possível colocar o requisito 2 a funcionar no site wordpress (explicado em baixo) e requisito 6 (aba do menu Covid Spread, também explicado mais em baixo em "requisito )
## Requisito 2
- Todos as funcionalidades estão funcionais, no entanto foram apenas feitas para
a sample page fornecida pelo wordpress, pois ocorreram vários erros 
quando juntado ao ponto 1. Assumi que os repositórios seriam os arquivos 
e adicionei o contacto a esta sample page, para cumprir um dos requisitos.
- Para correr esta monitoring tool sao necessários duas drivers: uma para o 
chrome (chromedriver) e uma para o firefox (gecko driver).
Para correr esta ferramenta basta correr o jar com o nome ES_complemento_meu. 
- No entanto para isto ser feito de duas em duas horas, tem que se usar os eventos 
do windows.Nesse evento o que vai ser executado é um bat file com o nome de script.
- Há que ter atenção que no código java, os caminhos dos driver e do ficheiro têm que 
ser alterados, e se houver mudanças terá que ser gerado um novo jar.
- Deixo aqui o link para o jar : https://drive.google.com/file/d/1wNpc7mpoH3I-yPpS-SYHZCZp4k49d_ac/view?usp=sharing

## Requisito 4
- Todas as funcionalidades pedidas na sua generalidade estão cumpridas, a unica coisa que não esta a funcionar é os VisualDataWeb links, tentei de varias maneiras e não consegui gerar um hyperlink que pudesse depois usar no website, conseguia vizualizar no browser mas depois pelo link ja não dava, então achei por bem simplesmente deixar la os links de tentativa ao inves simplesmente de null.

## Requisito 6 
- Gerar HTML: Foi gerado um HTML com 2 colunas (1ª coluna ficheiro antigo, 2ª coluna ficheiro novo, com as devidas diferenças realçadas). Não consegui gerar o HTML com o Tomcat Server, pois quando o tentei fazer noutra versão do Eclipse, o projeto todo começou a dar          erros,    por isso decidi ficar com o que já tinha em vez de destruir tudo
- Ligar ao git e obter 2 versões anteriores do ficheiro covid19spreading.rdf: O código está todo nas classes disponibilizadas no git, acontece que é possível clonar o repositório, mas quando chega à parte de ir        buscar o ficheiro o mesmo não foi possível, revi o código e não cheguei a nenhuma conclusão sobre o que poderia estar mal
