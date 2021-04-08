# testeubs
 Projeto de teste de cálculo de produtos
 <br/>
 + Para carregar os dados dos arquivos JSON no banco de dados, basta acessar a URL
 
 https://testeubs.herokuapp.com/carregarDados

 <br/>
 + Para calcular as vendas, basta acessar a URL, inserindo produto e quantidade no lugar apontado dentro das chaves {}:
  
 https://testeubs.herokuapp.com/calcularVenda/{produto}/{quantidade_logistas}
 
 Ex: https://testeubs.herokuapp.com/calcularVenda/EMMS/2

 <br/>
 + Para acessar o banco de dados, deve acessar através da seguinte URL
  
 https://testeubs.herokuapp.com/h2-console
 
 Para conectar, deve inserir os valores:  
 
 JDBC URL: jdbc:h2:mem:testubsdb
 
 User Name: sa
 
 Password: teste123

 
