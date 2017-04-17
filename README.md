# football-player-generator

Generates accurate football players based on a highly extendible template system, can also progress players ability through their age

Usage
-------

```java
new PlayerGenerator().generatePlayers(int numToGenerate);
```

will create a population of of players between the ages of 15-40, using an alpha of 2 and beta 4 to calculate the age distribution

![Image of Beta distribution](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Beta_distribution_pdf.svg/325px-Beta_distribution_pdf.svg.png)

Alternatively use the following method call to specify different parameters:

```java
new PlayerGenerator().generatePlayers(int numToGenerate, BetaDistribution beta, double minAge, double maxAge);
```

Where the beta distribution can be simply created using:

```java
BetaDistribution beta = new BetaDistribution(double alpha, double beta);
```
