package model;
public enum StudentType {
    REGULAR("Sinh viên chính quy"),
    PART_TIME("Sinh viên vừa học vừa làm"),
    INTERNATIONAL("Sinh viên quốc tế");

    private final String description;

    StudentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}