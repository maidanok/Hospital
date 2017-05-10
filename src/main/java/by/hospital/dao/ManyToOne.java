package by.hospital.dao;

import by.hospital.domain.Entity;
import by.hospital.exception.PersistentException;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * Created by Admin on 09.04.2017.
 */
//Класс отвечает за работу с полем, ссылающимся на хранимый объект нигде не используется
// @param <Owner>      класс объекта, чье поле ссылается на зависимый объект.
// @param <Dependence> класс зависимого объекта.
public class ManyToOne<Owner extends Entity, Dependence extends Entity> {
    private DaoFactory<Connection> factory; //фабрика фабрик которая в зависимости от типа объекта отдаст нужную фабрику для сохранения или апдейта элемента
    private Field field;
    private String name;
    private int hash;

    public Dependence getDependence(Owner owner) throws IllegalAccessException {
        return (Dependence) field.get(owner); //TODO что именно он тут отдаст????
    }

    public void setDependence(Owner owner, Dependence dependence) throws IllegalAccessException {
        field.set(owner, dependence); //сохраняет внутри себя ключ owner и значение dependence именно как объект
    }

    //persist и update 2 метода которые сохраняют в базу или изменяют объект dependence
    public Entity persistDependence(Owner owner, Connection connection) throws PersistentException, IllegalAccessException {
        return factory.getDao(connection, field.getType()).persist(getDependence(owner));
    }

    public void updateDependence(Owner owner, Connection connection) throws IllegalAccessException, PersistentException {
        factory.getDao(connection, field.getType()).update(getDependence(owner));
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return hash;
    }

    public ManyToOne(Class<Owner> ownerClass, DaoFactory<Connection> factory, String field) throws NoSuchFieldException {
        this.factory = factory;
        this.field = ownerClass.getDeclaredField(field); //вот тут самое важное мы получаем поле у класса ownerClass после его создания
        this.field.setAccessible(true);
        name = ownerClass.getSimpleName() + " to " + this.field.getType().getSimpleName();
        hash = ownerClass.hashCode() & field.hashCode();
        //что бы hash был уникальным сы его получаем из сложения hash owner и вложеного поля если вложенных полей несколько то hash будет для каждого уникальный
    }
}
/**
 * В частности реализация методов getDependence и setDependence очевидно требуют
 * ссылки на объект класса Owner и дополнительной информации непосредственно о том поле,
 * которое ссылается на зависимый объект. Чтобы реализовать методы persistDependence и updateDependence
 * необходима ссылка на фабрику дао-объектов и класс зависимого объекта
 */
