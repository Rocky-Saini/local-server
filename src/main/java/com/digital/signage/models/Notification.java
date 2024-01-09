package com.digital.signage.models;

public class Notification {
    private String title;
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final class Builder {
        private String title;
        private String message;

        private Builder() {
        }

        public static Builder aNotification() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Notification build() {
            Notification notification = new Notification();
            notification.setTitle(title);
            notification.setMessage(message);
            return notification;
        }
    }
}
