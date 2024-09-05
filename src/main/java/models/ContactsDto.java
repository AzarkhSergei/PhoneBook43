package models;

import java.util.List;

public class ContactsDto {

  private List<Contact> contacts;

  public List<Contact> getContacts() {
    return contacts;
  }

  public ContactsDto(){

  }

  @Override
  public String toString() {
    return "ContactsDto{" +
        "contacs=" + contacts +
        '}';
  }
}
