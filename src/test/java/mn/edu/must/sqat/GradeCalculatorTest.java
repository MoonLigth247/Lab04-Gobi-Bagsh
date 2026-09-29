package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator calc = new GradeCalculator();

    @Test
    @DisplayName("95 оноо A дүн байх ёстой")
    void ninetyFiveIsA() {
        assertEquals("A", calc.letterGrade(95.0));
    }

    @Test
    @DisplayName("85 оноо B дүн байх ёстой")
    void eightyFiveIsB() {
        assertEquals("B", calc.letterGrade(85.0));
    }

    @Test
    @DisplayName("75 оноо C дүн байх ёстой")
    void seventyFiveIsC() {
        assertEquals("C", calc.letterGrade(75.0));
    }

    @Test
    @DisplayName("65 оноо D дүн байх ёстой")
    void sixtyFiveIsD() {
        assertEquals("D", calc.letterGrade(65.0));
    }

    @Test
    @DisplayName("30 оноо F дүн байх ёстой")
    void thirtyIsF() {
        assertEquals("F", calc.letterGrade(30.0));
    }

 
    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        assertEquals("A", calc.letterGrade(90.0));
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (хязгаарын тохиолдол)")
    void eightyNineNinetyNineIsB() {
        assertEquals("B", calc.letterGrade(89.99));
    }

    @Test
    @DisplayName("60 оноо яг D дүн байх ёстой (хязгаарын тохиолдол)")
    void sixtyIsExactlyD() {
        assertEquals("D", calc.letterGrade(60.0));
    }

    @Test
    @DisplayName("59.99 оноо F дүн байх ёстой (хязгаарын тохиолдол)")
    void fiftyNineNinetyNineIsF() {
        assertEquals("F", calc.letterGrade(59.99));
    }

    @Test
    @DisplayName("0 ба 100 хязгаарын утгууд зөв ажиллах ёстой")
    void zeroAndHundredAreValid() {
        assertEquals("F", calc.letterGrade(0.0));
        assertEquals("A", calc.letterGrade(100.0));
    }

    @Test
    @DisplayName("Сөрөг оноо IllegalArgumentException шидэх ёстой")
    void negativeScoreThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    @DisplayName("100-аас дээш оноо IllegalArgumentException шидэх ёстой")
    void aboveHundredThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    @DisplayName("totalScore хязгаар дотор зөв нийлбэр гаргах ёстой (10+40+10+10+30=100)")
    void totalScoreValid() {
        assertEquals(100.0, calc.totalScore(10, 40, 10, 10, 30), 0.001);
    }

    @Test
    @DisplayName("totalScore сөрөг утгад exception шидэх ёстой")
    void totalScoreNegativeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("totalScore хэтэрсэн утгад exception шидэх ёстой (lab=41)")
    void totalScoreOverMaxThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30));
    }

    @ParameterizedTest
    @CsvSource({"95,A", "90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F"})
    @DisplayName("letterGrade хязгаарын утгууд (parameterized)")
    void letterGradeBoundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }

    @ParameterizedTest
    @CsvSource({
        "10,40,10,10,30,100",
        "0,0,0,0,0,0",
        "5,20,5,5,15,50",
        "10,40,10,10,0,70"
    })
    @DisplayName("totalScore хязгаарын утгууд (parameterized)")
    void totalScoreBoundaries(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, calc.totalScore(att, lab, q1, q2, exam), 0.001);
    }
}
