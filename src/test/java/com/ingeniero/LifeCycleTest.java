package com.ingeniero;

import com.ingeniero.ingeniero.HibernateUtil;
import com.ingeniero.ingeniero.model.Address;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

/*
1. transient
2. Managed
3.  Detached
4. Removed
5. New

 */
public class LifeCycleTest {


    @Test
    void transientState(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address  addess = new Address("Calle 1", "Ciudad", "Pais");
        System.out.println(session.contains(addess));

        //couando no es managed puede generar un error si se asocia a una entidad

        /*
        No funciona porque no es managed y no persiste en la base de datos

        var author = new Author("Juan", "Perez", null);
        author.setAddress(address);
        session.beginTransaction();
        session.persist(author);
        session.getTransaction().commit();

         */
    }

    @Test
    void transientStateUpdate(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address  address = new Address("Calle 1", "Ciudad", "Pais");
        address.setId(1L);

        session.beginTransaction();
       // session.persist(address);
        //Gestiona el estado de la entidad
        session.merge(address);
        session.getTransaction().commit();


        session.detach(address);

        //opcion find
        session.beginTransaction();
        //session.persist(address);
       var addres1=  session.find(Address.class, 1L);
         addres1.setCity("Ciudad 2");
       session.persist(addres1);

        session.getTransaction().commit();


    }

    @Test
    void managedState(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address  addess = new Address("Calle 1", "Ciudad", "Pais");

        session.beginTransaction();
        session.persist(addess);
        session.getTransaction().commit();

        System.out.println(session.contains(addess));
    }

    @Test
    void removedState(){
        Session session = HibernateUtil.getSessionFactory().openSession();
       // Address  address = new Address("Calle 1", "Ciudad", "Pais");
        Address address = new Address();
        address.setId(2L);

        session.beginTransaction();
        session.remove(address);
        session.getTransaction().commit();

        System.out.println(session.contains(address));
    }

    @Test
    void differentSession(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = session.find(Address.class, 3L);
        System.out.println(session.contains(address));

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(session.contains(address));

    }


    @Test
    void load(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        //no hace consulta hasta el get country
        Address address = session.getReference(Address.class, 3L);
        System.out.println("============");
        System.out.println(address.getCountry());


        //obtiene el objeto en la llamada
        Address address2 = session.find(Address.class, 3L);
        System.out.println("============");
        System.out.println(address2.getCountry());

       /*
        //no funcionaria ya que  cuando hace la consulta ha close con find si funcionaria
        Address address = session.getReference(Address.class, 3L);
        System.out.println("============");
        session.close();
        System.out.println(address.getCountry());*/
    }

}
