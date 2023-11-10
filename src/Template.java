import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Template {
    private static InputReader in;
    private static PrintWriter out;
    static AVLTree tree = new AVLTree();

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            // TODO: process inputs
        }

        int Q = in.nextInt();
        for (int i = 0; i < Q; i++) {
            char query = in.nextChar();

            if (query == 'G') { grow(); }
            else if (query == 'P') { pick(); }
            else if (query == 'F') { fall(); }
            else { height(); }
        }

        out.close();
    }

    static void grow() {
        // TODO: implement this method
    }

    static void pick() {
        // TODO: implement this method
    }

    static void fall() {
        // TODO: implement this method
    }

    static void height() {
        // TODO: implement this method
    }

    // taken from https://www.programiz.com/dsa/avl-tree
    // a method to print the contents of a Tree data structure in a readable
    // format. it is encouraged to use this method for debugging purposes.
    // to use, simply copy and paste this line of code:
    // printTree(tree.root, "", true);
    static void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            out.print(indent);
            if (last) {
                out.print("R----");
                indent += "   ";
            } else {
                out.print("L----");
                indent += "|  ";
            }
            out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public char nextChar() {
            return next().charAt(0);
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

class Node {
    // TODO: modify attributes as needed
    int key;
    Node left, right;

    Node(int key) {
        this.key = key;
    }
}

class AVLTree {
    // TODO: modify attributes as needed
    Node root;

    Node insert(Node node, int key) {
        // TODO: implement this method
        return null;
    }

    Node delete(Node node, int key) {
        // TODO: implement this method
        return null;
    }

    Node balancing(Node node) {
        // TODO: implement this method
        return null;
    }

    Node singleLeftRotate(Node node) {
        // TODO: implement this method
        return null;
    }

    Node singleRightRotate(Node node) {
        // TODO: implement this method
        return null;
    }
}