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
            System.out.println("Question #" + (questionNum + 1));
            System.out.println("\n" + currQuestion.getStory() + "\n");

            // Answers
            System.out.println(currQuestion.getQuestion());
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
            questions.add(new Question("Joseph Smith received a vision of the Nauvoo Temple design and layout. However, he passed the ideas \non to the temple's architect, William Weeks who took charge of putting the ideas on paper. Its \narchitecture followed themes of classical Greek and Roman architecture, using columns, \nand pediments while also following current American styles including timber framing and simple spaces.", "Who was the architect of the Nauvoo temple?", "a) Brigham Young\nb) William Clayton\nc) William Weeks\nd) Willard Richards", "c"));
            questions.add(new Question("Many members made large sacrifices during the construction of the temple. Rebecca Swain Williams, a \nfaithful member spent countless hours assisting in any way she could. She worked as a seamstress and \nmade clothes for the workers, and also provided food and lodging.", "In what way did  Rebecca Swain contribute to the construction of the Nauvoo temple?", "a) Carving the Angel Moroni\nb) Making clothes for the workers\nc) Donating ten-thousand dollars to the construction\nd) Creating the temple's blueprints", "b"));
            questions.add(new Question("Building the temple required a lot of different tools. On July 8, 1820, Joseph sent out a request to \nthe members looking for moulding planes. This is a tool used to shape wood into specific forms. Members \nwho donated received a credit on their tithing.", "What is a moulding plane?", "a) A surface used to make pottery\nb) An clay replica of the Salt Lake Temple\nc) A terrain the Saints walked across on their way to Utah\nd) A tool used to shape wood into specific forms", "d"));
            questions.add(new Question("Joseph Smith, speaking of the importance of the construction of the Nauvoo temple, stated \"Believing \nthe time has now come, when it is necessary to erect a house of prayer, a house of order, a house for \nthe worship of our God, where the ordinances can be attended to agreeably to His divine \nwill. . . . it behooves the Saints to weigh the importance of these things, in their minds, [and] . . . \nresolve to do all they can, and feel themselves as much interested as though the whole labor depended on themselves alone.\"", "How important did Joseph say it was for the Saints to be engaged in building the house of the Lord?", "a) It would be a bonus\nb) Important only if the Saints had time\nc) Recommended\nd) Necessary", "d"));
            questions.add(new Question("As the Nauvoo temple was not a small project, it was going to cost a lot of money to build. At first, \nfinancial contributions were given directly to the temple committee, but Joseph Smith later appointed \nWillard Richards to serve as the recorder for temple funds. The first contributor was John \nSanders, a convert from Scotland who gave five dollars.", "Who/where did members originally give money to be used in the construction of the temple before Willard Richards was appointed to recieve contributions?", "a) The church Auditing Department\nb) The temple committee\nc) Hyrum Smith\nd) A donation box near the temple", "b"));
            questions.add(new Question("Most temples of the Church of Jesus Christ have a golden Angel Moroni statue on top. The Nauvoo temple \nbegan this tradition. However, instead of the Moroni we are used to, it contained a weather vane \nfeaturing a flying Angel Moroni with a Book of Mormon in one hand and a trumpet in the other.", "What was the Nauvoo Temple's Angel Moroni holding?", "a) A Bible\nb) The Doctrine and Covenants\nc) The Book of Mormon\nd) The Urim and Thumim", "c"));
        }
        else if(questNum.equals("2")) {
            // create the objects
            questions.add(new Question("In D&C 124, Joseph Smith received a revelation to build the Nauvoo temple. In this revelation, the Lord \ninstructed the Saints to use the \"precious things of the earth; and build a house for my name.\" \nAdditionally, he revealed that one purpose for the temple is to \"restore the fullness of the Priesthood.\"", "What can we learn from God's commandment to build the temple out of the \"precious things of the earth\"?", "a) God cares about the sacrifices we make as we serve Him\nb) We should give our best to God every day\nc) The temple is a very important building\nd) All of the above (CHOOSE THIS ONE)", "d"));
            questions.add(new Question("The House of the Lord is a house of order. In D&C 127, the Lord commanded a record to be kept of the \nordinances performed in the temple. He explained that they will \"be held in remembrance from generation \nto generation\".", "What did the Lord command to keep a record of?", "a) Hours spent on temple construction\nb) Ordinances performed in the temple\nc) Hours spent shaking up hell through righteous study of the scriptures\nd) Number of meals served to temple workers", "b"));
            questions.add(new Question("In D&C 132 Joseph Smith was given a revelaition regaurding eternal marriage and joining families together \bforever. This was one revelation that Latter Day Saints treasure becuase of its profound impact on \nfamily relationships. In the short time that the Nauvoo temple was in operatoin, temple sealings \nwere performed within.", "What did the Lord reveal in D&C 132?", "a) The need to organize a primary\nb) A commandment to build the Nauvoo temple\nc) Eternal marriage\nd) The Law of Consecration", "c"));
            questions.add(new Question("Though baptisms for the dead had previously been performed, in D&C 124, the Lord revealed that a baptismal \nfont should be constructed as part of the temple. He also revealed that, with the exception of times \nof poverty where a temple is not yet built, the ordinance of baptisms for the dead should be performed \nin the temple.", "What ordinance did the Lord reveal belongs in the temple?", "a) Baptisms for the Dead\nb) Priesthood blessings\nc) The Sacrament\nd) Baby blessings", "a"));
            questions.add(new Question("On January 8, 1841, Joseph Smith made the announcement of the construction of the Nauvoo temple. Speaking of \nits intended function, he explained that  it would be constructed \"as to enable all the functions \nof the Priesthood to be duly exercised, and where instructions from the Most High will be received.\" \nTruly it would be a place where God would give great power to the hearts of those who entered within.", "Which functions of the Priesthood did Joseph Smith teach would be exercised within the temple?", "a) Those taught during Moses's dispensation\nb) Only new functions revealed in the last dispensation\nc) The top five most important ones\nd) All functions of the Priesthood", "d"));
            questions.add(new Question("In the years leading up to the dedication of the Nauvoo temple, the Lord had revealed a several ordinances to \nbe performed within His holy temples. These ordinances include Baptisms for the Dead, the Initiatory, \nthe Endowment, and Sealings. Each of these ordinances was performed in the Nauvoo temple during its \nshort time of operation.", "Which ordinances were performed in the Nauvoo temple?", "a) Initiatory\nb) Baptisms for the Dead\nc) Endowment\nd) All of the above (CHOOSE THIS ONE)", "d"));
        } else if(questNum.equals("3")) {
            // create the objects
            questions.add(new Question("Many church members who received the Endowment in the Nauvoo Temple testified of it's power in their lives \nas they embarked on the strenuous journey to Salt Lake. Of this, Sarah Pea Rich, an early member of \nthe church, said \"If it had not been for the faith and knowledge that was bestowed upon us in \nthat temple [Nauvoo] by the influence and help of the spirit of the Lord, our journey would have \nbeen like one taking a leap in the dark, to start out on such a journey in the winter as it was, \nand in our state of poverty, it would seem like walking into the jaws of death. But we had faith \nin our Heavenly Father, and we put our trust in Him, feeling that we were His chosen people and \nhad embraced His gospel; and instead of sorrow we felt to rejoice that the day of our deliverance \nhad come\". She, like many others, saw the value in the perspective gained from temple ordinances \nthat helps us push forward through difficult times.", "What did Sarah testify gave her strength as she moved forward and moved to Utah?", "a) John Taylor's special scone recipe\nb) Her favorite team of oxen\nc) Her two strong legs and determination\nd) The knowledge she received in the temple", "d"));
            questions.add(new Question("Erastus Snow, who would later become an Apostle, testified of the blessings that came from building the Nauvoo \ntemple. He wrote, \"The Spirit, Power, and Wisdom of God reigned continually in the Temple and \nall felt satisfied that during the two months we occupied it in the endowments of the Saints, \nwe were amply paid for all our labors in building it\"", "How did Erastus say the Saints were paid for their labours in building the temple?", "a) Amply\nb) With a lot of money\nc) With clothes and a hot meal\nd) five dollars per day", "a"));
            questions.add(new Question("Brigham Young, who planned to leave Nauvoo on February 4, 1846, saw a large group of people outside the Nauvoo \ntemple waiting to receive the endowment. He decided to stay another two weeks to ensure the \nadministration of the endowment to these faithful Saints, and in total, 6,000 people were able to \nreceive the endowment before leaving.", "How many Saints were able to receive the Endowment before leaving Nauvoo?", "a) 17\nb) 1000\nc) 6000\nd) 20,000", "c"));
            questions.add(new Question("Many of the workers who helped in the construction of the Nauvoo temple didn't receive pay for their work. Many \nfreely gave of their time, talents, and so much of what they had to help construct it. Their faith \nand dedication is inspiring and shows what can be accomplished as we consecrate ourselves \nto God's purposes.", "The sacrifices that many of the workers made while building the Nauvoo temple is an example of what?", "a) Consecration\nb) Love\nc) Faith\nd) All of the above (CHOOSE THIS)", "d"));
            questions.add(new Question("One great blessing of the Nauvoo temple is how it united members of the Church from all walks of life. Men worked \nin construction, women provided support, food and clothing, and children assisted in a variety \nof ways including gathering materials and bringing food to the workers. This served to unify \nthe poeple and help prepare their hearts to live as Zion.", "Who mainly contributed to the construction of the Nauvoo temple?", "a) Men\nb) Women\nc) Children\nd) All of the above (CHOOSE THIS)", "d"));
            questions.add(new Question("Mary Fielding Smith, whose husband, Hyrum Smith was previously killed by the mob at Carthage Jail, remained \nfaithful in the Church. In Nauvoo, she contributed significantly to the temple including collecting \npennies for purchasing glass for the temple. Her faith and dedication despite her life altering \nchallenges is inspiring to all.", "What is one lesson we can learn from Mary's story?", "a) We can find joy and meaning even during trying times\nb) We all can find unique ways to contribute to God's purposes\nc) As we press forward in difficult times, we can be a strength to others\nd) All of the above (CHOOSE THIS)", "d"));
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
