package test.algo.tree;



import main.algo.tree.BinaryTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryTreeUnitTest {

    @Test
    public void givenABinaryTree_WhenAddElements_ThenTreeContainsThoseElements() {
        BinaryTree bt = BinaryTree.createSampleBinaryTree();

        assertTrue(bt.contain(6));
        assertTrue(bt.contain(4));

        assertFalse(bt.contain(13));
        bt.add(13);
        assertTrue(bt.contain(13));
    }

    @Test
    public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
        BinaryTree bt = BinaryTree.createSampleBinaryTree();

        assertTrue(bt.contain(9));
        bt.delete(9);
        assertFalse(bt.contain(9));
    }
}
