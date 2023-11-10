import java.util.Arrays;

public class Node {
    private int id;
    private int[] studentPoints;
    private Node next;

    private static int ClassIdGenerator = 1; // Ini adalah variabel generator ID kelas

    private int classId;
    private int[] students;

    public Node(int numberOfStudents) {
        this.classId = ClassIdGenerator++;
        this.students = new int[numberOfStudents];
    }

    public int getClassId() {
        return classId;
    }

    public int[] getStudents() {
        return students;
    }

    public static int getClassIdGenerator() {
        return ClassIdGenerator;
    }

    public static void setClassIdGenerator(int classIdGenerator) {
        ClassIdGenerator = classIdGenerator;
    }

    public Node(int[] studentPoints) {
        this.id = ClassIdGenerator++;
        this.studentPoints = studentPoints;
        this.next = null;
    }

    public int getId() {
        return id;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int giveTask(int studentId, int points) {
        int index = studentId - 1;

        if (index >= 0 && index < studentPoints.length) {
            studentPoints[index] += points;
            return studentPoints[index];
        } else {
            return -1; // Student not found
        }
    }

    public int warnCheater(int studentId) {
        int index = studentId - 1;

        if (index >= 0 && index < studentPoints.length) {
            if (studentPoints[index] < 0) {
                return -1; // Invalid points (already warned or expelled)
            }

            studentPoints[index] -= 1;

            if (studentPoints[index] < 0) {
                return 3; // Cheater detected three times
            } else if (studentPoints[index] == 0) {
                return 2; // Cheater detected second time
            } else {
                return 1; // Cheater detected first time
            }
        } else {
            return -1; // Student not found
        }
    }

    public int removeStudent(int studentId) {
        int index = studentId - 1;

        if (index >= 0 && index < studentPoints.length) {
            int removedPoints = studentPoints[index];
            studentPoints[index] = -1; // Expel the student
            return removedPoints;
        } else {
            return -1; // Student not found
        }
    }

    public void addStudent(int points) {
        int[] newStudentPoints = new int[studentPoints.length + 1];
        System.arraycopy(studentPoints, 0, newStudentPoints, 0, studentPoints.length);
        newStudentPoints[studentPoints.length] = points;
        studentPoints = newStudentPoints;
    }

    public void evaluateStudents() {
        // Sorting the student points array in descending order
        Sorting.bubbleSort(studentPoints);

        // Print the top 3 and bottom 3 students
        System.out.print("Top 3 students: ");
        for (int i = 0; i < Math.min(3, studentPoints.length); i++) {
            System.out.print(studentPoints[i] + " ");
        }

        System.out.print("Bottom 3 students: ");
        for (int i = Math.max(0, studentPoints.length - 3); i < studentPoints.length; i++) {
            System.out.print(studentPoints[i] + " ");
        }

        System.out.println();
    }

    public void transferStudents(Node prevClass, Node nextClass) {
        int[] topStudents = getTopStudents(3);
        int[] bottomStudents = getBottomStudents(3);

        // Remove bottom students from current class
        for (int studentId : bottomStudents) {
            removeStudent(studentId);
        }

        // Add bottom students to previous class
        for (int studentId : bottomStudents) {
            prevClass.addStudent(studentPoints[studentId - 1]);
        }

        // Remove top students from current class
        for (int studentId : topStudents) {
            removeStudent(studentId);
        }

        // Add top students to next class
        for (int studentId : topStudents) {
            nextClass.addStudent(studentPoints[studentId - 1]);
        }
    }

    private int[] getTopStudents(int count) {
        int[] result = new int[count];
        int[] copy = Arrays.copyOf(studentPoints, studentPoints.length);

        for (int i = 0; i < count; i++) {
            int maxIndex = getMaxIndex(copy);
            result[i] = maxIndex + 1;
            copy[maxIndex] = Integer.MIN_VALUE; // Mark as visited
        }

        return result;
    }

    private int[] getBottomStudents(int count) {
        int[] result = new int[count];
        int[] copy = Arrays.copyOf(studentPoints, studentPoints.length);

        for (int i = 0; i < count; i++) {
            int minIndex = getMinIndex(copy);
            result[i] = minIndex + 1;
            copy[minIndex] = Integer.MAX_VALUE; // Mark as visited
        }

        return result;
    }

    private int getMaxIndex(int[] array) {
        int maxIndex = 0;
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private int getMinIndex(int[] array) {
        int minIndex = 0;
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}
