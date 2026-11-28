
package service;

import exception.DuplicateCodeException;
import model.Copy;
import model.CopyStatus;
import model.Document;

import java.io.*;
import java.util.*;

public class LibraryService {

    private Map<String, Document> documents = new LinkedHashMap<>();

    private final String filePath;

    public LibraryService(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    public Collection<Document> getAllDocuments() {
        return documents.values();
    }

    public Document findDocument(String documentId) {
        return documents.get(documentId);
    }

    public void addDocument(Document document)
            throws DuplicateCodeException {

        if (documents.containsKey(document.getDocumentId())) {
            throw new DuplicateCodeException(
                    "Mã tài liệu đã tồn tại!");
        }

        documents.put(document.getDocumentId(), document);

        saveToFile();
    }

    public void updateDocument(String id, String title,
            String author, String category) {

        Document document = requireDocument(id);

        document.setTitle(title);
        document.setAuthor(author);
        document.setCategory(category);

        saveToFile();
    }

    public void deleteDocument(String id) {

        if (documents.remove(id) == null) {
            throw new IllegalArgumentException(
                    "Không tìm thấy tài liệu!");
        }

        saveToFile();
    }

    public void addCopy(String documentId, Copy copy)
            throws DuplicateCodeException {

        Document document = requireDocument(documentId);

        if (findCopy(copy.getCopyId()) != null) {
            throw new DuplicateCodeException(
                    "Mã bản sao đã tồn tại!");
        }

        document.getCopies().add(copy);

        saveToFile();
    }

    public void updateCopy(String documentId, String copyId,
            CopyStatus status, String note) {

        Copy copy = requireCopy(documentId, copyId);

        copy.setStatus(status);
        copy.setNote(note);

        saveToFile();
    }

    public void deleteCopy(String documentId, String copyId) {

        Document document = requireDocument(documentId);

        boolean removed = document.getCopies().removeIf(
                copy -> copy.getCopyId().equalsIgnoreCase(copyId));

        if (!removed) {
            throw new IllegalArgumentException(
                    "Không tìm thấy bản sao!");
        }

        saveToFile();
    }

    public List<Copy> getCopies(String documentId) {

        return new ArrayList<>(
                requireDocument(documentId).getCopies());
    }

    public List<Document> searchDocuments(String keyword) {

        List<Document> result = new ArrayList<>();

        String key = keyword.trim().toLowerCase(Locale.ROOT);

        for (Document document : documents.values()) {

            if (document.getDocumentId().toLowerCase(Locale.ROOT).contains(key)
                    || document.getTitle().toLowerCase(Locale.ROOT).contains(key)
                    || document.getAuthor().toLowerCase(Locale.ROOT).contains(key)
                    || document.getCategory().toLowerCase(Locale.ROOT).contains(key)) {

                result.add(document);
            }
        }

        return result;
    }

    public List<Document> sortByTitle() {

        List<Document> result = new ArrayList<>(
                documents.values());

        result.sort(
                Comparator.comparing(
                        Document::getTitle,
                        String.CASE_INSENSITIVE_ORDER));

        return result;
    }

    private Document requireDocument(String id) {

        Document document = documents.get(id);

        if (document == null) {
            throw new IllegalArgumentException(
                    "Không tìm thấy tài liệu có mã " + id);
        }

        return document;
    }

    private Copy requireCopy(String documentId, String copyId) {

        Document document = requireDocument(documentId);

        for (Copy copy : document.getCopies()) {

            if (copy.getCopyId().equalsIgnoreCase(copyId)) {
                return copy;
            }
        }

        throw new IllegalArgumentException(
                "Không tìm thấy bản sao!");
    }

    private Copy findCopy(String copyId) {

        for (Document document : documents.values()) {

            for (Copy copy : document.getCopies()) {

                if (copy.getCopyId().equalsIgnoreCase(copyId)) {
                    return copy;
                }
            }
        }

        return null;
    }

    public void saveToFile() {

        File file = new File(filePath);

        File parent = file.getParentFile();

        try {

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(file))) {

                output.writeObject(documents);
            }

        } catch (IOException e) {

            System.out.println(
                    "Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {

        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {

            documents = (Map<String, Document>) input.readObject();

            System.out.println("Đã tải dữ liệu từ file.");

        } catch (IOException | ClassNotFoundException
                | ClassCastException e) {

            System.out.println(
                    "Không thể đọc dữ liệu: " + e.getMessage());

            documents = new LinkedHashMap<>();
        }
    }
}
