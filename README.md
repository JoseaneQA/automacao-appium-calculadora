# Automação Appium

Projeto Java para automação de testes mobile com Appium + Android.

## Objetivo

Este projeto serve como base para iniciar automações em dispositivos Android e emuladores usando Appium com Java e Maven.

## Tecnologias

- Java 11
- Maven
- Appium Java Client 9.5.0
- Selenium 4.34.0
- UIAutomator2
- Android Emulator

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- JDK 11
- Maven
- Android Studio / Android SDK
- Emulador Android configurado
- Appium Server
- Node.js e npm (se for usar Appium CLI)

## Instalar e iniciar o Appium

No terminal, execute:

```bash
appium
```

Ou, se estiver usando Appium via npx:

```bash
npx appium
```

O servidor normalmente fica disponível em:

```text
http://127.0.0.1:4723/
```

## Verificar o emulador conectado

```bash
adb devices
```

Se o emulador estiver ativo, deve aparecer algo como:

```text
List of devices attached
emulator-5554   device
```

## Verificar pacote da aplicação instalada

Para descobrir o package da app instalada no emulador, rode:

```bash
adb -s emulator-5554 shell pm list packages | findstr /i calculator
```

Exemplo de retorno esperado:

```text
package:com.google.android.calculator
```

## Executar o projeto

Na raiz do projeto, rode:

```bash
mvn clean compile
```

Para executar a classe principal:

```bash
mvn exec:java -Dexec.mainClass=br.joseane.automacaoAppium.CalculadoraTeste
```

Se você quiser apontar diretamente para uma app já instalada no emulador:

```bash
mvn exec:java -Dexec.mainClass=br.joseane.automacaoAppium.CalculadoraTeste -Dapp.package=com.google.android.calculator -Dapp.activity=com.android.calculator2.Calculator
```

Se você possuir um APK local:

```bash
mvn exec:java -Dexec.mainClass=br.joseane.automacaoAppium.CalculadoraTeste -Dapp.path=C:\caminho\para\app.apk
```

## Estrutura do projeto

```text
AutomacaoAppium/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── joseane/
│                   └── automacaoAppium/
│                       └── CalculadoraTeste.java
└── target/
```

## Exemplo de uso

A classe `CalculadoraTeste` serve como base para:

- abrir uma app Android
- localizar elementos visuais
- interagir com botões
- inserir valores
- validar resultados

## Dicas de troubleshooting

### 1. Erro de `LocationContext`

Se aparecer erro de `LocationContext`, garanta que a versão do Selenium seja compatível com o Appium Java Client.

### 2. Erro ao iniciar sessão do Appium

Verifique:

- Appium Server rodando
- emulador ativo
- package/activity corretos
- app instalada no dispositivo

### 3. App não encontrada

Use comando:

```bash
adb -s emulator-5554 shell pm list packages
```

Para localizar o nome correto do pacote da aplicação.

## Observações

Este projeto é uma base inicial e pode ser expandido com:

- Page Objects
- TestNG ou JUnit
- testes de fluxo real
- validação de textos e resultados
- screenshots em falhas

## Licença

Este projeto está disponível para uso educacional e pessoal.

## Autor

Joseane
