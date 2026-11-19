//Code lỗi 
//employees.stream()
    //.filter(e -> e.getSalary() = 15000000)
    //.forEach(System.out::println);
//Dấu = dùng để gán giá trị.
//Muốn kiểm tra lương lớn hơn hoặc bằng 15 triệu thì phải dùng >=.
//Không thể gán giá trị trực tiếp vào kết quả getSalary().

//Code lỗi
//List<Employee> names = employees.stream()
    //.map(Employee::getName)
    //.collect(Collectors.toList());
//Employee là kiểu dữ liệu nhân viên.
//getName() chỉ lấy ra tên nhân viên.
//Tên nhân viên có kiểu String, không phải Employee

//Code lỗi
//employees.stream()
    //.sorted((a, b) ->
        //a.getSalary() > b.getSalary())
    //.forEach(System.out::println);
// Ở đây chương trình đang so sánh hai mức lương bằng dấu >.
// Nhưng cách sắp xếp này cần kết quả so sánh là một số nguyên, không phải đúng hoặc sai.
// phải dùng Comparator.comparingDouble(Employee::getSalary) và Collections.reverseOrder() để sắp xếp giảm dần.