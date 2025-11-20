import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ---------------------------------------------
// Abstract Parent Class: Student
// ---------------------------------------------
abstract class Student {
    private int id;
    private String name;
    private String course;
    protected double baseFee;

    public Student(int id, String name, String course, double baseFee) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.baseFee = baseFee;
    }

    // Polymorphic method
    public abstract double calculateTotalFee();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setBaseFee(double baseFee) {
        this.baseFee = baseFee;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Course: " + course +
               ", Base Fee: " + baseFee;
    }
}

// ---------------------------------------------
// Child Class: DayScholar
// ---------------------------------------------
class DayScholar extends Student {
    private double busFee;

    public DayScholar(int id, String name, String course, double baseFee, double busFee) {
        super(id, name, course, baseFee);
        this.busFee = busFee;
    }

    public double getBusFee() {
        return busFee;
    }

    public void setBusFee(double busFee) {
        this.busFee = busFee;
    }

    @Override
    public double calculateTotalFee() {
        return baseFee + busFee;
    }

    @Override
    public String toString() {
        return "[Day Scholar] " + super.toString() +
               ", Bus Fee: " + busFee +
               ", Total Fee: " + calculateTotalFee();
    }
}

// ---------------------------------------------
// Child Class: Hosteller
// ---------------------------------------------
class Hosteller extends Student {
    private double hostelFee;

    public Hosteller(int id, String name, String course, double baseFee, double hostelFee) {
        super(id, name, course, baseFee);
        this.hostelFee = hostelFee;
    }

    public double getHostelFee() {
        return hostelFee;
    }

    public void setHostelFee(double hostelFee) {
        this.hostelFee = hostelFee;
    }

    @Override
    public double calculateTotalFee() {
        return baseFee + hostelFee;
    }

    @Override
    public String toString() {
        return "[Hosteller] " + super.toString() +
               ", Hostel Fee: " + hostelFee +
               ", Total Fee: " + calculateTotalFee();
    }
}

// ---------------------------------------------
// Main Class: StudentManagementSystem
// ---------------------------------------------
public class StudentManagementSystem {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n========= Student Management System =========");
            System.out.println("1. Add Day Scholar");
            System.out.println("2. Add Hosteller");
            System.out.println("3. View All Students");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Update Student Details");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addDayScholar();
                    break;
                case 2:
                    addHosteller();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    searchStudentById();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);

        sc.close();
    }

    // ---------------------------------------------
    // Option 1: Add Day Scholar
    // ---------------------------------------------
    private static void addDayScholar() {
        System.out.println("\n--- Add Day Scholar ---");
        int id = readInt("Enter Student ID: ");
        sc.nextLine(); // clear buffer
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        double baseFee = readDouble("Enter Base Fee: ");
        double busFee = readDouble("Enter Bus Fee: ");

        Student s = new DayScholar(id, name, course, baseFee, busFee);
        students.add(s);
        System.out.println("Day Scholar added successfully!");
    }

    // ---------------------------------------------
    // Option 2: Add Hosteller
    // ---------------------------------------------
    private static void addHosteller() {
        System.out.println("\n--- Add Hosteller ---");
        int id = readInt("Enter Student ID: ");
        sc.nextLine(); // clear buffer
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        double baseFee = readDouble("Enter Base Fee: ");
        double hostelFee = readDouble("Enter Hostel Fee: ");

        Student s = new Hosteller(id, name, course, baseFee, hostelFee);
        students.add(s);
        System.out.println("Hosteller added successfully!");
    }

    // ---------------------------------------------
    // Option 3: View All Students
    // ---------------------------------------------
    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ---------------------------------------------
    // Option 4: Search by ID
    // ---------------------------------------------
    private static void searchStudentById() {
        System.out.println("\n--- Search Student by ID ---");
        int id = readInt("Enter Student ID to search: ");
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
        } else {
            System.out.println("Student found:");
            System.out.println(s);
        }
    }

    // ---------------------------------------------
    // Option 5: Update Student
    // ---------------------------------------------
    private static void updateStudent() {
        System.out.println("\n--- Update Student Details ---");
        int id = readInt("Enter Student ID to update: ");
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        sc.nextLine(); // clear buffer
        System.out.print("Enter new Name (leave blank to keep same): ");
        String name = sc.nextLine();
        if (!name.trim().isEmpty()) {
            s.setName(name);
        }

        System.out.print("Enter new Course (leave blank to keep same): ");
        String course = sc.nextLine();
        if (!course.trim().isEmpty()) {
            s.setCourse(course);
        }

        String baseFeeStr;
        System.out.print("Enter new Base Fee (or -1 to keep same): ");
        baseFeeStr = sc.next();
        try {
            double baseFee = Double.parseDouble(baseFeeStr);
            if (baseFee >= 0) {
                s.setBaseFee(baseFee);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Base fee not changed.");
        }

        // Type-specific updates using polymorphism + instanceof
        if (s instanceof DayScholar) {
            DayScholar ds = (DayScholar) s;
            double newBusFee = readDouble("Enter new Bus Fee (or -1 to keep same): ");
            if (newBusFee >= 0) {
                ds.setBusFee(newBusFee);
            }
        } else if (s instanceof Hosteller) {
            Hosteller hs = (Hosteller) s;
            double newHostelFee = readDouble("Enter new Hostel Fee (or -1 to keep same): ");
            if (newHostelFee >= 0) {
                hs.setHostelFee(newHostelFee);
            }
        }

        System.out.println("Student updated successfully!");
    }

    // ---------------------------------------------
    // Option 6: Delete Student
    // ---------------------------------------------
    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        int id = readInt("Enter Student ID to delete: ");
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        students.remove(s);
        System.out.println("Student deleted successfully!");
    }

    // ---------------------------------------------
    // Helper: Find by ID
    // ---------------------------------------------
    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // ---------------------------------------------
    // Helper: Safe int input
    // ---------------------------------------------
    private static int readInt(String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. " + message);
            sc.next();
        }
        return sc.nextInt();
    }

    // ---------------------------------------------
    // Helper: Safe double input
    // ---------------------------------------------
    private static double readDouble(String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            System.out.print("Invalid input. " + message);
            sc.next();
        }
        return sc.nextDouble();
    }
}