
package model;

public enum CopyStatus {
    GOOD("Còn tốt"),
    DAMAGED("Hư hỏng"),
    LOST("Mất");

    private String displayName;

    CopyStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
