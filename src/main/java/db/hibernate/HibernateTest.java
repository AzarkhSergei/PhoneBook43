package db.hibernate;

import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import jakarta.persistence.Query;
import models.Contact;
import org.hibernate.Session;

import java.util.UUID;

public class HibernateTest {
  public static void main(String[] args) {
    String id = addNewContact().getId();
    Contact cont = getContactById(id);
    System.out.println(cont.toString());
  }


  public static Contact addNewContact(){
    Contact contact = new Contact(
        NameAndLastNameGenerator.generateName()
        ,NameAndLastNameGenerator.generateLastName()
        , EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.VALID)
        , PhoneNumberGenerator.generatePhoneNumber()
        , AddressGenerator.generateAddress()
        ,"Test desc...");

    try (Session session = HibernateUtil.getSession()){
      session.beginTransaction();
      String id = String.valueOf(UUID.randomUUID());
      contact.setId(id);
      System.out.println("ID: "+id);
      session.save(contact);
      session.getTransaction().commit();
    }catch (Throwable throwable){

    }
    return contact;
  }

  public static Contact getContactById(String id){
    Contact contact = null;
    try(Session session = HibernateUtil.getSession()){
      session.beginTransaction();
      Query query = session.createQuery("from Contact where id = :id");
      query.setParameter("id", id);
      contact = (Contact) query.getSingleResult();
      session.getTransaction().commit();
    }catch (Throwable throwable){}
    return contact;
  }



}
