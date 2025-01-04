import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + enrolled + "/" + capacity + "\nSchedule: " + schedule;
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
        } else if (!course.isAvailable()) {
            System.out.println("This course is full.");
        } else {
            registeredCourses.add(course);
            course.enrolled++;
            System.out.println("Successfully registered for " + course.title);
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.enrolled--;
            System.out.println("Successfully dropped " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name + "\nRegistered Courses: " +
                (registeredCourses.isEmpty() ? "None" : "\n" + registeredCourses);
    }
}

@SuppressWarnings("unused")
class CourseDatabase {
    private static final Map<String, Course> courses = new HashMap<>();
    private static final Map<String, Student> students = new HashMap<>();

    public static void addCourse(String code, String title, String description, int capacity, String schedule) {
        courses.put(code, new Course(code, title, description, capacity, schedule));
    }

    public static void addStudent(String id, String name) {
        students.put(id, new Student(id, name));
    }

    public static void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses.values()) {
                System.out.println(course);
                System.out.println("---------------------");
            }
        }
    }

    public static Course findCourse(String code) {
        return courses.get(code);
    }

    public static Student findStudent(String id) {
        return students.get(id);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        addCourse("CS101", "Introduction to Programming", "Learn basic programming concepts.", 30, "Mon/Wed 10:00 AM");
        addCourse("CS102", "Data Structures", "Understand data structures and algorithms.", 25, "Tue/Thu 2:00 PM");

        addStudent("S001", "Alice");
        addStudent("S002", "Bob");

        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> listCourses();
                case 2 -> {
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = findStudent(studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();
                    Course course = findCourse(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    student.registerCourse(course);
                }
                case 3 -> {
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = findStudent(studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code to drop: ");
                    String courseCode = scanner.nextLine();
                    Course course = findCourse(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    student.dropCourse(course);
                }
                case 4 -> {
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = findStudent(studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                    } else {
                        System.out.println(student);
                    }
                }
                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
