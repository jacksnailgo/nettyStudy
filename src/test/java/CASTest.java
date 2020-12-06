import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CASTest {


    public static void main(String[] args) {
        AtomicReferenceFieldUpdater<Person,String> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Person.class,String.class,"name");
        Person person = new Person();
        fieldUpdater.compareAndSet(person, person.name, "天王老子");
        System.out.println(person.name);
    }
}

class Person {
    volatile  String name = "abc";
}
