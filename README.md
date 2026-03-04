# Planejador de Viagens 🗺️🚗

Um aplicativo Android nativo desenvolvido em Kotlin para ajudar viajantes a estimarem os custos de suas próximas viagens com base na distância, consumo do veículo e preço do combustível.

## 🎯 Sobre o Projeto
Este projeto foi desenvolvido como um desafio do curso **Fundamentos do Android com Kotlin** da **Rocketseat**. O aplicativo consiste em um fluxo interativo ("wizard" de 3 etapas) que coleta informações do usuário e apresenta o cálculo estimado do custo final da viagem.

O grande diferencial deste projeto é a construção arquitetural focada em escalabilidade e nas melhores práticas recomendadas no mercado Android atual.

## ✨ Funcionalidades
* **Tela Inicial:** Boas vindas e início do fluxo de planejamento.
* **Etapas Dinâmicas:** 
  * Etapa 1: Inserção da Distância (Km)
  * Etapa 2: Inserção do Consumo Médio (Km/L)
  * Etapa 3: Inserção do Preço do Combustível (R$)
* **Validação de Inputs:** O aplicativo barra o avanço caso o usuário tente pular etapas ou inserir valores inválidos (State Validation).
* **Resultado e Cálculo:** Computação automática dos dados informados: `Custo = (Distância ÷ Consumo) × Preço`.
* **Reset de Fluxo:** Opção para planejar uma nova viagem recomeçando de um estado "limpo".

## 🛠️ Tecnologias e Arquitetura Aplicadas
Este projeto foge do básico (chumbar lógicas na `MainActivity`) e foca em uma arquitetura robusta:
* **Linguagem:** Kotlin
* **UI Toolkit:** XML Layouts com Material Design Components (`TextInputLayout`)
* **Navegação:** **Jetpack Navigation Component** (Tratamento de rotas e fragmentos dinâmicos gerenciados por um `NavHostFragment` via _DestinationListener_).
* **State Management:** **ViewModel + StateFlow** (Unidirectional Data Flow - UDF).
    * Uma **Shared ViewModel** (com escopo da Activity) centralizando o estado (`TravelUiState`) como a Única Fonte da Verdade para manter os dados seguros entre as transações de tela.
* **Separação de Preocupações (SoC):** As validações de formulário vivem em uma `data class` dedicada (`TravelUiState`), deixando a Interface apenas focada em mostrar dados e a ViewModel apenas para gerir atualizações via `update { }`.
* **Outros:** ViewBinding (para segurança no acesso às Views).

## 🚀 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/planejador-de-viagens.git
```
2. Abra o projeto no **Android Studio**.
3. Deixe o Gradle sincronizar/baixar as dependências e criar a pasta `build`.
4. Aperte "Run" ou faça o Build para o seu Dispositivo/Emulador Android via cabo USB/Wi-Fi.

## 🧑‍💻 Boas Práticas Extra
Durante o desenvolvimento, garantimos:
- **Clean State:** A navegação reinicia a memória e não apenas retorna à View, evitando bugs de _ghost state_.
- **Internacionalização Base:** Uso recomendado de `strings.xml` para formatações com `placeholders`, minimizando hardcodeds no arquivo.

## 📝 Licença
Desenvolvido por **Caio Vinícius** como parte do ecossistema Rocketseat. Sintam-se livres para explorar o código.
