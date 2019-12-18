package task;

import java.util.Properties;

public class FeedbackPopupObject {

    private String fullName;

    private String email;

    private String phoneNumber;

    private String[] methodFeedback;

    private String[] subject;

    private String[] service;

    private String message;

    public FeedbackPopupObject(Properties testProperties) {
        this.fullName = testProperties.getProperty("fullname");
        this.email = testProperties.getProperty("email");
        this.phoneNumber = testProperties.getProperty("phoneNumber");
        this.methodFeedback = testProperties.getProperty("methodFeedback").split(",");
        this.subject = testProperties.getProperty("subject").split(",");
        this.service = testProperties.getProperty("service").split(",");
        this.message = testProperties.getProperty("message");
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String[] getMethodFeedback() {
        return methodFeedback;
    }

    public String[] getSubject() {
        return subject;
    }

    public String[] getService() {
        return service;
    }

    public String getMessage() {
        return message;
    }

}
