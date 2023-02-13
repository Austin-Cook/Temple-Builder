package game;

public class Question {
    String story;
    String question;
    String answers;
    String correctAnswerNum;

    public Question(String story, String question, String answers, String correctAnswerNum) {
        this.story = story;
        this.question = question;
        this.answers = answers;
        this.correctAnswerNum = correctAnswerNum;
    }

    public String getStory() {
        return story;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers() {
        return answers;
    }

    public String getCorrectAnswerNum() {
        return correctAnswerNum;
    }
}
