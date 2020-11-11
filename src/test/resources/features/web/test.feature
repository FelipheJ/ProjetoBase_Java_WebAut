#language: pt
#encoding: UTF-8
#author: Feliphe Jesus

Funcionalidade: Teste de execução do site xxxxxxx

  Contexto: Acessar website
    Dado que acesso o site "https://www.xxxxxxx.com.br/"

  @web
  Cenario: Acessar site
    E estou executando o teste
      | Projeto              | Número CT | Nome CT      | Executor | Sprint    |
      | Criação de Framework | CFrame01a | CT que passa | Lorrão   | Sprint 01 |

  @web @teste
  Cenario: Acessar site e falhar
    E estou executando o teste
      | Projeto              | Número CT | Nome CT      | Executor | Sprint    |
      | Criação de Framework | CFrame01a | CT que passa | Lorrão   | Sprint 01 |
    E clico no botão "teste"