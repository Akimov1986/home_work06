package ru.geekbrains.home_work06;

//Task 6.1
// Распределение книг в библиотеке
 /*     энциклопедия
         /      \
     наука     культура
                /     \
           искусство  ремесло*/

//Task 6.2; 6.3; 6.4;
public class Person {
    public String name;
    public int id;
    public int age;

    public Person (int id,String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
class Node {
    public Person person;
    public Node leftChild;
    public Node rightChild;

    public void display(){
        System.out.println("Имя: " + person.name + " Возраст " + person.age);
    }
}
class Tree {
    private  Node root;

    public void insert(Person person) {
        Node node = new Node();
        node.person = person;
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;

            while (true) {
                parent = current;
                if (person.id < current.person.id) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    } else {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            return;
                        }
                    }
                }
            }
        }
    }
    public Node find ( int key){
        Node current = root;
        while (current.person.id != key) {
            if (key < current.person.id) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    private void preOrder(Node rootNode){
        if(rootNode != null){
            rootNode.display();
            preOrder(rootNode.leftChild);
            preOrder(rootNode.rightChild);
        }
    }

    private void postOrder(Node rootNode){
        if(rootNode != null){
            postOrder(rootNode.leftChild);
            postOrder(rootNode.rightChild);
            rootNode.display();
        }
    }
    private void inOrder (Node rootNode){
        if (rootNode != null) {
            inOrder(rootNode.leftChild);
            rootNode.display();
            inOrder(rootNode.rightChild);
        }
    }

    public Node min(){
        Node current, last = null;
        current = root;
        while (current != null){
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    public Node max(){
        Node current, last = null;
        current = root;
        while (current != null){
            last = current;
            current = current.rightChild;
        }
        return  last;
    }

    public boolean delete (int id){
        Node current = root;
        Node parent = root;

        boolean isLeftChild = true;
        while (current.person.id != id){
            parent = current;
            if(id < current.person.id){
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null){
                return false;
            }
        }
            if(current.leftChild == null && current.rightChild == null){
                if(current == root){
                    root = null;
                } else if (isLeftChild){
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (current.rightChild == null){
                if(current == null){
                    root = current.leftChild;
                }else if(isLeftChild){
                    parent.leftChild = current.leftChild;
                }else {
                    parent.rightChild = current.leftChild;
                }
            } else if (current.leftChild == null){
                if(current == null){
                    root = current.rightChild;
                } else if (isLeftChild){
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            } else {
                Node successor = getSuccesor(current);
                if(current == root){
                    root = successor;
                }else if(isLeftChild){
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
                successor.leftChild = current.leftChild;
            }
            return true;
    }

    public Node getSuccesor(Node node){
        Node successorParent = node;
        Node successor = node;
        Node current = node.rightChild;

        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if(successor != node.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild;
        }
        return successor;
    }
    public void displayTree() {
        Node current = root;
        System.out.println("Симетричный");
        inOrder(root);
        System.out.println("Прямой");
        preOrder(root);
        System.out.println("Обратный");
        postOrder(current);
    }
}
public class Main {

    public static void main(String[] args) {

        Tree theThree = new Tree();

        theThree.insert(new Person(3, "Roman" ,33));
        theThree.insert(new Person(5, "Ivan", 29));
        theThree.insert(new Person(2, "Kolya", 27));
        theThree.insert(new Person(6, "Dmitriy", 34));

        theThree.max().display();
        theThree.min().display();

        theThree.find(5).display();

        theThree.delete(2);
        System.out.println();
        theThree.displayTree();

    }
}
