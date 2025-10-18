package oop.lesson2_abstract_classes.task1;

//nie mozna tworzyc obiektu tej klasy(ma byc dziedziczony)
public abstract class Base {

    //abstract methods moga byc tylko w abstract class
    //wymusza nadpisanie tej metody u Derived klas
    public abstract void print();

    public void print2(){
        System.out.println("Base");
    }

}
