package main.tricks;

public class EnumTestFile {

    public void testEnum1() {
        System.out.println("EnumTest1.ADV2: " + EnumTest1.ADV2);
        System.out.println(" comparison w/ string: " + "1234568".equals(EnumTest1.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV2: " + EnumTest1.ADV2.equals(EnumTest1.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV3: " + EnumTest1.ADV2.equals(EnumTest1.ADV3));
    }

    public void testEnum2() {
        System.out.println("\nEnumTest2.ADV2: " + EnumTest2.ADV2);
        System.out.println(" \"1234568\".equals(EnumTest2.ADV2.getValue(): " + "1234568".equals(EnumTest2.ADV2.getValue()));
        System.out.println(" \"1234568\".equals(EnumTest2.ADV2:\t\t\t " + "1234568".equals(EnumTest2.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV2: " + EnumTest2.ADV2.equals(EnumTest2.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV3: " + EnumTest2.ADV2.equals(EnumTest2.ADV3));
    }

    public void testEnum3() {
        EnumTest3.ADV2.setValue("1234569");
        System.out.println("\nEnumTest3.ADV2: " + EnumTest3.ADV2);
        System.out.println(" \"1234569\".equals(EnumTest3.ADV2.getValue(): " + "1234569".equals(EnumTest3.ADV2.getValue()));
        System.out.println(" \"1234569\".equals(EnumTest3.ADV2:\t\t\t " + "1234568".equals(EnumTest3.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV2: " + EnumTest3.ADV2.equals(EnumTest3.ADV2));
        System.out.println(" comparison w/ ADV2 & ADV3: " + EnumTest3.ADV2.equals(EnumTest3.ADV3));
    }

    public static void main(String[] args) {
        EnumTestFile etf = new EnumTestFile();
        etf.testEnum1();
        etf.testEnum2();
        etf.testEnum3();
    }
}
