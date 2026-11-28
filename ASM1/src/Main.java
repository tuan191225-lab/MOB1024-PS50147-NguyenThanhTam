
import exception.DuplicateCodeException;
import model.Copy;
import model.CopyStatus;
import model.Document;
import service.LibraryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final LibraryService service = new LibraryService("data/library.dat");

    public static void main(String[] args) {

        int choice;

        do {

            showMenu();

            choice = readInt("Chọn chức năng: ");

            try {

                switch (choice) {

                    case 1:
                        addDocument();
                        break;

                    case 2:
                        updateDocument();
                        break;

                    case 3:
                        deleteDocument();
                        break;

                    case 4:
                        showDocuments(
                                new ArrayList<>(service.getAllDocuments()));
                        break;

                    case 5:
                        addCopy();
                        break;

                    case 6:
                        updateCopy();
                        break;

                    case 7:
                        deleteCopy();
                        break;

                    case 8:
                        showCopies();
                        break;

                    case 9:
                        searchDocuments();
                        break;

                    case 10:
                        showSortedDocuments();
                        break;

                    case 11:
                        showPagedDocuments();
                        break;

                    case 0:
                        service.saveToFile();
                        System.out.println("Đã lưu dữ liệu. Tạm biệt!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (DuplicateCodeException
                    | IllegalArgumentException e) {

                System.out.println("Thông báo: " + e.getMessage());

            } catch (Exception e) {

                System.out.println(
                        "Có lỗi xảy ra: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private static void showMenu() {

        System.out.println("\n========== QUẢN LÝ THƯ VIỆN ==========");
        System.out.println("1. Thêm tài liệu");
        System.out.println("2. Sửa tài liệu");
        System.out.println("3. Xóa tài liệu");
        System.out.println("4. Xem danh sách tài liệu");

        System.out.println("--------------------------------------");

        System.out.println("5. Thêm bản sao");
        System.out.println("6. Sửa bản sao");
        System.out.println("7. Xóa bản sao");
        System.out.println("8. Xem bản sao theo tài liệu");

        System.out.println("--------------------------------------");

        System.out.println("9. Tìm kiếm tài liệu");
        System.out.println("10. Sắp xếp tài liệu theo tên");
        System.out.println("11. Phân trang tài liệu");

        System.out.println("0. Lưu và thoát");
        System.out.println("======================================");
    }

    private static void addDocument()
            throws DuplicateCodeException {

        System.out.println("\n--- THÊM TÀI LIỆU ---");

        String id = readText("Mã tài liệu: ");
        String title = readText("Tên tài liệu: ");
        String author = readText("Tác giả: ");
        String category = readText("Thể loại: ");

        Document document = new Document(
                id, title, author, category);

        service.addDocument(document);

        System.out.println("Thêm tài liệu thành công!");
    }

    private static void updateDocument() {

        System.out.println("\n--- SỬA TÀI LIỆU ---");

        String id = readText("Nhập mã tài liệu cần sửa: ");
        String title = readText("Tên mới: ");
        String author = readText("Tác giả mới: ");
        String category = readText("Thể loại mới: ");

        service.updateDocument(
                id, title, author, category);

        System.out.println("Cập nhật thành công!");
    }

    private static void deleteDocument() {

        String id = readText("Nhập mã tài liệu cần xóa: ");

        service.deleteDocument(id);

        System.out.println("Xóa tài liệu thành công!");
    }

    private static void addCopy()
            throws DuplicateCodeException {

        System.out.println("\n--- THÊM BẢN SAO ---");

        String documentId = readText("Mã tài liệu: ");
        String copyId = readText("Mã bản sao: ");

        CopyStatus status = readStatus();

        String note = readText("Ghi chú: ");

        Copy copy = new Copy(copyId, status, note);

        service.addCopy(documentId, copy);

        System.out.println("Thêm bản sao thành công!");
    }

    private static void updateCopy() {

        System.out.println("\n--- SỬA BẢN SAO ---");

        String documentId = readText("Mã tài liệu: ");
        String copyId = readText("Mã bản sao cần sửa: ");

        CopyStatus status = readStatus();

        String note = readText("Ghi chú mới: ");

        service.updateCopy(
                documentId, copyId, status, note);

        System.out.println("Cập nhật bản sao thành công!");
    }

    private static void deleteCopy() {

        String documentId = readText("Mã tài liệu: ");
        String copyId = readText("Mã bản sao cần xóa: ");

        service.deleteCopy(documentId, copyId);

        System.out.println("Xóa bản sao thành công!");
    }

    private static void showCopies() {

        String documentId = readText("Nhập mã tài liệu: ");

        List<Copy> copies = service.getCopies(documentId);

        System.out.println("\nMÃ BẢN SAO   | TRẠNG THÁI   | GHI CHÚ");
        System.out.println("-----------------------------------------------");

        if (copies.isEmpty()) {

            System.out.println("Tài liệu chưa có bản sao.");

        } else {

            for (Copy copy : copies) {
                System.out.println(copy);
            }
        }
    }

    private static void searchDocuments() {

        String keyword = readText(
                "Nhập mã, tên, tác giả hoặc thể loại cần tìm: ");

        List<Document> result = service.searchDocuments(keyword);

        showDocuments(result);
    }

    private static void showSortedDocuments() {

        List<Document> result = service.sortByTitle();

        showDocuments(result);
    }

    private static void showPagedDocuments() {

        List<Document> all = new ArrayList<>(
                service.getAllDocuments());

        int pageSize = 3;

        if (all.isEmpty()) {

            System.out.println("Chưa có tài liệu.");

            return;
        }

        int totalPages = (int) Math.ceil(
                (double) all.size() / pageSize);

        int page = readInt(
                "Nhập trang (1-" + totalPages + "): ");

        if (page < 1 || page > totalPages) {

            System.out.println("Số trang không hợp lệ!");

            return;
        }

        int fromIndex = (page - 1) * pageSize;

        int toIndex = Math.min(
                fromIndex + pageSize,
                all.size());

        System.out.println(
                "Trang " + page + "/" + totalPages);

        showDocuments(all.subList(fromIndex, toIndex));
    }

    private static void showDocuments(
            List<Document> documents) {

        System.out.println(
                "\nMÃ TÀI LIỆU  | TÊN TÀI LIỆU             | TÁC GIẢ              | THỂ LOẠI        | SỐ BẢN SAO");

        System.out.println(
                "-----------------------------------------------------------------------------------------------");

        if (documents.isEmpty()) {

            System.out.println("Không có dữ liệu.");

            return;
        }

        for (Document document : documents) {

            System.out.println(document);
        }
    }

    private static CopyStatus readStatus() {

        System.out.println("Trạng thái bản sao:");
        System.out.println("1. Còn tốt");
        System.out.println("2. Hư hỏng");
        System.out.println("3. Mất");

        int choice = readInt("Chọn trạng thái: ");

        switch (choice) {

            case 1:
                return CopyStatus.GOOD;

            case 2:
                return CopyStatus.DAMAGED;

            case 3:
                return CopyStatus.LOST;

            default:
                throw new IllegalArgumentException(
                        "Trạng thái không hợp lệ!");
        }
    }

    private static String readText(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "Không được để trống. Vui lòng nhập lại!");
        }
    }

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            try {

                return Integer.parseInt(value);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập một số nguyên!");
            }
        }
    }
}
