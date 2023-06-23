package ru.job4j.io;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 26;
        short admissionYear = 2023;
        int solvedTasks = 450;
        float countTaskDay = 2.09f;
        double completedEx = 28.18;
        boolean isCompleted = false;
        char grade = 'D';
        LOG.debug("User info: age {}. gets grades {}", age, grade);
        LOG.debug("The year of admission to the course {}, during this time it was decided {}",
                admissionYear, solvedTasks);
        LOG.debug("Average number of tasks per day {}", countTaskDay);
        LOG.debug("Course completed: {}, course completed on {}", isCompleted, completedEx);
    }
}
