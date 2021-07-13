[![code style: prettier](https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square)](https://github.com/prettier/prettier)
<br />

<p align="center">

  <h1 align="center">TeraWare</h1>

  <p align="center">    
	<b>TeraWare</b> è un progetto realizzato durante il corso di <i>Tecnologie Software per il Web</i> tenuto dalla prof. Rita Francse durante l'a.a. 2020/2021.
    <br />
	<br />
    <a href="https://github.com/xrenegade100/TeraWare/issues">Report Bug</a>
    ·
    <a href="https://github.com/xrenegade100/TeraWare/pulls">Pull Request</a>
	<br />
	<br />
    <img alt="GitHub issues" src="https://img.shields.io/github/issues/xrenegade100/TeraWare">
    <img alt="GitHub pull requests" src="https://img.shields.io/github/issues-pr/xrenegade100/TeraWare">
    <img alt="GitHub" src="https://img.shields.io/github/license/xrenegade100/TeraWare?1">
  </p>
</p>

<!-- TABLE OF CONTENTS -->

## Table of Contents

- [Descrizione](#descrizione)
  - [Software](#software)
- [Come iniziare](#come-iniziare)
  - [Prerequisiti](#prerequisiti)
  - [Esecuzione](#esecuzione)
- [Per contribuire](#per-contribuire)
- [Licenza](#licenza)

## Descrizione

TeraWare è un sito web di e-commerce specializzato nella vendita di prodotti di informatica per il gaming e l'ufficio. Il frontend è realizzato interamente senza l'utilizzo di template o librerie. Per il backend con Java sono utilizzate (al momento) solo le librerie Gson di Google per la gestione di dati in formato JSON e JDBC come driver per la connessione al database MySQL.

### Software

- Java EE
- MySQL
- Sass
- Tomcat 9.0

## Come iniziare

Per far partire il sito sul tuo computer assicurati di installare tutti i software della sezione seguente e poi segui i passaggi per lanciare il sito.

### Prerequisiti

- [Compilatore Sass](https://marketplace.visualstudio.com/items?itemName=ritwickdey.live-sass)
- MySQL
- Eclipse for Java EE
- Tomcat

### Esecuzione

1. Clona la repo

```bash
$ git clone https://github.com/xrenegade100/TeraWare
```

2. Apri Eclipse for Java EE
   - `File` > `Import` > `Existing projects into workspace`
   - Seleziona la cartella che contiene la repo clonata
   - Clicca `Finish`
3. Clicca con il tastro destro sul progetto nel _Project Explorer_ di Eclipse
   1. Clicca `Configure Build Path`
   2. Seleziona `Classpath`
   3. Sulla sinistra clicca `Add Library`
   4. Scegli `JRE System Library` e aggiungi un'istanza di Java
   5. Ripeti i passaggi iii. e iv. scegliendo `Server Runtime` e aggiungi un server Tomcat
   6. Sulla sinistra clicca `Add External JARs` e naviga in `/TeraWare/site/WebContent/WEB-INF/lib/`, seleziona tutti i file JAR e aggiungili.
4. Compila i file Sass (è preferibile tramite Visual Studio Code con [Live Sass Compiler](https://marketplace.visualstudio.com/items?itemName=ritwickdey.live-sass), aprendo la cartella `site` che si trova nella root della repo e poi cliccando su `Watch Sass`) ![Tutorial](https://github.com/ritwickdey/vscode-live-sass-compiler/raw/master/images/Screenshot/statusbar.jpg)
5. In Eclipse naviga in `WebContent` > `install`, clicca con il tasto destro su `install.jsp` > `Run As` > `Run On Server` e segui i passaggi per l'installazione del sito.

## Per contribuire

Ogni Pull Request è ben accetta, prima però, apri un [issue](https://github.com/xrenegade100/TeraWare/issues) per discutere dei cambiamenti che vorresti fare :grin:

## Licenza

[MIT](LICENSE)
