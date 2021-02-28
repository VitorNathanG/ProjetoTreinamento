# Projeto Gerenciamento de Treinamento

###Objetivo
Criar um sistema simples para gerenciar a distribuição de participantes em um
evento.

Por Vitor Nathan Gonçalves.

## Compilação
### Requisitos

SDK Liberica 11 (versão full, que contém a biblioteca JavaFX):

 - [Download para Windows 64bit](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-windows-amd64-full.msi)

 - [Download para MacOS](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-macos-amd64-full.dmg)

 - [Download para Linux](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-linux-amd64-full.tar.gz)

 - [Instruções para instalação](https://bell-sw.com/pages/liberica_install_guide-11.0.9/)

IntelliJ Idea Community Edition: [Download do IntelliJ](https://www.jetbrains.com/pt-br/idea/)

### Instruções de compilação

#### Caso seja a primeira abertura do programa:

Selecione a opção "Get From VCS". Continue na numeração abaixo

#### Caso já tenha algum projeto aberto:
Na barra de menu, vá para File -> New -> Project from Version Control...

 1. Em URL, digite https://github.com/VitorNathanG/ProjetoTreinamento e aperte em Clone;

 2. Abaixo e a direita do nome da janela, clique no botão Add Configuration.

 3. No canto superior esquerdo, clique em + -> Application.

 4. Na opção _module not specified_, clique em _select alternative JRE..._ e selecione o caminho até a JDK Liberica (geralmente C:\\ProgramFiles\\BellSoft\\LibericaJDK-11-Full)
 
 5. Na opção ao lado (Main class), digite classes.Main. Clique em Apply.

 6. Perto de onde estava o botão Add Configuration, clique no botão Run "Main", e uma mensagem de erro aparecerá no canto inferior direito. Clique em Configure

 7. Abaixo do nome do projeto, clique em Dependencies.

 8. Em Module SDK: selecione "11 Bellsoft Liberica JDK", e clique em Apply.

 9. Espere alguns instantes para a JDK ser indexada.
 
 10. na barra de menu, vá para Build -> Build Artifacts. 

 11. No diálogo que é aberto, selecione Rebuild.

 12. O arquivo .jar gerado está localizado na pasta out/artifacts/ProjetoTreinamento_jar. Arraste esse arquivo para sua área de trabalho e execute o programa.

_Nota 1: caso ocorra algum problema no processo de compilação, é possível utilizar o .jar já construido na pasta indicada acima._

## Como o programa funciona

Este projeto é baseado na organização de um evento. Tal evento ocorre de uma maneira bem definida:

 - É dividido em duas etapas (ou fases);
 - Cada fase consiste em uma parte de __treinamento__ e um __Coffee Break__  
 - As pessoas são divididas em salas de treinamento (qualquer quantidade delas), para receber o treinamento;
 - Os participantes também são separados em dois (ou mais) espaços de café;
 - Metade dos participantes de cada sala deve trocar de sala entre a primeira e a segunda fase do treinamento;
 - Os participantes devem ser bem divididos entre as salas, ou seja, não pode haver uma diferença superior a 1 participante entre salas ou fases do treinamento

Isto posto, partiremos para um exemplo de funcionamento:

### Exemplo de uso

Suponha que você precise organizar um treinamento para 40 pessoas, seguindo as diretrizes estabelecidas acima. 
Você tem acesso a 3 salas com capacidade máxima para 15 participantes cada, uma pequena cantina e um café,
ambos com capacidade máxima de 25 pessoas, e deseja usar este aplicativp para fazer a organização.

Ao abrir o programa, clique na opção __Salas__, à esquerda da interface. Digite um nome para uma sala no local indicado 
na parte inferior da tela, juntamente com a lotação (capacidade máxima) da sala, que é de 15 pessoas, e clique em Adicionar.
Repita este processo para as outras duas salas. As salas criadas aparecerão na tabela acima, juntamente com as
indicações de ocupação.

Feito isso, clique no botão __Espaços de Café__, abaixo do botão __Salas__. Repita o processo utilizado para adicionar uma
sala para incluir também os espaços de café na organização do evento.

Para adicionar os participantes, clique no botão __Participantes__. Insira o nome do participante a ser incluido no evento
e aperte em adicionar participante. Adicione todos os participantes ao evento.

Caso você queira excluir um participante, sala ou espaço, selecione o nome do elemento na tabela e clique em __Excluir__,
no canto inferior esquerdo.

Repare que o programa redistribui automaticamente os participantes quando um participante, sala ou espaço de café
é adicionado ou removido do evento.

Você pode conferir os espaços que cada participante utilizará selecionando a pessoa desejada na tabela e clicando em 
__Detalhes__. Isto abrirá uma janela contendo todas as informações do participante. O mesmo procedimento pode ser 
utilizado para consultar os detalhes de cada sala de treinamento ou espaço de café.

Com todas as informações alimentadas no programa, você pode salvar as informações na barra de Menu, no topo da tela, 
em Arquivo -> Salvar Dados. Mas não se preocupe, caso você se esqueça de salvar os arquivos antes fechar o programa, uma janela se abrirá perguntando se 
você deseja salvar os arquivos. 

Você também pode fazer um backup do evento, que pode ser recuperado caso você se equivoque ao editar alguma informação. 

## Para desenvolvedores

Disclaimer: caso uma sala tenha um número ímpar de participantes, o algoritmo define que a metade desses participantes + 1 deve trocar de sala.
Caso uma sala seja para 1 participante (generalização matemática do problema), esse participante deve trocar de sala.

A implementação do programa leva em conta a resolução do problema acima citado para uma quantidade arbitrária tanto de pessoas, 
quanto de espaços (tanto as salas quanto os espaços de café), levando em consideração também, espaços com capacidades distintas.
Para situações onde uma das salas com capacidade menor fique lotada, o programa ainda consegue separar as outras pessoas nas salas que
ainda não estão lotadas __às vezes__*. 

No exemplo de uso anterior, caso uma das salas tivesse lotação máxima de 12 participantes, o algoritmo designaria 
12 pessoas para essa sala (sendo que 6 delas trocariam de sala para a segunda fase), e as outras duas salas ficariam com 14 
pessoas cada (sendo que 7 trocariam de sala para a segunda fase).

Tornando a situação mais complexa: caso além dessas 3 salas, fosse adicionada uma 4ª sala, que tivesse lotação máxima de 7
pessoas, o programa distribuiria 7 pessoas para essa sala e 10 pessoas para a sala que comporta apenas 10. As outras 23 pessoas seriam
distribuídas entre as salas que comportam 20 pessoas, ficando uma sala com 11 e a outra com 12 pessoas.

*Explicação para o "às vezes": o algoritmo que foi criado com o propósito de distribuir os integrantes
para a segunda fase não consegue lidar com algumas situações extremas relacionadas a uma grande discrepância de capacidades
máximas entre salas/cafés. Parte desse problema está relacionado com a natureza do algoritmo, que não é 100% funcional nessas situações,
mas outra parte provém de fatores matemáticos.

Um exemplo onde seria impossível a distribuição: distribuir 10 pessoas em 3 salas, uma para 6 pessoas e duas salas para 2 pessoas.
Como 3 pessoas precisam sair da primeira sala, e só há 2 vagas nas outras salas para elas (uma pessoa por sala), essa disposição de
salas não atende às diretrizes estabelecidas e não funcionará se inserida no programa.

O autor definiu uma família infinita de situações que não segue as diretrizes do algoritmo, e que não terá sua 
distribuição bem sucedida. Essa família consiste na generalização do exemplo impossível acima: um número arbitrário de 
salas com capacidade máxima baixa e uma sala com capacidade maior. Caso a ocupação necessária (número mínimo de pessoas que 
necessariamente utilizará este espaço) da sala maior seja superior à metade do número de total de participantes, 
a distribuição torna-se sempre impossível. 

### Teorema:
_Qualquer situação que não esteja descrita no parágrafo acima é uma situação onde a distribuição é 
teoricamente possível._

### Demonstração
Denotaremos por Xp o número de participantes que estão na sala P.

Assuma uma quantidade N de participantes para o evento, onde N é um inteiro positivo. Tomando uma sala A qualquer sem
perda de generalidade, por definição, a sala A possui no máximo N/2 (ou (N-1)/2 caso N ímpar) participantes.
Todas as outras salas combinadas possuem, no mínimo, N/2 (ou (N+1)/2 caso N ímpar) participantes. Devido ao disclaimer apresentado no 
início da seção "Para desenvolvedores", é percebido que o número de participantes que precisam trocar de sala para a segunda etapa da sala 
A é de, no máximo, N/4.

As N/2 pessoas que não estão na sala A são particionadas e atribuídas para uma quantidade qualquer de salas. Tomemos uma
destas salas e chamemo-la sala B, sem perda de generalidade. A sala B terá um valor Xb de participantes, dentre os quais pelo menos
Xb/2 troca de sala para a segunda parte (ou (Xb + 1)/2 caso ímpar). Tomemos então o somatório S de todos os participantes em
todas as salas (exceto a sala A) que trocam de sala para a segunda fase. É fácil perceber que o valor mínimo deste somatório 
será de:

    S ≥ Xb/2 + Xc/2 + Xd/2 + ... + Xn/2
    S ≥ (Xb + Xc + Xd + ... + Xn)/2

Perceba que o somatório Xb + Xc + Xd + ... + Xn é justamente o total N de participantes em todas as salas, menos o total de
participantes da Sala A, que é de, no máximo, N/2, ou seja:

    S ≥ (Xb + Xc + Xd + ... + Xn)/2 = (N - Xa)/2
    S ≥ (N-Xa)/2 ≥ (N-N/2)/2
    S ≥ (N-N/2)/2 = N/4
    S ≥ N/4

Como S é o número de participantes que trocam de sala em todas as salas que não são a sala A, e N/4 é o valor máximo de 
participantes que saem da sala A para outras salas, podemos concluir que sempre é possível transferir os participantes da
sala A para outras salas P quaisquer na segunda etapa. Como tomamos todas as salas de forma arbitrária, demonstramos que:

_Caso nenhuma sala tenha sua ocupação mínima superior à metade do total de participantes, é sempre possível fazer 
a distribuição dos participantes nos espaços para a segunda etapa do treinamento._

### Informações adicionais

Este é o primeiro projeto que o autor desenvolveu depois de um hiato de quase 3 anos sem programar. É possível que em certas
situações, más práticas de programação tenham sido empregadas, ou que algum algoritmo não funcione corretamente 100% das vezes, 
especialmente graças ao nível de generalização do problema que o autor optou por implementar. 

Tirando isso, esse desafio foi uma experiência interessantíssima que reavivou (e muito), meu desejo de voltar a programar.
