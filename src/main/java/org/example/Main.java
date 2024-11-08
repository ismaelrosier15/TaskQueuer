package org.example;
public class Main {
    public static void main(String[] args) {
        // Get the TaskQueuer singleton instance
        TaskQueuer taskQueuer = TaskQueuer.getInstance();

        // Test scheduling a feedback session reminder
        taskQueuer.scheduleFeedbackSessionReminders("CS101", "Midterm Feedback", "instructor123");

        // You can add more similar tests based on other methods in TaskQueuer
    }
}
