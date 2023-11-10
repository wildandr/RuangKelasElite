public class ClassList {
    private Node head;
    private int totalClasses;

    public ClassList() {
        this.head = null;
        this.totalClasses = 0;
    }

    public void addClass(int[] studentPoints) {
        Node newNode = new Node(studentPoints);
        newNode.setNext(head);
        head = newNode;
        totalClasses++;
    }

    public int giveTask(int classId, int studentId, int points) {
        Node currentClass = findClassById(classId);

        if (currentClass != null) {
            int result = currentClass.giveTask(studentId, points);
            return (result != -1) ? result : -1;
        } else {
            return -1; // Class not found
        }
    }

    public int warnCheater(int classId, int studentId) {
        Node currentClass = findClassById(classId);

        if (currentClass != null) {
            int result = currentClass.warnCheater(studentId);

            if (result == 3) {
                // Remove the class if the cheater detected three times
                removeClass(classId);
            }

            return result;
        } else {
            return -1; // Class not found
        }
    }

    public int moveStudent(int fromClassId, int toClassId, int studentId) {
        Node fromClass = findClassById(fromClassId);
        Node toClass = findClassById(toClassId);

        if (fromClass != null && toClass != null) {
            int result = fromClass.removeStudent(studentId);

            if (result != -1) {
                toClass.addStudent(result);
            }

            return result;
        } else {
            return -1; // Class not found
        }
    }

    public void evaluateStudents(int classId) {
        Node currentClass = findClassById(classId);

        if (currentClass != null) {
            currentClass.evaluateStudents();
        }
    }

    public void exchangeStudents(int classId, int prevClassId, int nextClassId) {
        Node currentClass = findClassById(classId);
        Node prevClass = findClassById(prevClassId);
        Node nextClass = findClassById(nextClassId);

        if (currentClass != null) {
            currentClass.transferStudents(prevClass, nextClass);
        }
    }

    private Node findClassById(int classId) {
        Node current = head;

        while (current != null && current.getId() != classId) {
            current = current.getNext();
        }

        return current;
    }

    public void removeClass(int classId) {
        Node current = head;
        Node prev = null;

        while (current != null && current.getId() != classId) {
            prev = current;
            current = current.getNext();
        }

        if (current != null) {
            if (prev != null) {
                prev.setNext(current.getNext());
            } else {
                head = current.getNext();
            }

            totalClasses--;
        }
    }

    public int getTotalClasses() {
        return totalClasses;
    }
}
