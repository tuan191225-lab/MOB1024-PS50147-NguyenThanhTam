
package model;

import java.io.Serializable;

public class Copy implements Serializable {

    private static final long serialVersionUID = 1L;

    private String copyId;
    private CopyStatus status;
    private String note;

    public Copy(String copyId, CopyStatus status, String note) {
        this.copyId = copyId;
        this.status = status;
        this.note = note;
    }

    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-12s | %s",
                copyId, status.getDisplayName(), note);
    }
}
