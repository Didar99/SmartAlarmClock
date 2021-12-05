package ne.iot.gulshatdiploma;

public class QuizLogic {
    String equation;
    long solution;
    int streak = 0;

    public void generateEquation() {
        long num1 = Math.round(Math.random() * 100);
        long num2 = Math.round(Math.random() * 100);

        String[] operators = {"+", "-"};
        String operator = operators[ (int) Math.round(Math.random())];

        if (operator.equals("+")) {
            solution = num1 + num2;
        } else {
            solution = num1 - num2;
        }

        equation = String.format("%s %s %s", num1, operator, num2);
    }

    public String answerIsCorrect(String userAnswer) {
        if (userAnswer.equals(String.valueOf(solution))) {
            streak++;
            return "Correct";
        } else {
            streak = 0;
            return "Incorrect";
        }
    }

    public String getEquation() {
        return equation;
    }

    public long getSolution() {
        return solution;
    }

    public int getStreak() {
        return streak;
    }


}