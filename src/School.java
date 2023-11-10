public class School {
    private ClassList classList;
    private BST bst;
    private int[] pakcilPosition;

    public School() {
        this.classList = new ClassList();
        this.bst = new BST();
        this.pakcilPosition = new int[]{1, 1}; // Initial position of Pakcil
    }

    public void initSchool(int[][] studentPoints) {
        for (int[] points : studentPoints) {
            classList.addClass(points);
            bst.insert(classList.getTotalClasses());
        }
    }

    public void addNewClass(int[] studentPoints) {
        classList.addClass(studentPoints);
        bst.insert(classList.getTotalClasses());
    }

    public void giveTask(int points, int studentId) {
        int currentClass = pakcilPosition[1] - 1;
        classList.giveTask(currentClass, studentId, points);
    }

    public void warnCheater(int studentId) {
        int currentClass = pakcilPosition[1] - 1;
        int cheaterWarning = classList.warnCheater(currentClass, studentId);

        if (cheaterWarning == 3) {
            // Cheater detected three times, move to the worst class
            int worstClass = bst.findMin();
            int currentStudentId = classList.moveStudent(currentClass, worstClass, studentId);

            if (currentStudentId != -1) {
                // Remove the student from the BST and re-insert after the move
                bst.delete(currentClass + 1); // Note: Adjusting class index for BST
                bst.insert(worstClass);
            }
        }
    }

    public void movePakcil(char direction) {
        if (direction == 'L' && pakcilPosition[1] > 1) {
            pakcilPosition[1]--;
        } else if (direction == 'R' && pakcilPosition[1] < classList.getTotalClasses()) {
            pakcilPosition[1]++;
        }
    }

    public void evaluateStudents() {
        int currentClass = pakcilPosition[1] - 1;
        classList.evaluateStudents(currentClass);
    }

    public void reorderClasses() {
        int currentClass = pakcilPosition[1] - 1;
        int prevClass = (currentClass > 0) ? currentClass - 1 : -1;
        int nextClass = (currentClass < classList.getTotalClasses() - 1) ? currentClass + 1 : -1;

        classList.exchangeStudents(currentClass, prevClass, nextClass);
        bst.exchange(currentClass + 1, prevClass + 1, nextClass + 1); // Note: Adjusting class index for BST
    }
}
