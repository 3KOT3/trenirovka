package Java2;

public class TypeMain {


    public static void main(String[] args) {

        // задание 1
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(-500);
        System.out.println(bankAccount.getBalance());

        // задание 2 старая реализация
//        Person person = new Person();
//        person.lastName = "Иванов";
//        person.firstName = "Иван";
//        person.age = 18;
//        person.introduce();

        // задание 2 новая реализация
        Person person1 = new Person("Иванов", "Иван", 29);
        Person person2 = new Person("Иванов", "Стас", 40);
        Person person3 = new Person("Иванов", "Джон", 18);
        person1.introduce();
        person2.introduce();
        person3.introduce();

        // задание 3
        System.out.println(MathHelper.sumTotal(5, 10));
        System.out.println(MathHelper.maxNumber(-1, 12));
        System.out.println(MathHelper.isEven(-99));

        // задание 4
        Visitor visitor = new Visitor();
        Visitor visitor1 = new Visitor();
        Visitor visitor2 = new Visitor();
        Visitor visitor3 = new Visitor();
        Visitor visitor4 = new Visitor();

        System.out.println(visitor.getTotalVisitors());
        System.out.println(visitor1.getTotalVisitors());
        System.out.println(visitor2.getTotalVisitors());
        System.out.println(visitor3.getTotalVisitors());
        System.out.println(visitor4.getTotalVisitors());

        /*переменная totalVisitors статичная - значит одна на весь класс, а не у каждого объекта своя.
        и получается мы просто получаем для каждого обхекта класса одну и туже переменную
        */

        // задание 5
        Product product = new Product("Банан", 10);
        Product product2 = new Product("Вишня", 100);
        Product product3 = new Product("Яблоко", 4);

        Cart cart = new Cart();
        cart.addProduct(product, 1);
        cart.addProduct(product, 1);
        cart.addProduct(product2, 3);
        cart.addProduct(product3, 4);

        System.out.println(cart.getProductCount());
        System.out.println(cart.getTotalPrice());
    }
}