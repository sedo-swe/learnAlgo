package main.practice;

/**
 * Question about cursor, is it locating next to character? or pointing a character like highlighting?
 * Because, based on this, implementation will be different.
 */
public class TextEditor {
    StringBuffer text;
    int cursor;
    public TextEditor() {
        this.text = new StringBuffer();
        cursor = 0;
    }

    public void insert(String s) {  //
        this.text.insert(this.cursor < 0 ? 0 : this.cursor, s);
        cursor += s.length();
    }

    public void left(int move) {
        cursor -= move;
        if (cursor < 0)
            cursor = 0;
    }

    public void right(int move) {
        cursor += move;
        if (cursor > text.length())
            cursor = text.length();
    }

    public void print(int num) {
        int start = this.cursor - num < 0 ? 0 : this.cursor - num;
        int end = this.cursor + num > this.text.length() ? this.text.length() - 1 : this.cursor + num;
        String temp = this.text.substring(start, end);
        System.out.println(temp);
    }
    // text: ['h', 'e', 'l', '', '']
    // cursor: 3
    // end: 3
    // start: 0
    public void delete(int num) {
        if (this.cursor >= this.text.length())
            return;
        int end = this.cursor + num > this.text.length() ? this.text.length() - 1 : this.cursor + num;
        this.text.replace(this.cursor, end, "");
    }

    public void backspace(int num) {
        int start = this.cursor - num < 0 ? 0 : this.cursor - num;
        this.text.replace(start, this.cursor, "");
        this.cursor = start;
    }

    public static void main(String[] args) {
        TextEditor te = new TextEditor();
        te.insert("hello");
        System.out.println(te.cursor);
//        te.delete(1);
//        System.out.println(te.cursor);
        te.backspace(1);
        System.out.println(te.text.toString());
        System.out.println(te.cursor);
        te.left(3);
//        te.backspace(1);
        te.delete(1);
        System.out.println(te.text.toString());
        System.out.println(te.cursor);
//        System.out.println(te.text.toString());
//        te.right(4);
//        System.out.println(te.cursor);
//        te.print(4);
//        te.left(1);
//        System.out.println(te.cursor);
//        te.backspace(1);
//        te.print(4);

        System.out.println();
        StringBuffer sb = new StringBuffer();
        sb.insert(0, "hello");
        sb.delete(3, 4);
        System.out.println(sb.toString());
//        sb.replace(1, 3, "");
//        System.out.println(sb.length());
//        System.out.println(sb.toString());
    }
}
