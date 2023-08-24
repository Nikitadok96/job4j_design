package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    public static final String SEPARATOR = System.lineSeparator();
    private static List<Comment> comments = new ArrayList<>();
    public static final int COUNT = 50;
    private  List<String> phrases;
    private UserGenerator userGenerator;
    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            StringJoiner stringJoiner = new StringJoiner(SEPARATOR);
            stringJoiner.add(phrases.get(random.nextInt(phrases.size())));
            stringJoiner.add(phrases.get(random.nextInt(phrases.size())));
            stringJoiner.add(phrases.get(random.nextInt(phrases.size())));
            comments.add(new Comment(stringJoiner.toString(),
                    userGenerator.randomUser()));
        }
    }
}
