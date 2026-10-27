package Java2;

import com.codeborne.selenide.commands.ancestor.AncestorResult;

public class TypeMain {


    public static void main(String[] args) {

        // задание 1
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwnerName("Сергей");
        bankAccount.setBalance(500000);
        bankAccount.setBalance(-500);

        System.out.println(bankAccount.getBalance());

        // задание 2 старая реализация
        Person person = new Person();
        person.setFirstName("Иван");
        person.setLastName("Иванович");
        person.setAge(20);
        person.introduce();

        // задание 2 новая реализация
        Person person1 = new Person("Иван", "Иванович", 25);
        person1.introduce();

        // задание 3
        System.out.println(MathHelper.sum(10, 10));
        System.out.println(MathHelper.max(1200, 320));
        System.out.println(MathHelper.isEven(21));

        //задание 5



    }
}