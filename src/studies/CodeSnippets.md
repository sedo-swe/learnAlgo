## Array 

### 1. Copy
```agsl
int[] test1 = {1, 2, 3};
int[] test1_1 = Arrays.copyOf(test1, test1.length + 1);
test1_1[test1.length] = 4;

==> Expected: 1234, Actual: 1234
```

```agsl
int[] test2 = {3, 4, 5};
int[] test2_1 = new int[test2.length + 1];
System.arraycopy(test2, 0, test2_1, 1, test2.length);
test2_1[0] = 1;

==> Expected: 1345, Actual: 1345
```

### 2. Contains
```java
    String[] vowels = { "A", "I", "E", "O", "U" };
    List<String> al = Arrays.asList(vowels);
    System.out.println(al.contains("A"));
```

### 3. Split, Stream & To Array
```java
    String lines = "I Love Java 8 Stream!";

    // split by space, uppercase, and convert to Array
    String[] result = Arrays.stream(lines.split("\\s+"))
        .map(String::toUpperCase)
        .toArray(String[]::new);
```

## Bit Manipulation
1. Bitwise operations cheat sheet (https://nicolwk.medium.com/bitwise-operations-cheat-sheet-743e09aec5b5)
```java
** Operators
AND         &
OR          |
NOT         ~
XOR         ^
        1^1: 0, 1^0 or 0^1:1, 0^0: 0
Left shift  <<
Right shift >>

Integer.toBinaryString(5) ==> "101"
Integer.toBinaryString(-1) ==> "11111111111111111111111111111111"
Integer.parseInt("101", 2) ==> 5

```
   
## String related
1. StringBuilder, StringBuffer
```java
- setLength(int newLength)
==> Reduce the length of current string, or append null characters
    Check easy.BinaryTreePaths

```

