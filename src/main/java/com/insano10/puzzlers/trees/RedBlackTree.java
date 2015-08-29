package com.insano10.puzzlers.trees;

import com.google.common.annotations.VisibleForTesting;

import java.util.function.Consumer;

import static com.insano10.puzzlers.trees.RedBlackTree.Colour.BLACK;
import static com.insano10.puzzlers.trees.RedBlackTree.Colour.RED;

public class RedBlackTree<T extends Comparable<T>>
{
    public enum Colour
    {
        RED, BLACK
    }

    private RedBlackNode<T> root = null;

    @VisibleForTesting
    public RedBlackNode<T> getRoot()
    {
        return root;
    }

    public void insert(T data)
    {
        RedBlackNode<T> node = binaryTreeInsert(data);

        rebalance(node);
    }

    private void rebalance(RedBlackNode<T> node)
    {
        // rebalance the tree if the newly inserted node is not the root
        // and it's parent is red
        if (!root.equals(node) && node.getParent().getColour() == RED)
        {
            RedBlackNode<T> uncle = getUncleOf(node);

            if (uncle.getColour() == RED)
            {
                node.getParent().setColour(BLACK);
                uncle.setColour(BLACK);
                rebalance(node.getParent().getParent());
            }
            else
            {
                if (leftLeftCase(node))
                {
                    rightRotate(node.getParent().getParent());
                    flipColour(node.getParent());
                    flipColour(node.getParent().getParent());
                }
                else if(leftRightCase(node))
                {
                    leftRotate(node.getParent());

                    //same as leftLeftCase
                    rightRotate(node.getParent().getParent());
                    flipColour(node.getParent());
                    flipColour(node.getParent().getParent());
                }
                else if(rightRightCase(node))
                {
                    leftRotate(node.getParent().getParent());
                    flipColour(node.getParent());
                    flipColour(node.getParent().getParent());
                }
                else if(rightLeftCase(node))
                {
                    rightRotate(node.getParent());

                    //same as rightRightCase
                    leftRotate(node.getParent().getParent());
                    flipColour(node.getParent());
                    flipColour(node.getParent().getParent());
                }
                else
                {
                    throw new RuntimeException("WTF?");
                }
            }
        }
    }

    private void flipColour(RedBlackNode<T> node)
    {
        if(node.getColour() == RED)
        {
            node.setColour(BLACK);
        }
        else
        {
            node.setColour(RED);
        }
    }

    /*
                    [G]
                    /
                  [P]
                  /
                [N]
     */
    private boolean leftLeftCase(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> grandParent = parent.getParent();

        return grandParent.getLeft().equals(parent) &&
                parent.getLeft().equals(node);
    }

    /*
                   [G]
                   /
                 [P]
                  \
                   [N]
    */
    private boolean leftRightCase(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> grandParent = parent.getParent();

        return grandParent.getLeft().equals(parent) &&
                parent.getRight().equals(node);
    }

    /*
                    [G]
                     \
                     [P]
                      \
                      [N]
     */
    private boolean rightRightCase(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> grandParent = parent.getParent();

        return grandParent.getRight().equals(parent) &&
                parent.getRight().equals(node);
    }

    /*
                    [G]
                     \
                     [P]
                     /
                   [N]
     */
    private boolean rightLeftCase(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> grandParent = parent.getParent();

        return grandParent.getRight().equals(parent) &&
                parent.getLeft().equals(node);
    }

    private void rightRotate(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> top = node.getLeft();

        if(parent == null)
        {
            //node is the root
            root = top;
        }
        else
        {
            if(parent.getLeft().equals(node))
            {
                parent.setLeft(top);
            }
            else
            {
                parent.setRight(top);
            }
        }
        top.setRight(node);
    }

    private void leftRotate(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> top = node.getRight();

        if(parent == null)
        {
            //node is the root
            root = top;
        }
        else
        {
            if(parent.getLeft().equals(node))
            {
                parent.setLeft(top);
            }
            else
            {
                parent.setRight(top);
            }
        }
        top.setLeft(node);
    }

    private RedBlackNode<T> getUncleOf(RedBlackNode<T> node)
    {
        RedBlackNode<T> parent = node.getParent();
        RedBlackNode<T> grandparent = parent.getParent();

        return grandparent.getLeft().equals(parent) ? grandparent.getRight() : grandparent.getLeft();
    }

    public void visitInOrder(Consumer<T> onVisit)
    {
        traverseInOrder(root, onVisit);
    }

    private void traverseInOrder(RedBlackNode<T> node, Consumer<T> onVisit)
    {
        if (!node.isLeaf())
        {
            traverseInOrder(node.getLeft(), onVisit);
            onVisit.accept(node.getData());
            traverseInOrder(node.getRight(), onVisit);
        }
    }

    private RedBlackNode<T> binaryTreeInsert(T data)
    {
        if (root == null)
        {
            root = new InternalRedBlackNode<>(data, BLACK, null);
            return root;
        }
        else
        {
            RedBlackNode<T> next = root;

            while (true)
            {
                if (data.compareTo(next.getData()) <= 0)
                {
                    if (next.getLeft().isLeaf())
                    {
                        RedBlackNode<T> newNode = new InternalRedBlackNode<>(data, RED, next);
                        next.setLeft(newNode);
                        return newNode;
                    }
                    next = next.getLeft();
                }
                else
                {
                    if (next.getRight().isLeaf())
                    {
                        RedBlackNode<T> newNode = new InternalRedBlackNode<>(data, RED, next);
                        next.setRight(newNode);
                        return newNode;
                    }
                    next = next.getRight();
                }
            }
        }
    }
}
