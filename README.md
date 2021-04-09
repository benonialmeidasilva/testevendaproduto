# testvendaproduto
 
Projeto de teste em JAVA para armazenamento de dados de estoque de produtos e cálculo de vendas distribuidas entre logistas.

Foi desenvolvido o back-end do projeto e realizado deploy na plataforma do Heroku, dessa forma ele está disponível na núvem para testes.

**OBS: O Heroku baixa as aplicações quando não estão sendo executas a algum tempo, logo, o sistema subirá automaticamente, porém a primeira requisição poderá ficar lenta até o sistema subir completamente.

Recomento acessar primeiro a URL https://testevendaproduto.herokuapp.com/h2-console , pois quando carregar a página o sistema já subiu, dessa forma poderão ser testadas as funções de carga dos dados e de cálculo normalmente. 

<br></br>
Abaixo, segue os links disponíveis para realização dos testes, basta abrir os links no navegador web.

## Para carregar os dados dos arquivos JSON no banco de dados, basta acessar a URL
 
https://testevendaproduto.herokuapp.com/carregarDados
 
 
## Para calcular as vendas, basta acessar a URL, inserindo produto e quantidade de lojas no lugar apontado dentro das chaves {}, e o sistema retornará os dados das vendas distribuidas entre as lojas, no formato JSON:
 
https://testevendaproduto.herokuapp.com/calcularVenda/{produto}/{quantidade_lojas}
 
Ex: https://testevendaproduto.herokuapp.com/calcularVenda/EMMS/2

**OBS: A carga dos dados é feita em background, dessa forma, após executar a carga no link ".../carregarDados" e não mostrar resultados neste link de calculo de vendas, vai tentando atualizar a página novamente que em alguns segundos o processo da carga termina e mostrará dados no cálculo da venda. O servidor do Heroku possui poucos recursos e o processo fica mais lento que em uma máquina local ou servidor de aplicações comum. 
 
 
## Para acessar o banco de dados, deve acessar através da seguinte URL
  
https://testevendaproduto.herokuapp.com/h2-console
 
Para conectar, deve inserir os valores: 
 
**JDBC URL:** jdbc:h2:mem:testevendaprodutodb
 
**User Name:** sa
 
**Password:** teste123


## Para reexecutar a carga de dados dos arquivos JSON para banco de dados, basta acessar a URL
 
https://testevendaproduto.herokuapp.com/recarregarDados

<br></br><br></br>

# Para executar o projeto na máquina local através do prompt de comando, basta seguir o seguinte tutorial:

No link desde repositório, clicar em "Code > Download ZIP" e extrair o arquivo ZIP do download em uma pasta do computador.

<img src="/src/main/resources/static/images/readme_info/img1.png">

Após extrair o arquivo, acessar a pasta que foi extraída no prompt de comando, e dentro dela executar o comando:

mvn clean package spring-boot:run

Exemplo: C:\Users\benon\Documents\GitHub\testevendaproduto> mvn clean package spring-boot:run

<img src="/src/main/resources/static/images/readme_info/imgPrompt1.png">

Após a execução do comando, o sistema estará disponível para teste

<img src="/src/main/resources/static/images/readme_info/imgPrompt2.png">

**OBS: Para funcionar corretamente, é necessário que estejam instalados o JAVA JDK 8 e o Maven, e configuradas as devidas variáveis de ambiente.

<br></br>
## Os links para utilização do sistema na máquina local após a aplicação ser iniciada são:

http://localhost:8080/carregarDados

http://localhost:8080/calcularVenda/{produto}/{quantidade_lojas}

http://localhost:8080/recarregarDados

http://localhost:8080/h2-console

<br></br><br></br>

# Para executar o projeto na máquina local na IDE Eclipse, basta seguir o seguinte tutorial:
<br></br>
Será necessário possuir instalados na máquina:

-IDE Eclipse (disponível para download no link https://www.eclipse.org/downloads/packages/release/2019-12/r).

-JAVA JDK 8 (disponível para download no link https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html).

<br></br>
No link desde repositório, clicar em "Code > Download ZIP" e extrair o arquivo ZIP do download em uma pasta do computador.

<img src="/src/main/resources/static/images/readme_info/img1.png">

<br></br>
Abrir o Eclipse, e acessar o menu "File > Import..."

<img src="/src/main/resources/static/images/readme_info/img2.png">

<br></br>
Na janela do import, selecionar "Maven > Existing Maven Projects"

<img src="/src/main/resources/static/images/readme_info/img3.png">

<br></br>
Na janela que abriu, clicar no botão Browse, selecionar a pasta que foi extraida do arquivo ZIP baixado do repositório, marcar o checkbox da linha que iniciar com a descrição "/pom.xml ...", e clicar no botão Finish.

<img src="/src/main/resources/static/images/readme_info/img4.png">

<br></br>
Após o projeto ser carregado, poderá aparecer um ícone de exclamação "!" vermelho na pasta raiz do projeto, apenas aguardar pois o Maven irá atualizar os arquivos .jars necessários na máquina. (Esse processo é um download, e possíveis bloqueios de firewall, proxy ou antivirus poderão interferir).
Caso o exclamação não desapareça automaticamente após alguns minutos, favor clicar com o botão direito na pasta raiz do projeto, e clicar no menu "Maven > Update Project...", clicar no botão OK na janela que abrir e aguardar.

<img src="/src/main/resources/static/images/readme_info/img5.png">

<br></br>
Após o projeto completamente carregado e não haver nenhum ícone "x" ou "!" na pasta raiz do projeto, ele poderá ser executado. Para isso basta selecionar a pasta raiz do projeto, clicar no ícone de opções do botão Run, e clicar na opção "TesteApplication".

<img src="/src/main/resources/static/images/readme_info/img6.png">

<br></br>
Na aba Console, serão descritos os logs de inicialização do projeto, e o projeto estará pronto para uso após descrever a linha "Starded TesteApplication in..."

<img src="/src/main/resources/static/images/readme_info/img7.png">

<br></br>
## Os links para utilização do sistema na máquina local após a aplicação ser iniciada são:

http://localhost:8080/carregarDados

http://localhost:8080/calcularVenda/{produto}/{quantidade_lojas}

http://localhost:8080/recarregarDados

http://localhost:8080/h2-console
