package by.hospital.dao;

import by.hospital.exception.PersistentException;

/**
 * Created by Admin on 08.04.2017.
 */


// Фабрика объектов для работы с базой данных
public interface DaoFactory<Context> {

//создает нужное дао и принимает контекст для MySql - connection
    interface DaoCreator<Context> {
        public GenericDAO create(Context context);
    }

    //возвращает подключение к данным MySql - connection
    Context getContext() throws PersistentException;

    //возвращает объект для управления персистентным состояние объекта
    GenericDAO getDao(Context context, Class dtoClass) throws PersistentException;
}

/* мы будем заводить Map с ключем типа Class, которая будет возвращать нам
    экземпляры соответствующий ключу дао объектов

* Context - сущность, описывающая сеанс взаимодействия с системой хранения данных,
 * в случае с JDBC в качестве контекста выступает Connection.
 */
