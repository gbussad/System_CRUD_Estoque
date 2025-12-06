# Sistema de Estoque Integrado (Entrega Final - AT)

![Status do Pipeline](https://github.com/gbussad/Systrem_CRUD_Estoque/actions/workflows/maven.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-green)
![Selenium](https://img.shields.io/badge/Selenium-Headless-blue)

## 1. Visão Geral da Arquitetura
O sistema foi desenvolvido seguindo os princípios de **Clean Code** e **Arquitetura Hexagonal (Ports and Adapters)** simplificada. O foco principal foi a robustez, testabilidade e desacoplamento.

### Principais Decisões Arquiteturais:
* **Imutabilidade:** Uso extensivo de Java Records (`record Produto`) para garantir integridade dos dados e evitar efeitos colaterais (Side Effects).
* **Inversão de Dependência (DIP):** O `ProdutoController` não depende da implementação concreta, mas sim da interface `IProdutoService`. Isso permite trocar a implementação do serviço sem quebrar o controlador.
* **Fail Gracefully:** Implementação de um `GlobalExceptionHandler` que captura erros inesperados e apresenta mensagens amigáveis ao usuário, evitando vazamento de Stack Trace.

## 2. Estratégia de Testes e QA
O projeto implementa uma pirâmide de testes completa para garantir a qualidade:

1.  **Testes Unitários:** Validação de regras de negócio no `EstoqueService`.
2.  **Fuzzing Testing (Segurança/DAST):** Implementado em `FuzzingTest.java`, bombardeia o sistema com dados aleatórios para garantir robustez contra entradas maliciosas.
3.  **Testes E2E (Interface):** Implementados com **Selenium WebDriver** rodando em modo **Headless** (sem interface gráfica), permitindo execução em servidores CI/CD. Utiliza o padrão **Page Object Model (POM)** para facilitar a manutenção.

## 3. Pipeline de CI/CD (GitHub Actions)
A automação foi configurada no arquivo `maven.yml` e divide-se em 4 estágios (Jobs):

1.  **Build:** Compilação do código e verificação de sintaxe.
2.  **Testes (QA):** Execução da suíte de testes (JUnit + Selenium). O deploy só ocorre se 100% dos testes passarem.
3.  **Staging:** Simulação de deploy em ambiente de homologação.
4.  **Production:** Geração do artefato final (`.jar`), execução de testes pós-deploy (verificação de integridade) e publicação do release.

## 4. Manual de Execução

### Pré-requisitos
* Java JDK 17
* Maven

### Como Rodar Localmente
1.  Clone o repositório.
2.  Execute o comando: `mvn spring-boot:run`
3.  Acesse: `http://localhost:8080`

### Como Rodar os Testes
* Execute: `mvn test`
* *Nota:* O Selenium abrirá e fechará o navegador rapidamente (ou rodará em background se configurado como Headless).

### Link para Artigo Científico:  
* https://www.researchgate.net/publication/398374955_Revisao_Sistematica_de_Literatura_Eficacia_do_Desenvolvimento_Orientado_por_Testes_em_Projetos_e_Design_de_Software 
