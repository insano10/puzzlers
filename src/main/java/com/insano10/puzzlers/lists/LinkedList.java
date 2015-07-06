package com.insano10.puzzlers.lists;

public class LinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private long size;

    public LinkedList()
    {
        size = 0;
    }

    public void add(T element)
    {
        Node<T> newNode = new Node<>(element);

        if (head == null)
        {
            head = newNode;
            tail = head;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    //todo: you could search backwards if the index was closer to the size than zero for better performance
    public T get(int index)
    {
        checkIndex(index);

        Node<T> node = head;
        for (int i = 1; i <= index; i++)
        {
            node = node.next;
        }
        return node.data;
    }

    private void checkIndex(int index)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException("Index is negative: " + index);
        }
        if(index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static class Node<T>
    {
        private Node<T> next;
        private T data;

        public Node(T element)
        {
            this.data = element;
        }

        public void setNext(Node<T> node)
        {
            this.next = node;
        }
    }

}
