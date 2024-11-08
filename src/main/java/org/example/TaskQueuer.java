package org.example;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskQueuer {

    // This will mimic logging functionality
    private static void log(String message) {
        System.out.println(message);
    }

    // Singleton instance of TaskQueuer
    private static final TaskQueuer instance = new TaskQueuer();
    private final TaskQueueService service;

    // Constructor that decides which TaskQueueService to use (Mocked for simplicity)
    private TaskQueuer() {
        this.service = new TaskQueueService();
    }

    public static TaskQueuer getInstance() {
        return instance;
    }

    // Add task to queue (simplified)
    private void addTask(String queueName, String workerUrl, Map<String, String> paramMap, Object requestBody) {
        addDeferredTask(queueName, workerUrl, paramMap, requestBody, 0);
    }

    // Add deferred task (simulated version)
    private void addDeferredTask(String queueName, String workerUrl, Map<String, String> paramMap,
                                 Object requestBody, long countdownTime) {
        TaskWrapper task = new TaskWrapper(queueName, workerUrl, paramMap, requestBody);
        service.addDeferredTask(task, countdownTime);
    }

    // Example API method to schedule feedback session reminders (simplified)
    public void scheduleFeedbackSessionReminders(String courseId, String feedbackSessionName, String instructorId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("instructorId", instructorId);
        paramMap.put("feedbackSessionName", feedbackSessionName);
        paramMap.put("courseId", courseId);

        addTask("feedbackSessionRemindEmailQueue", "/feedbackSessionRemindEmailWorker", paramMap, null);
    }

    // Simplified version of TaskQueueService (No external dependencies)
    static class TaskQueueService {
        public void addDeferredTask(TaskWrapper task, long countdownTime) {
            log("Task added to queue: " + task);
        }
    }

    // Simplified version of TaskWrapper (Mocking the behavior of TaskWrapper)
    static class TaskWrapper {
        private String queueName;
        private String workerUrl;
        private Map<String, String> paramMap;
        private Object requestBody;

        public TaskWrapper(String queueName, String workerUrl, Map<String, String> paramMap, Object requestBody) {
            this.queueName = queueName;
            this.workerUrl = workerUrl;
            this.paramMap = paramMap;
            this.requestBody = requestBody;
        }

        @Override
        public String toString() {
            return "TaskWrapper{" +
                    "queueName='" + queueName + '\'' +
                    ", workerUrl='" + workerUrl + '\'' +
                    ", paramMap=" + paramMap +
                    ", requestBody=" + requestBody +
                    '}';
        }
    }
}
