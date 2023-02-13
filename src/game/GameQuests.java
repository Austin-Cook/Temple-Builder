package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class GameQuests {
    ImagePrinter imagePrinter = new ImagePrinter();
    TextPrinter textPrinter = new TextPrinter();

    /*
    Player selects a character, weapon, and quest
     */
    public String[] startGame() throws InterruptedException, IOException {
        String characterNum = "";
        String weaponNum = "";
        String questNum = "";

        // Intro
        System.out.print("""
                
                
                Welcome to Temple Builder my fellow faithful disciple!
                In this game, you will have the chance to learn about the construction, ordinances, and stories of the Nauvoo Temple.
                Your Goal: Build the Nauvoo temple by succesfully answering 4 questions
                
                Select your character:
                    1) Joseph Smith
                    2) Emma Smith
                    3) Brigham Young
                    4) Eliza R. Snow
                    5) Heber C. Kimball
                    6) Orson Hyde
                    
                Type the number (1-6) of your chosen character: """);

        // Choose Character
        while(true) {
            Scanner scanner = new Scanner(System.in);
            characterNum = scanner.next();

            if(getCharacterName(characterNum) != null) {
                break;
            }

            System.out.print("\"" + characterNum + "\"" + " isn't a number between 1 and 6, try again: ");
        }

        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        imagePrinter.printImage(getCharacterName(characterNum));
        System.out.print("\nYou chose " + getCharacterName(characterNum) + "!");
        Thread.sleep(3000);


        // Choose Weapon
        System.out.print("\n\n\n\n");
        System.out.print("""
                Select your weapon:
                    1) Belt of Truth
                    2) Breastplate of Righteousness
                    3) Shoes of Peace
                    4) Shield of Faith
                    5) Helmet of Salvation
                    6) Sword of the Spirit
                
                Type the number (1-6) of your chosen weapon: """);

        while(true) {
            Scanner scanner = new Scanner(System.in);
            weaponNum = scanner.next();

            if(getWeaponName(weaponNum) != null) {
                break;
            }

            System.out.print("\"" + weaponNum + "\"" + " isn't a number between 1 and 6, try again: ");
        }

        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textPrinter.printText(getWeaponName(weaponNum));
        System.out.print("You chose " + getWeaponName(weaponNum) + "!");
        Thread.sleep(3000);


        // Choose Quest
        System.out.print("\n\n\n\n");
        System.out.print("""
                Select your quest:
                    1) Divine Design - Constructing the House of the Lord
                    2) Ordinance Odyssey - Diving Revelation for Temple Ordinances and Covenants
                    3) Discovery Dash - Spiritual Lessons and Insights
                
                Type the number (1-3) of your chosen quest: """);

        while(true) {
            Scanner scanner = new Scanner(System.in);
            questNum = scanner.next();

            if(getQuestName(questNum) != null) {
                break;
            }

            System.out.print("\"" + questNum + "\"" + " isn't a number between 1 and 3, try again: ");
        }

        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        textPrinter.printText(getQuestName(questNum));
        System.out.print("\n");

        return new String[] {characterNum, weaponNum, questNum};
    }

    /*
    Gameplay where the player answers 4 questions
     */
    public void startQuest(String characterNum, String weaponNum, String questNum) throws InterruptedException {
        System.out.println("Welcome to " + getQuestName(questNum) + "!\n" +
                "You have 4 questions to answer, and if you do so correctly, you will build a temple!\n");

        // Get the questions from the chosen quest
        List<Question> questions = getQuestions(questNum);

        // Randomize questions
        Collections.shuffle(questions);

        // Question loop - 4 questions - use indexes 0-3
        for(int questionNum = 0; questionNum < 4; questionNum++) {
            Question currQuestion = questions.get(questionNum);

            // Story
            System.out.println("\n" + currQuestion.getStory() + "\n");

            // Answers
            System.out.println("Question #" + (questionNum + 1));
            System.out.println(currQuestion.getAnswers() + "\n");
            System.out.println("Type the letter of the correct answer: ");

            // Get users answer
            String userInput = "";
            while(true) {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.next().toLowerCase();

                if(userInput.equals("a") || userInput.equals("b") || userInput.equals("c") || userInput.equals("d")) {
                    break;
                }

                System.out.println("\"" + userInput + "\"" + " isn't a letter between a and d, try again: ");
            }

            if(userInput.equals(currQuestion.getCorrectAnswerNum())) {
                // Last turn - player won
                if(questionNum >= 3) {
                    winGame();
                }
            } else {
                loseGame(questionNum);
                break;
            }
        }
    }

    private String getCharacterName(String characterNum) {
        if(characterNum.equals("1")) {
            return "Joseph Smith";
        } else if(characterNum.equals("2")) {
            return "Emma Smith";
        } else if(characterNum.equals("3")) {
            return "Brigham Young";
        } else if(characterNum.equals("4")) {
            return "Eliza R. Snow";
        } else if(characterNum.equals("5")) {
            return "Heber C. Kimball";
        } else if(characterNum.equals("6")) {
            return "Orson Hyde";
        } else {
            return null;
        }
    }

    private String getWeaponName(String weaponNum) {
        if(weaponNum.equals("1")) {
            return "Belt of Truth";
        } else if(weaponNum.equals("2")) {
            return "Breastplate of Righteousness";
        } else if(weaponNum.equals("3")) {
            return "Shoes of Peace";
        } else if(weaponNum.equals("4")) {
            return "Shield of Faith";
        } else if(weaponNum.equals("5")) {
            return "Helmet of Salvation";
        } else if(weaponNum.equals("6")) {
            return "Sword of the Spirit";
        } else {
            return null;
        }
    }

    private String getQuestName(String questNum) {
        if(questNum.equals("1")) {
            return "Divine Design";
        } else if(questNum.equals("2")) {
            return "Ordinance Odyssey";
        } else if(questNum.equals("3")) {
            return "Discovery Dash";
        } else {
            return null;
        }
    }

    private List<Question> getQuestions(String questNum) {
        List<Question> questions = new ArrayList<>();

        if(questNum.equals("1")) {
            // create the objects
            questions.add(new Question("Quest1 Story1: answer is a", "Question1", "a)\nb)\nc)\nd)", "a"));
            questions.add(new Question("Quest1 Story2: answer is b", "Question2", "a)\nb)\nc)\nd)", "b"));
            questions.add(new Question("Quest1 Story3: answer is c", "Question3", "a)\nb)\nc)\nd)", "c"));
            questions.add(new Question("Quest1 Story4: answer is d", "Question4", "a)\nb)\nc)\nd)", "d"));
        } else if(questNum.equals("2")) {
            // create the objects
            questions.add(new Question("Quest2 Story1: answer is a", "Question1", "a)\nb)\nc)\nd)", "a"));
            questions.add(new Question("Quest2 Story2: answer is b", "Question2", "a)\nb)\nc)\nd)", "b"));
            questions.add(new Question("Quest2 Story3: answer is c", "Question3", "a)\nb)\nc)\nd)", "c"));
            questions.add(new Question("Quest2 Story4: answer is d", "Question4", "a)\nb)\nc)\nd)", "d"));
        } else if(questNum.equals("3")) {
            // create the objects
            questions.add(new Question("Quest3 Story1: answer is a", "Question1", "a)\nb)\nc)\nd)", "a"));
            questions.add(new Question("Quest3 Story2: answer is b", "Question2", "a)\nb)\nc)\nd)", "b"));
            questions.add(new Question("Quest3 Story3: answer is c", "Question3", "a)\nb)\nc)\nd)", "c"));
            questions.add(new Question("Quest3 Story4: answer is d", "Question4", "a)\nb)\nc)\nd)", "d"));
        } else {
            System.err.println("Error: Invalid input for questNum: " + questNum);
            System.exit(-1);
        }

        return questions;
    }

    private void winGame() throws InterruptedException {
        System.out.println("Congratulations! You successfully built the Nauvoo Temple!");
        printTemple(4);
    }

    private void loseGame(int numAnswersCorrect) throws InterruptedException {
        if(numAnswersCorrect == 0) {
            System.out.println("Sorry, you didn't answer any questions correctly - the temple remains unbuilt");
        } else {
            System.out.println("Sorry, you only answered " + numAnswersCorrect + "/4 correctly - the temple is partially built");
            printTemple(numAnswersCorrect);
        }
    }

    private void printTemple(int numAnswersCorrect) throws InterruptedException {
        System.out.println("Building the temple: ");

        if(numAnswersCorrect == 4) {
            imagePrinter.printImage("temple top");
            Thread.sleep(1000);
        }
        if(numAnswersCorrect == 4 || numAnswersCorrect == 3) {
            imagePrinter.printImage("temple middle-top");
            Thread.sleep(1000);
        }
        if(numAnswersCorrect == 4 || numAnswersCorrect == 3 || numAnswersCorrect == 2) {
            imagePrinter.printImage("temple middle-bottom");
            Thread.sleep(1000);
        }
        if(numAnswersCorrect == 4 || numAnswersCorrect == 3 || numAnswersCorrect == 2 || numAnswersCorrect == 1) {
            imagePrinter.printImage("temple bottom");
            Thread.sleep(1000);
        }
    }
}
