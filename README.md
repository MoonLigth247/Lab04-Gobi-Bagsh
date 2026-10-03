# Лаборатори №4: Нэгжийн тестийн эхлэл — JUnit 5

**Оюутан:** [Н.Мөнхбаяр]
**Код:** [B242270058]
**Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба тест (2026)
**Репозитори:** https://github.com/MoonLigth247/Lab04-Gobi-Bagsh

## 1. Орчны хувилбар

java -version:
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment Temurin-17.0.20.1+1 (build 17.0.20.1+1)
OpenJDK 64-Bit Server VM Temurin-17.0.20.1+1 (build 17.0.20.1+1, mixed mode, sharing)

mvn -version:
Apache Maven 3.9.x
Maven home: /usr/share/maven
Java version: 17.0.20.1, vendor: Eclipse Adoptium
Java home: /usr/lib/jvm/temurin-17-jdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.x", arch: "amd64", family: "unix"

## 2. Төслийн бүтэц

lab04-junit/
├── pom.xml
├── README.md
├── .gitignore
├── results/
│   ├── mvn-test.txt
│   └── mvn-test-mutant.txt
└── src/
    ├── main/java/mn/edu/must/sqat/
    │   └── GradeCalculator.java
    └── test/java/mn/edu/must/sqat/
        └── GradeCalculatorTest.java

## 3. Тестийн тоо

Тестийн методын тоо: 14 (үүнээс 2 нь @ParameterizedTest).
results/mvn-test.txt доторх "Tests run": 27.
Surefire нь @CsvSource-ийн мөр бүрийг тусдаа тест гэж тоолдог тул 14 метод нь 27 тест болж ажилласан.

Тестийн методүүд:
1. ninetyFiveIsA — @Test — Ердийн утга 95 → A
2. eightyFiveIsB — @Test — Ердийн утга 85 → B
3. seventyFiveIsC — @Test — Ердийн утга 75 → C
4. sixtyFiveIsD — @Test — Ердийн утга 65 → D
5. thirtyIsF — @Test — Ердийн утга 30 → F
6. ninetyIsExactlyA — @Test — Хязгаар 90 → A
7. eightyNineNinetyNineIsB — @Test — Хязгаар 89.99 → B
8. sixtyIsExactlyD — @Test — Хязгаар 60 → D
9. fiftyNineNinetyNineIsF — @Test — Хязгаар 59.99 → F
10. zeroAndHundredAreValid — @Test — Хязгаар 0, 100
11. negativeScoreThrows — @Test — Буруу оролт -1
12. aboveHundredThrows — @Test — Буруу оролт 101
13. totalScoreValid — @Test — 10+40+10+10+30 = 100
14. totalScoreNegativeThrows — @Test — att = -5
15. totalScoreOverMaxThrows — @Test — lab = 41
16. letterGradeBoundaries — @ParameterizedTest — 8 мөр CSV
17. totalScoreBoundaries — @ParameterizedTest — 4 мөр CSV

## 4. Мутацийн үр дүн

GradeCalculator.letterGrade доторх score >= 90 нөхцөлийг санаатайгаар score > 90 болгож мутаци хийсэн. results/mvn-test-mutant.txt дотор:
Tests run: 27, Failures: 2, Errors: 0, Skipped: 0
BUILD FAILURE
Унасан тест: ninetyIsExactlyA — "90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)".

Мутаци нь хязгаарын утга (90) дээр тест бичсэнээр илэрсэн. Хэрэв зөвхөн 95, 85 гэх мэт ердийн утгуудыг тестэлсэн бол энэ алдаа илрэхгүй байх байсан. Дараа нь >= 90 болгож буцаасан ба results/mvn-test.txt дахин ногоон болсон.

## 5. Хамгийн сонирхолтой тест/алдаа

Хамгийн сонирхолтой нь 89.99 → B болон 90 → A гэсэн хязгаарын тестүүд байсан. Эдгээр нь >= ба >-ийн ялгааг тодорхой харуулж, мутаци тестээр илэрсэн. Мөн totalScore-д сөрөг утга болон дээд хязгаараас хэтэрсэн утгыг шалгах нь чухал байсан — учир нь эдгээр нь IllegalArgumentException шидэх ёстой.

## 6. Дүгнэлт

Энэ лабораторид JUnit 5-ын үндсэн ойлголтуудыг практикт хэрэгжүүлсэн: @Test, @DisplayName, assertThrows, @ParameterizedTest, @CsvSource. Хязгаарын утгууд дээр тест бичих нь яагаад чухал болохыг мутаци тестээр баталсан. Мөн Arrange-Act-Assert бүтцийг баримталж, тест бүрийг ойлгомжтой монгол нэрээр нэрлэсэн.

## 7. Тестийг ажиллуулах

mvn test 2>&1 | tee results/mvn-test.txt
Ногоон гаралт: Tests run: 27, Failures: 0, Errors: 0, Skipped: 0 + BUILD SUCCESS.

Мутаци хийсний дараа:
mvn test 2>&1 | tee results/mvn-test-mutant.txt
Улаан гаралт: Tests run: 27, Failures: 2, Errors: 0, Skipped: 0 + BUILD FAILURE.
Унасан тестүүд:
1. ninetyIsExactlyA — "90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)"
2. letterGradeBoundaries — @CsvSource доторх "90,A" мөр
## 8. Ашигласан хэрэгслүүд

Java: OpenJDK 17 (Temurin, Adoptium)
Maven: Apache Maven 3.9.x
JUnit: JUnit 5 (Jupiter) 5.10.2
Surefire: maven-surefire-plugin 3.2.5
OS: Debian 13 (Trixie)
