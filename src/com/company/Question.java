
package com.company;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private String question;
    private String correctAnswer;
    private List<String> wrongAnswers;

    public Question(String question, String correctAnswer, String... wrongAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = new ArrayList<>(Arrays.asList(wrongAnswers));
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    @Override
    public String toString() {
        String string = String.format("%s;%s", question, correctAnswer);
        for (String wrongAnswer : wrongAnswers) {
            string += ";" + wrongAnswer;
        }
        return string;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject(this);
        return jsonObject.toString();
    }
}
