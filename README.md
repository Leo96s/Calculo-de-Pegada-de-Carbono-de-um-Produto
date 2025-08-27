# Calculo-de-Pegada-de-Carbono-de-um-Produto
Pretende-se desenvolver uma aplicação em java para cálculo de Pegada de Carbono de um Produto. O processo de desenvolvimento do produto de software deve serguir práticas de garantia de qualidade.

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

## 3. SCM Activities
### 3.1. Configuration Identification
As versões irão ser numeradas através de Versionamento Semântico,no formato X.Y.Z, onde X, Y, e Z são inteiros não negativos, não contendo zeros à esquerda.Cada elemento aumenta numericamente. Por exemplo: 1.9.0 -> 1.10.0 -> 1.11.0. Grandes mudanças ao projeto aumenta numericamente o X de forma a que a versão 1.0.0 passa para 2.0.0, mudanças menores como adição de funcionalidades, por exemplo, aumenta numericamente o Y de forma a que a versão 1.0.0 passa para 1.1.0, pequenas correções ao projeto (_Patches_) aumentam numericamente o Z de forma a que a versão 1.0.0 passa para 1.0.1. A versão _Alpha_ irá começar com a numeração 1.0.0-alpha seguido da versão _Beta_ passando para 1.0.0-beta.

### 3.2. Configuration Control
Esta secção tem como objetivo garantir que todas as alterações nos artefactos de configuração sejam feitas de maneira planeada e controlada, para manter a integridade e a qualidade do produto ao longo do ciclo de vida do mesmo.

 - Solicitação de Mudanças (Change Request)
   - Qualquer mudança proposta deve ser formalmente registrada por meio de uma Solicitação de Mudança (Change Request - CH).
   - O CH deve incluir a descrição da mudança, o motivo, o impacto esperado e a urgência.
   - Os CHs podem ser aprovados, rejeitados ou em estado de negotiation, caso haja pouca informação do assunto.
   - Os CHs so irão passar para UserStory caso o Product Owner o aprove.

 - UserStories
   - As UserStories so serão divididas em tasks e implementadas no Sprint Backlog quando estiverem no estado de Ready no Product Backlog, vai ser selecionado X userstories por sprint de acordo com o seu peso e importância.
 
 - Avaliação de Impacto
   - Todas as mudanças propostas devem passar por uma avaliação de impacto para determinar os efeitos nos artefactos de configuração, nas funcionalidades do sistema, no cronograma e no orçamento.
   - A avaliação de impacto deve ser conduzida pela equipa de desenvolvimento e revista pela equipe de SCM ou controlo de qualidade.

 - Aprovação de Mudanças
   - As mudanças propostas devem ser aprovadas por  um Comitê de Controlo de Configuração (Configuration Control Board - CCB) ou por responsáveis autorizados, dependendo do nível de criticidade.
   - Somente após a aprovação formal a mudança pode ser implementada.

 - Priorização e Planejamento de Mudanças
   - Mudanças devem ser priorizadas com base em critérios estabelecidos (como urgência, criticidade e impacto) e integradas ao plano de projeto.
   - Alterações críticas podem receber prioridade para resolução imediata, enquanto mudanças não críticas podem ser agendadas para uma fase posterior.
 - Controlo de Versões
   - Cada mudança aprovada deve ser implementada em uma nova versão ou revisão do artefacto de configuração afetado.
   - O sistema de controlo de versão deve garantir que todas as alterações sejam corretamente rotuladas e que versões anteriores possam ser recuperadas, se necessário.

 - Teste e Verificação de Mudança
   - Após a implementação, cada mudança deve ser testada e verificada para garantir que foi aplicada corretamente e que não afeta adversamente outros componentes do sistema.
   - Os resultados do teste devem ser documentados, e qualquer problema detectado deve ser corrigido antes da integração final.
   - No campo de test case specification irá ser abordado as técnicas usadas para desenvolver os testes neste caso testes blackbox usando as técnicas aprendidas em contexto de sala de aula: Tabelas ECP e BVA.
   - No campo de test case outline será apresentado os testes a serem desenvolvidos pela equipa de desenvolvimento para as suas respetivas funcionalidades.
   - É necessário que os testes tenham uma taxa de cobertura de pelo menos 80% e que passem todos os testes desenvolvidos pelas técnicas para dar como aprovado a funcionalidade. 

 - Revisão e Auditoria de Mudanças
   - Devem ser realizadas auditorias periódicas para verificar se as mudanças foram implementadas conforme os procedimentos de controlo e se todos os registros estão completos.
   - Qualquer desvio detectado deve ser comunicado para correção e melhoria dos processos de controlo.
 - Comunicação das Mudanças
   - Todas as partes interessadas devem ser notificadas sobre as mudanças aprovadas e implementadas, especialmente se a mudança impactar diretamente o trabalho ou entregas de outras equipas.
   - A comunicação deve incluir detalhes sobre a alteração, seu impacto e o plano de implementação.

 - Planning Poker:
   - Será atribuido a cada user story e cada task o esforço associado e teremos como refência a seguinte estrutura, onde **1** representa baixa complexidade/esforço e **10** alta complexidade/esforço. 
   - Escala de Classificação de Tasks/User Stories:
      - 1-Muito Fácil
      - 2-Fácil
      - 3-Pouco Esforço
      - 4-Moderado
      - 5-Médio
      - 6-Esforço Considerável
      - 7-Complexo
      - 8-Muito Complexo
      - 9-Difícil
      - 10-Extremamente Difícil

---

### 3.3. Configuration Status and Accounting
A **_Configuration Status Accounting and Reporting_** tem como propósito adquirir e manter a informação da configuração do produto para garantir previsões mais precisas do produto.
 - Todos os artefactos de configuração (componentes de software, documentos, bibliotecas, etc.) devem ter uma identificação única.
 - Toda alteração em um artefacto de configuração deve ser documentada, incluindo:
    - O motivo da mudança.
    - A data da mudança.
    - A pessoa responsável pela modificação.
 - Qualquer mudança em artefactos de configuração críticos deve ser previamente autorizada.
 - Devem ser realizadas auditorias regulares das configurações para garantir que os artefactos de configuração estão corretos e atualizados, conforme o planejamento do projeto.
 - Cada build e release deve ser registrado, com informações detalhadas sobre as versões de cada item de configuração incluído.

### Com base no Repositório _GitLab_,
a estratégia de _Branching_ baseia-se em:
- Uma branch com base no componente ou numa feature a ser desenvolvida;
- Uma branch com base num change request (pedido dos stakeholders, clientes, product owner, correção a um requisito ou de um bug)
- _Development_ _Branch_ : _branch_ principal para desenvolvimento do projeto;
- Master _Branch_ : _branch_ que contém a versão estável do projeto / release.

Todas as mudanças devem seguir por um processo de _Merge Request_ para _review_ do código antes de darem _merge_ com o _branch_ principal.

Todos os _commits_ devem ser diários e conter mensagens concisas, fazendo referência a problemas relevantes ou funcionalidades.

Deve haver _sprints_ semanais com término no Domingo com vista a monitorizar o avanço do projeto.

## 4. SCRUM
### 4.1. SCRUM ROLES

#### 4.1.1 SCRUM Master
**Scrum Master** - é um novo papel de gestão introduzido pela _Scrum_:
> • É responsável por assegurar que o projecto é executado de acordo com as
 práticas, valores e regras do _Scrum_ e que progridem como planeado.

> • Interage com a equipa do projeto, bem como com o cliente e gestão durante o
 projeto.

> • É responsável por assegurar que qualquer impedimento é removido e alterado no
 processo para manter a equipa a trabalhar tão produtivamente quanto possível.

#### 4.1.2 PRODUCT OWNER
**Product Owner** - é oficialmente responsável pelo projeto, gestão, controlo do Product Backlog
> • É selecionado pelo Scrum Master, cliente e gestão

> • Toma as decisões finais das tarefas relacionadas com o Product Backlog

> • Participa na estimativa dos esforços de desenvolvimento para os backlog itens e torna os aspectos da Backlog em características a serem desenvolvidas

#### 4.1.3 SCRUM TEAM
**Scrum Team** - é a equipa de projeto que tem a autoridade para decidir as ações necessárias e organizar-se no sentido de alcançar os objetivos de cada Sprint, esta é constituída pelos membros: Francisco Barbosa, Leonardo Silva, Joaquim Matos e Rafael Silva.

> • Está envolvida, por exemplo, na estimativa de esforços, criação do Sprint Backlog e revisão do Product Backlog.

#### 4.1.4 CUSTOMER
**Costumer** - O cliente Cristovão Dinis Polido Sousa participa nas tarefas relacionadas com o **Product Backlog** para o sistema a ser desenvolvido ou melhorado.

#### 4.1.5 MANAGEMENT
**Management** - Participa na definição de objetivos e requisitos.

> • Por exemplo, a gestão está envolvida na seleção do **Product Owner**, medição do progresso e redução do _backlog_ com o **Scrum Master**.
