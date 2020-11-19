#language: pt
#encoding: UTF-8
#author: Feliphe Jesus

Funcionalidade: Teste de execução do site https://www.google.com

  Contexto: Acessar website
    Dado que acesso o site "https://www.google.com"

  @web
  Cenario: Acessar site
    E estou executando o teste
      | Projeto         | Número CT | Nome CT           | Executor      | Sprint    |
      | Testando Google | CT0001    | Barra de Pesquisa | Feliphe Jesus | Sprint 01 |
    E insiro no campo "Barra de Pesquisa" o valor "Feliphej777 GitHub"
    Quando aperto a tecla "Enter"
    Então visualizo os resultados da pesquisa

  @web
  Cenario: Acessar site
    E estou executando o teste
      | Projeto         | Número CT | Nome CT           | Executor      | Sprint    |
      | Testando Google | CT0001    | Barra de Pesquisa | Feliphe Jesus | Sprint 01 |
    E insiro no campo "Barra de Pesquisa" o valor "Feliphej777 GitHub"
    Quando aperto a tecla "Enter"
    Então não visualizo os resultados da pesquisa