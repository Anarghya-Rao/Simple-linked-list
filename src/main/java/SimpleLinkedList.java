import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class Node<T>{
    T data;
    Node<T> next;

    Node(T data){
        this.data = data;
        this.next = null;
    }
}

class SimpleLinkedList<T> {
    Node<T> head = null;
    int size = 0;

    SimpleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    SimpleLinkedList(T[] values) {
        Node<T> temp;
        Node<T> node = new Node<>(values[0]);
        head = node;
        temp = node;

        for(int i = 1; i<values.length; i++){
            Node<T> newNode = new Node<>(values[i]);
            temp.next = newNode;

            temp = newNode;
        }

        size = values.length;
    }

    void push(T value) {
        Node<T> newNode = new Node<>(value);

        if(head == null) {
            head = newNode;
        }
        else {
            Node<T> temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }
        size += 1;
    }

    T pop() {
        if(head == null){
            throw new NoSuchElementException();
        }

        Node<T> prev = head;
        Node<T> current = head;

        while(current.next != null){
            prev = current;
            current = current.next;
        }

        T value = current.data;
        prev.next = null;
        size -= 1;

        return value;
    }

    void reverse() {
        Node<T> prev = head;
        Node<T> current = head;
        Node<T> future = head;

        if(head != null) {
            future = future.next;

            while (future != null){
                current.next = prev;
                prev = current;
                current = future;
                future = future.next;
            }

            current.next = prev;

            head.next = null;
            head = current;
        }
    }

    T[] asArray(Class<T> clazz) {
        T[] arr = (T[]) Array.newInstance(clazz, size);

        Node<T> temp = head;
        int i = size-1;

        while(temp != null){
            arr[i] = temp.data;
            i -= 1;
            temp = temp.next;
        }
        return arr;
    }

    int size() {
        return size;
    }
}
