package main.algo.graph;

public class TestGraphs {

    public TestGraphs() {}


    /*
                   1
              ┌────╂────┐
              2    3    4
              ┊    ┠──┐
              5    6  7
                      ┊
                      8
     */
    public Node prepareDefaultTestGraphs() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);
        Node n07 = new Node(7);
        Node n08 = new Node(8);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n01.addChildren(n04);
        n02.addChildren(n05);
        n03.addChildren(n06);
        n03.addChildren(n07);
        n07.addChildren(n08);
        n04.addChildren(n08);

        return header;
    }

    /*
                 1
              ┌──┸──┐
              2     3
              ┊  ╲  ┊
              4─────5
              └──┳──┘
                 6
     */
    public Node prepareTestGraphsWithClose1() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n02.addChildren(n04);
        n02.addChildren(n05);
        n03.addChildren(n05);
        n04.addChildren(n05);
        n04.addChildren(n06);
        n05.addChildren(n06);

        return header;
    }

    /*
                 1
              ┌──┸──┐
              2     3
              ┊  ╱  ┊
              4─────5
              └──┳──┘
                 6
     */
    public Node prepareTestGraphsWithClose2() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n02.addChildren(n04);
        n03.addChildren(n04);
        n03.addChildren(n05);
        n04.addChildren(n06);
        n05.addChildren(n04);
        n05.addChildren(n06);

        return header;
    }

    /*
                 1
              ┌──┸──┐
              2     3
              ┊  ╱  ┊
              4─────5
              └──┳──┫
                 6  7
     */
    public Node prepareTestGraphsWithClose3() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);
        Node n07 = new Node(7);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n02.addChildren(n04);
        n03.addChildren(n04);
        n03.addChildren(n05);
        n04.addChildren(n05);
        n04.addChildren(n06);
        n05.addChildren(n06);
        n05.addChildren(n07);

        return header;
    }

    /*
                 2─────┐
              ┌──╂──┐  ┊
           ┌─ 3  ┊  0  ┊
           └──┘  ┊  ┠──┘
                 ┊  1
                 └──┘
     */
    public Node prepareTestGraphsComplicate() {
        Node n00 = new Node(0);
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);

        Node header = n02;
        n02.addChildren(n00);
        n02.addChildren(n03);
        n00.addChildren(n02);
        n00.addChildren(n01);
        n01.addChildren(n02);
        n03.addChildren(n03);

        return header;
    }

    /*
               1 ───────┐
          ┌────╂────┐   ┊
          2    3    4   ┊
          ┊  ╱ ┠──┐     ┊
          5    6  7     ┊
               ┠──┘     ┊
               9        ┊
               ┊        ┊
               10 ──────┘
    */
    public Node prepareTestGraphsComplicate2() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);
        Node n07 = new Node(7);
        Node n08 = new Node(8);
        Node n09 = new Node(9);
        Node n10 = new Node(10);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n01.addChildren(n04);
        n02.addChildren(n05);
        n03.addChildren(n05);
        n03.addChildren(n06);
        n03.addChildren(n07);
        n06.addChildren(n09);
        n07.addChildren(n09);
        n09.addChildren(n10);

        n01.addChildren(n10);

        return header;
    }

    /*
                 1
              ┌──┸──┐
              2     3
              ┊     ┊
              4     5
              └──┳──┘
                 6
     */
    public Node prepareTestGraphsWithSimpleCircle() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n02.addChildren(n04);
        n03.addChildren(n05);
        n04.addChildren(n06);
        n05.addChildren(n06);

        return header;
    }

    /*
                   1
              ┌────╂─────┐
              2    3     10
              ┊    ┠──┐  ┊
              4    5  7  11
           ┌──┸─┳──┸──┘  ┊
           9    6        12
           ┊    ┊        ┊
           ┊    ┊        13
           └────╂────────┘
                8

        from 1 to 8
            1 2 4 9 8
            1 2 4 6 8
            1 3 5 6 8
            1 3 7 6 8
            1 10 11 12 13 8
     */
    public Node prepareTestGraphWithComplicateCircle() {
        Node n01 = new Node(1);
        Node n02 = new Node(2);
        Node n03 = new Node(3);
        Node n04 = new Node(4);
        Node n05 = new Node(5);
        Node n06 = new Node(6);
        Node n07 = new Node(7);
        Node n08 = new Node(8);
        Node n09 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        Node n12 = new Node(12);
        Node n13 = new Node(13);

        Node header = n01;
        n01.addChildren(n02);
        n01.addChildren(n03);
        n01.addChildren(n10);
        n02.addChildren(n04);
        n03.addChildren(n05);
        n03.addChildren(n07);
        n04.addChildren(n09);
        n04.addChildren(n06);
        n05.addChildren(n06);
        n07.addChildren(n06);
        n09.addChildren(n08);
        n06.addChildren(n08);

        n10.addChildren(n11);
        n11.addChildren(n12);
        n12.addChildren(n13);
        n13.addChildren(n08);

        return header;
    }
}
