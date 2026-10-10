# Kontoappen — Examination 1, Java grunder

En Java-konsolapplikation för att skapa och hantera bankkonton. Projektet använder inkapsling, en registerklass som ansvarar för skapandet (Factory), listor, metoder, felhantering och arv via `SavingsAccount`.

## Funktioner

- Skapa vanligt konto och sparkonto.
- Lista konton med saldo och kontotyp.
- Sätta in pengar och ta ut pengar.
- Stoppa uttag som är större än saldot.
- Beräkna ränta för sparkonton.
- Hantera ogiltig menyinmatning och numeriska värden utan att programmet kraschar.
- Söka konto via ägarens namn (skiftlägesokänsligt).

## Krav

- JDK 17 eller senare (koden använder switch-uttryck och pattern matching för `instanceof`).
- IntelliJ IDEA, VS Code eller terminal.

## Köra programmet

Från projektroten:

```bash
javac -d out src/*.java
java -cp out Main
```

I IntelliJ: öppna projektmappen och kör `Main.java`.

## Struktur

- `src/Account.java` — kontots data och regler för insättning/uttag.
- `src/AccountRegister.java` — listan med konton, sökning och Factory-metoder.
- `src/SavingsAccount.java` — subklass med ränta och överskuggad utskrift.
- `src/Main.java` — meny och inmatning från användaren.

## 1. Datasäkerhet och inkapsling

I `Account` är `owner` och `balance` privata, så kod utanför klassen kan inte ändra saldot direkt. I stället använder resten av programmet getters och metoderna `deposit` och `withdraw`, som kontrollerar beloppet och stoppar uttag som skulle ge ett negativt saldo. Utan inkapsling skulle andra delar av programmet kunna ändra saldot utan validering och till exempel skapa ett ogiltigt negativt saldo.

## 2. Skapande-mönster (Factory)

Konton skapas via `AccountRegister.createAccount` eller `createSavingsAccount`, inte direkt i menylogiken. Registret skapar objektet och lägger det i listan på samma ställe, vilket minskar risken att ett konto skapas men glöms bort i registret. Om reglerna för skapande ändras kan de också hanteras centralt i registret.

## 3. Flöde: insättning

Användaren väljer menyval 3 och anger ägarens namn. `Main.changeBalance(true)` frågar registret via `findAccountByOwner` efter rätt konto. När kontot hittats läser metoden in beloppet och anropar `Account.deposit(amount)`, som kontrollerar att beloppet är ändligt och större än noll innan saldot ökas. Därefter visar `Main` ett bekräftelsemeddelande.

## 4. Reflektion över problemlösning och AI

Jag använde kursens kravspecifikation som checklista och delade upp lösningen efter ansvar: kontoregler i `Account`, samling och skapande i `AccountRegister`, och interaktion i `Main`. Ett problem som behövde förebyggas var att `Scanner`-inmatning kunde krascha vid text där ett tal förväntades; därför läser programmet in rader som text och försöker konvertera dem med validering. Jag använde AI som bollplank för att granska möjliga fel, men jag behöver själv provköra lösningen, förstå varje metod och justera eventuella problem innan inlämning. Jag ska inte lämna in påståenden om tester som jag inte har utfört.

## Muntlig redovisning — plan för 2–3 delar

1. **`Account.java` — `withdraw(double amount)`:** förklara valideringen, varför saldot kontrolleras före subtraktionen och varför metoden returnerar `false` när uttaget nekas.
2. **`AccountRegister.java` — `createAccount(...)`:** visa hur objektet skapas och läggs till i listan. Förklara varför `Main` inte själv skapar kontot.
3. **`SavingsAccount.java` — arv:** visa `extends Account`, `super(...)`, `applyInterest()` och `@Override printInfo()`. Förklara hur sparkontot återanvänder basklassens saldo och insättningsregler.

## Videolänk

Lägg in den fungerande delningslänken till din Teams-inspelning här efter att du har spelat in och kontrollerat behörigheten:

`[LÄGG IN DIN TEAMS/ONEDRIVE-LÄNK HÄR]`

## Inlämningskontroll

- [ ] Programmet kompilerar och startar i din miljö.
- [ ] Testa minst två konton, insättning, uttag som lyckas och uttag som nekas.
- [ ] Testa text vid menyval och ogiltiga belopp.
- [ ] Skapa sparkonto och beräkna ränta; kontrollera att det syns i listan.
- [ ] Publicera detta i ett eget **publikt** GitHub-repo.
- [ ] Gör minst fem meningsfulla commits under arbetet.
- [ ] Ersätt videoplatshållaren med den verkliga länken och kontrollera att läraren kan öppna den.
- [ ] Lämna in repo-URL i Moodle.
