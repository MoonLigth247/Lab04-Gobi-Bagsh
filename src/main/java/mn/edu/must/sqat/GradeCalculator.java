package mn.edu.must.sqat;

public class GradeCalculator {

    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be 0-100: " + score);
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        checkRange(att, 0, 10, "att");
        checkRange(lab, 0, 40, "lab");
        checkRange(quiz1, 0, 10, "quiz1");
        checkRange(quiz2, 0, 10, "quiz2");
        checkRange(exam, 0, 30, "exam");
        return att + lab + quiz1 + quiz2 + exam;
    }

    private void checkRange(double value, double min, double max, String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " out of range: " + value);
        }
    }
}
