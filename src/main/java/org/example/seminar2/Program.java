package org.example.seminar2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Program {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        task01();
        task02();
    }

    private static void task02() throws IllegalAccessException {
        Employee user = new Employee("Stanislav", "sample@gmail.com");
        UUID pk = UUID.randomUUID();
        QueryBuilder queryBuilder = new QueryBuilder();

        // Генерация SQL запроса для вставки
        String insertQuery =  queryBuilder.buildInsertQuery(user);
        System.out.println("Insert query: " + insertQuery);

        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.println("Select Query: " + selectQuery);

        user.setEmail("sample@mail.ru");

        //Генерация SQL-запроса на обновление данных
        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.println("Update Query: " + updateQuery);

        //Генерация SQL-запроса на удаление данных
        String deleteQuery = queryBuilder.buildUpdateQuery(user);
        System.out.println("Delete Query: " + deleteQuery);
    }

    private static void task01() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException {
        Class<?> personalClass = Class.forName("seminar02.Person");

        //получить список всех полей
        Field[] fields = personalClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Поле: " + field.getName());
        }

        //  Получение публичных полей
        Field[] fields1 = personalClass.getFields();
        for (Field field : fields1) {
            System.out.println("Поле: " + field.getName());
        }

        // Получить список всех конструкторов
        Constructor[] constructors = personalClass.getConstructors();

        // Создадим экземпляр класса
        Object personInstance = constructors[0].newInstance(null);

        //Устанавливаем значения полей
        Field nameField = personalClass.getDeclaredField("name");
        nameField.setAccessible(true);        // разрешаем доступ к закрытому полю
        nameField.set(personInstance, "Alex");
//        nameField.get(pernsonInstance);
        System.out.println(nameField.get(personInstance));

        Object personInstance2 = constructors[0].newInstance(null);
        Field ageField = personalClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(personInstance2, 30);
        nameField.set(personInstance2, "Alex");

        //вызов метода
        Method displayInfoMethod = personalClass.getDeclaredMethod("displayInfo");
        displayInfoMethod.invoke(personInstance2);
    }
}
