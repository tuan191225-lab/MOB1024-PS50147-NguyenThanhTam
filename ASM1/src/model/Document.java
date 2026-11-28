
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentId;
    private String title;
    private String author;
    private String category;
    private List<Copy> copies;

    public Document(String documentId, String title,
            String author, String category) {

        this.documentId = documentId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.copies = new ArrayList<>();
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return String.format(
                "%-12s | %-25s | %-20s | %-15s | %d",
                documentId, title, author, category, copies.size());
    }
}
