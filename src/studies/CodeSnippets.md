## Array Copy
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

## Bit Manipulation
1. Bitwise operations cheat sheet (https://nicolwk.medium.com/bitwise-operations-cheat-sheet-743e09aec5b5)
```agsl
** Operators
AND         &
OR          |
NOT         ~
XOR         ^
Left shift  <<
Right shift >>

```
   

