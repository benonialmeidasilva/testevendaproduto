# testeubs
 
Projeto de teste em JAVA para armazenamento de dados de estoque de produtos e cálculo de vendas distribuidas entre logistas.

Foi desenvolvido o back-end do projeto e realizado deploy na plataforma do Heroku, dessa forma ele está disponível na núvem para testes.




Abaixo, segue os links disponíveis para realização dos testes, basta abrir os links no navegador web.
 


 
## Para carregar os dados dos arquivos JSON no banco de dados, basta acessar a URL
 
https://testeubs.herokuapp.com/carregarDados
 
 
## Para calcular as vendas, basta acessar a URL, inserindo produto e quantidade no lugar apontado dentro das chaves {}:
 
 https://testeubs.herokuapp.com/calcularVenda/{produto}/{quantidade_logistas}
 
Ex: https://testeubs.herokuapp.com/calcularVenda/EMMS/2
 
 
## Para acessar o banco de dados, deve acessar através da seguinte URL
  
https://testeubs.herokuapp.com/h2-console
 
Para conectar, deve inserir os valores: 
 
**JDBC URL:** jdbc:h2:mem:testubsdb
 
**User Name:** sa
 
**Password:** teste123

 
