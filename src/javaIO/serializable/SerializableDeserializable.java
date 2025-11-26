package javaIO.serializable;

import java.io.*;
import java.nio.file.Path;

public class SerializableDeserializable {
    static void main() throws IOException, ClassNotFoundException {
        Person dima = new Person(12, "Dima");
        Path path = Path.of("resources", "student.out");
        //aby mozna było zapisac jakis obiekt do pliku - ma inplementowac interface Serializable
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            objectOutputStream.writeObject(dima);
        }

        try(ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(path.toFile()))){
            Object o = objectInputStream.readObject();//throw ClassNotFoundException
            if(o instanceof Person person){
                System.out.println(person);
            }
        }


    }

}
