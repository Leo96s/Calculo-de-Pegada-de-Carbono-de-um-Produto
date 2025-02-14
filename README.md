# Calculo-de-Pegada-de-Carbono-de-um-Produto
Pretende-se desenvolver uma aplicação em java para cálculo de Pegada de Carbono de um Produto. O processo de desenvolvimento do produto de software deve serguir práticas de garantia de qualidade.

- [1. Introdução](#tema1)
  - [1.1. Propósito do Documento](./Home/1.Introdução/1.1 Proposito do Documento)
  - [1.2 Âmbito do sistema](./Home/1.Introdução/1.2 Âmbito do sistema)
  - [1.3 Definições, acrónimos e abreviaturas](./Home/1.Introdução/1.3 Definições, acrónimos e abreviaturas)
  - [1.4 Descrição da estrutura do documento](./Home/1.Introdução/1.5 Descrição da estrutura do documento)
- [2. SCM Management](#tema2)
  - [2.1 Non-SCM members](./Home/2. SCM Management/2.1 Non-SCM members)
    - [2.1.1 Dev Team](./Home/2. SCM Management/2.1 Non-SCM members/2.1.1 Dev Team)
    - [2.1.2 Customers](./Home/2. SCM Management/2.1 Non-SCM members/2.1.2 Customers)
  - [2.2 SCM Roles and Responsibilities](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities)
    - [2.2.1 CM Team (Configuration Management Team)](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities/2.2.1 CM Team)
      - [2.2.1.1 CM Leader](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities/2.2.1 CM Team/2.2.1.1 CM Leader)
    - [2.2.2 Configuration Control Board (CCB)](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities/2.2.2 Configuration Control Board)
      - [2.2.2.1 CCB Leader](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities/2.2.2 Configuration Control Board/2.2.2.1 CCB Leader)
      - [2.2.2.2 CCB Members](./Home/2. SCM Management/2.2 SCM Roles and Responsibilities/2.2.2 Configuration Control Board/2.2.2.2 CCB Members)
- [3. SCM Activities](#tema3)
  - [3.1. Configuration Identification](./Home/3. SCM Activities/3.1. Configuration Identification)
  - [3.2. Configuration Control](./Home/3. SCM Activities/3.2. Configuration Control)
  - [3.3. Configuration Status and Accounting](./Home/3. SCM Activities/3.3. Configuration Status and Accounting)
  - [3.4. Capture of Configuration Items](./Home/3. SCM Activities/3.4. Capture of Configuration Items)
- [4. SCRUM](#example4)
  -  [4.1. SCRUM ROLES](./Home/4. SCM Activities/4.1. SCRUM ROLES)
      - [4.1.1 SCRUM MASTER](./Home/4. SCM Activities/4.1. SCRUM ROLES/4.1.1 SCRUM MASTER)
      - [4.1.2 PRODUCT OWNER](./Home/4. SCM Activities/4.1. SCRUM ROLES/4.1.2 PRODUCT OWNER)
      - [4.1.3 SCRUM TEAM](./Home/4. SCM Activities/4.1. SCRUM ROLES/4.1.3 SCRUM TEAM)
      - [4.1.4 CUSTOMER](./Home/4. SCM Activities/4.1. SCRUM ROLES/4.1.4 CUSTOMER)
      - [4.1.5 MANAGEMENT](./Home/4. SCM Activities/4.1. SCRUM ROLES/4.1.5 MANAGEMENT)
- [5. Diagramas](#example5)
  - [5.1. Diagramas de Componentes](./Home/5. Diagramas/5.1. Diagramas de Componentes)
  - [5.2. Diagramas de Classes](./Home/5. Diagramas/5.2. Diagramas de Classes)
- [6. Ferramentas](#example5)
  - [6.1. Java](./Home/6. Ferramentas/6.1. Java)
  - [6.2. Gradle](./Home/6. Ferramentas/6.2. Gradle)
  - [6.3. JUnit](./Home/6. Ferramentas/6.3. JUnit)
  - [6.4. Jacoco](./Home/6. Ferramentas/6.4. Jacoco)
  - [6.5. PMD](./Home/6. Ferramentas/6.5. PMD)
  - [6.6. GitLab](./Home/6. Ferramentas/6.6. GitLab)
- [7. Test Design Specifications](#example6)
  - [7.1. Model](./Home/7. Test Design Specifications/7.1. Model)
  - [7.2. Calculo](./Home/7. Test Design Specifications/7.2. Calculo)
  - [7.3. Import](./Home/7. Test Design Specifications/7.3. Import)
  - [7.4. Export](./Home/7. Test Design Specifications/7.4. Export)
- [8. Test Case Outline](#example7)
  - [8.1. Model](./Home/8. Test Case Outline/8.1. Model)
  - [8.2. Calculo](./Home/8. Test Case Outline/8.2. Calculo)
  - [8.3. Import](./Home/8. Test Case Outline/8.3. Import)
  - [8.4. Export](./Home/8. Test Case Outline/8.4. Export)

## 1. Introdução
### 1.1 Proposito do Documento
O propósito do documento é fornecer uma estrutura clara dos procedimentos e regras utilizados para o desenvolvimento da aplicação, utilizando uma metodologia **_SCRUM_** com recurso a _**TDD**_.

### 1.2 Âmbito do sistema
O tema da sustentabilidade está na ordem do dia e a necessidade de criação de produtos mais amigos do ambiente é um requisito que não advém apenas das exigências dos consumidores, mas também
de imposições legais que, pouco a pouco, vão sendo adotadas no contexto da União Europeia. A sustentabilidade dos produtos poderá ser vista de várias perspetivas: i) percentagem de materiais reciclados usados no fabrico dos produtos; ii) durabilidade dos produtos; iii) potencial de reciclagem e reutilização dos produtos; iv) consumo energético durante o seu ciclo de vida do produto; v)
emissões associadas ao ciclo de vida do produto. Para avaliar a sustentabilidade dos produtos, existem várias metodologias. O **PEF** (_Product Environmetal Footprint_), por exemplo, é uma metodologia recomendada pela Comissão Europeia (**CE**) que fornece uma estrutura padronizada para realizar estudos de avaliação do ciclo de vida de um produto no que concerne a aspetos relacionados com sustentabilidade. A **CE** tem vindo a definir um
conjunto de regras para realização de um estudo **PEF** para diferentes categorias de produtos. As Regras da Categoria **PEF** (**PEFCR**) para a indústria de vestuário e calçado são projetadas para garantir
que todas as marcas sigam uma estrutura comum para calcular e partilhar os impactos ambientais dos seus produtos. Enquanto a **PEFCR** abrange 16 categorias de impacto, a “_Climate Change_” é o indicador de impacto mais utilizado e é fundamental para materializar o **PCF** (_Product Carbon Footprint_). Podemos assim dizer que o **PCF** contempla uma parte específica de um estudo de avaliação do ciclo de vida do produto, ou seja, **PCF** é uma pequena parte do **PEF**. O Indicador de “_Climate Change_”, refere-se ao potencial de Aquecimento Global (**GWP**) e é medido em kg de CO2 equivalente. Deste modo, pretende-se automatizar o processo de cálculo do **PCF** através do desenvolvimento de uma aplicação em java.

### 1.3 Definições, acrónimos e abreviaturas
**Definições:**

> Fluxos de entrada, correspondentes a inputs de matéria-prima e sobre os quais temos maior
> controlo;

> Fluxos de saída, correspondentes aos outputs do processo, sejam o produto acabado,
> produto intermédio ou serviço;

> Outros fluxos de entrada e que correspondem a cofatores de produção e sem os quais não
> seria possível transformar o produto;

> Outros fluxos de saída e que correspondem a emissões decorrentes das tarefas de
> transformação;

> SCRUM é uma metodologia ágil, iterativa e incremental

**Abreviações:**

> PEF (Product Environmetal Footprint)

> CE (Comissão Europeia)

> PEFCR (Regras da Categoria PEF)

> PCF (Product Carbon Footprint)

> GWP (Potencial de Aquecimento Global)

> TDD (Test driven development

> SCM (Software Configuration Management)

### 1.4 Descrição da estrutura do documento
Este documento tem a seguinte estrutura:
- Introdução e análise do problema, neste caso o desenvolvimento de uma aplicação para o cálculo do **PCF**;
- Explicação das variadas definições, acrónimos e abreviaturas utilizadas ao longo do documento;
- Indicação das referências utilizadas;
- Definição do _SCM Plan_ aplicado ao projeto;
- Descrição das **SCRUM Roles**;
- Diagramas da arquitetura de alto nível do software a desenvolver;
- Ferramentas usadas no projeto;

## 2. SCM Management
### 2.1 Non-SCM members
#### 2.1.1 Dev Team
Quando os _developers_ querem fazer mudanças a qualquer código ou documentação, estes precisam de submeter um _ticket_ para a CCB via _GitLab Issue Tracker_ e ter a aprovação dessa mudança da CCB. Eles devem seguir os procedimentos dados a estes pela CCB para submeter _change requests_ e usar os formulários de _change request_ provenientes da equipa de CM. Procedimentos _change request_ e formulários estão descritos na [secção 3.2](https://gitlab.estg.ipp.pt/group_ai/project-esii/-/wikis/Home/3.-SCM-Activities/3.2.-Configuration-Control) deste documento. Os membros desta equipa são Francisco Barbosa, Leonardo Silva, Joaquim Matos e Rafael Silva.

#### 2.1.2 Customers
**Customers:** Nesta seção, listaremos as partes interessadas que desempenham um papel consumidor no contexto deste projeto.
>São os **consumidores** e **utilizadores finais**, por exemplo empresas interessadas no produto e pretendem através do mesmo identificar oportunidades de melhoria, reduzir custos, aumentar a competitividade e contribuir para a sustentabilidade ambiental.

### 2.2 SCM Roles and Responsibilities
#### 2.2.1 CM Team (Configuration Management Team)
A CM team é responsável pela manutenção de toda a documentação, código e o respetivo software durante o projeto. A CM team é constituída pelos seguintes membros: Francisco Barbosa, Leonardo Silva, Joaquim Matos e Rafael Silva.

##### 2.2.1.1 CM Leader
Como isto é um projeto académico, o cargo de CM Leader vai ser rotativo, logo todos os elementos da dev team vão realizar este cargo . O _CM leader_ cria o _CM Plan_, encaminha o estabelecimento da **_CCB_** , encaminha o estabelecimento e a divulgação da mudança de procedimentos e encaminha a fundação de _coding conventions_. Também é a responsabilidade do CM Leader informar as responsabilidades dos membros da equipa. O _CM leader_ também irá realizar reuniões de _review_ de código para garantir que estão a cumprir as tarefas e horários atribuídos.

**_Major change requests_** que resultam num **_commit/merge request_** necessitam de uma **_code review_** realizada pelo _CM leader_.

#### 2.2.2 Configuration Control Board (CCB)
A **_CCB_** é responsável de aprovar mudanças em todo _software_ e documentação pela duração do projeto.

Os procedimentos de aprovação de mudanças da **_CCB_** estão descritos na [secção 3.2](https://gitlab.estg.ipp.pt/group_ai/project-esii/-/wikis/Home/3.-SCM-Activities/3.2.-Configuration-Control) deste documento. É a responsabilidade da **_CCB_** esclarecer aos _developers_ como eles se estão envolvidos em aprovações de mudanças.

##### 2.2.2.1 CCB Leader
O **CCB** **Leader**, assim como o **CM Leader** será uma role rotativa que será realizada por todos os membros da dev team.

Este coordena as reuniões de **CCB** e envia os avisos das reuniões para os membros da equipa do projeto. O **CCB** **Leader** pode de forma autónoma aprovar as _change requests_ que irão ser implementadas ou realizar reuniões do CCB para analizar/aprovar _change requests_, pode interromper os esforços em _change requests_ específicos e estabelecer as _change priorities_.

##### 2.2.2.2 CCB Members
Os CCB members são qualquer pessoa num papel de CCB (temporário ou permanente). Um membro que estiver a atuar como Product Owner também terá o papel de CCB.

