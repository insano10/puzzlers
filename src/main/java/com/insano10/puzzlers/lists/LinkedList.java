package com.insano10.puzzlers.lists;

import com.insano10.puzzlers.sorting.QuickSort;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class LinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

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
            newNode.setPrev(tail);
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

    public boolean remove(T data)
    {
        if(head == null)
        {
            return false;
        }
        else
        {
            Node<T> currentNode = head;

            while(currentNode != null)
            {
                if(currentNode.data.equals(data))
                {
                    removeNode(currentNode);
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public void clear()
    {
        head = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean removeDuplicates()
    {
        if(head == null)
        {
            return false;
        }

        Set<T> elementsSeen = new HashSet<>();
        boolean removedElements = false;
        Node<T> currentNode = head;

        while(currentNode != null)
        {
            if(elementsSeen.contains(currentNode.data))
            {
                removeNode(currentNode);
                removedElements = true;
            }
            else
            {
                elementsSeen.add(currentNode.data);
            }

            currentNode = currentNode.next;
        }

        return removedElements;
    }

    public boolean removeDuplicatesNoExtraDataStructure()
    {
        if(head == null)
        {
            return false;
        }

        boolean removedElements = false;
        Node<T> currentNode = head;

        while(currentNode != null)
        {
            //remove occurrences of this element from later on in the list
            Node<T> possibleDuplicate = currentNode.next;
            while(possibleDuplicate != null)
            {
                if(possibleDuplicate.data.equals(currentNode.data))
                {
                    removeNode(possibleDuplicate);
                    removedElements = true;
                }
                possibleDuplicate = possibleDuplicate.next;
            }

            currentNode = currentNode.next;
        }

        return removedElements;
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

    private void removeNode(Node<T> currentNode)
    {
        if(size == 1)
        {
            clear();
        }
        else
        {
            if(currentNode.prev == null)
            {
                //head is being removed
                head = currentNode.next;
            }
            else
            {
                //zip the 2 surrounding nodes together
                currentNode.prev.next = currentNode.next;

                if(currentNode.next == null)
                {
                    tail = currentNode.prev;
                }
            }
            size--;
        }
    }

    public static <T extends Comparable<T>> void sortAscending(LinkedList<T> list, Class<T> listClass)
    {
        if(list.size() > 0)
        {
            T[] array = (T[])Array.newInstance(listClass, list.size());

            for (int i = 0; i < list.size(); i++)
            {
                array[i] = list.get(i);
            }

            QuickSort.sortInPlace(array);

            list.clear();
            for (int i = 0; i < array.length; i++)
            {
                list.add(array[i]);
            }
        }
    }

    private static class Node<T>
    {
        private Node<T> prev;
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
        public void setPrev(Node<T> node)
        {
            this.prev = node;
        }
    }

}
