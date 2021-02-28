# Projeto Gerenciamento de Treinamento

## Compilação
### Requisitos

SDK Liberica 11 (versão full, que contém a biblioteca JavaFX):

 - [Download para Windows 64bit](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-windows-amd64-full.msi)

 - [Download para MacOS](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-macos-amd64-full.dmg)

 - [Download para Linux](https://download.bell-sw.com/java/8u282+8/bellsoft-jdk8u282+8-linux-amd64-full.tar.gz)

 - [Instruções para instalação](https://bell-sw.com/pages/liberica_install_guide-11.0.9/)

IntelliJ Idea Community Edition:

### Instruções de compilação
[Link para download do IntelliJ](https://www.jetbrains.com/pt-br/idea/)

Para compilar no IntelliJ: 
Na barra de menu, vá para File -> New -> Project from Version Control...

 1. Em URL, digite https://github.com/VitorNathanG/ProjetoTreinamento e aperte em Clone;

 2. Com o projeto aberto, na barra de menu, vá para Build -> Build Artifacts. 

 3. No diálogo que é aberto, selecione build.

 4. O arquivo .jar gerado está localizado na pasta out/artifacts/ProjetoTreinamento_jar. Arraste esse arquivo para sua área de trabalho e execute o programa.

## Como o programa funciona

Este projeto é baseado na organização de um evento. Tal evento ocorre de uma maneira bem definida:

 - É divido em duas etapas (ou fases);
 - Cada fase consiste em uma parte de __treinamento__ e um __Coffee Break__  
 - As pessoas são divididas em salas de treinamento (qualquer quantidade delas), para receber o treinamento;
 - Os participantes também são separados em dois (ou mais) espaços de café;
 - Metade dos participantes de cada sala deve trocar de sala entre a primeira e a segunda fase do treinamento;
 - Os participantes devem ser bem divididos entre as salas, ou seja, não pode haver uma diferença superior a 1 participante entre salas ou fases do treinamnto

Isto posto, partiremos para um exemplo de funcionamento:

### Exemplo de uso

Suponha que você precise organizar um treinamento para 40 pessoas, seguindo as diretrizes estabelecidas acima. 
Você tem acesso a 3 salas com capacidade máxima para 15 participantes cada, uma pequena cantina e um café,
ambos com capacidade máxima de 25 pessoas, e deseja usar este aplicativo para fazer a organização.

Ao abrir o programa, clique na opção __Salas__, à direita da interface. Digite um nome para uma sala no local indicado 
na parte inferior da tela, juntamente com a lotação (capacidade máxima) da sala, que é de 15 pessoas e clique em Adicionar.
Repita esse processo para as outras duas salas. As salas criadas aparecerão na tabela acima, juntamente com as
indicações de ocupação.

Feito isso, clique no botão __Espaços de Café__, abaixo do botão __Salas__. Repita o processo utilizado para adicionar uma
sala para incluir também os espaços de café na organização do evento.

Para adicionar os participantes, clique no botão __Participantes__. Insira o nome do participante a ser incluido no evento
e aperte em adicionar participante. Adicione todos os participantes ao evento.

Caso você queira excluir um participante, sala ou espaço, clique sobre o nome do elemento na tabela e clique em __Excluir__,
no canto inferior esquerdo.

Repare que o programa redistribui automaticamente os participantes quando um participante, sala ou espaço de café
é adicionado ou removido ao evento.

Você pode conferir os espaços que cada participante utilizará selecionando a pessoa desejada na tabela e clicando em 
__Detalhes__. Isto abrirá uma janela contendo as todas as informações do participante. O mesmo procedimento pode ser 
feito para consultar os detalhes de cada sala de treinamento ou espaço de café.

Com todas as informações alimentadas para o programa, você pode salvar as informações na barra de Menu, no topo da tela, 
em Arquivo -> Salvar Dados. Mas não se preocupe, caso você esqueça de salvar os arquivos antes fechar o programa, uma janela abrirá perguntando se 
você deseja salvar os arquivos. 

Você também pode fazer um backup do evento, que pode ser recuperado caso você se equivoque ao editar alguma informação. 

## Para desenvolvedores
A implementação do programa leva em conta a resolução do problema acima citado 