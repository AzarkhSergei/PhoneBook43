package models;

import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.*;
import java.util.Objects;

@Entity
@Table(name = "contacts")

public class Contact implements Serializable {
  @Id
  String id;
  String name;
  String lastName;
  String email;
  String phone;
  String address;
  String description;

  public Contact(String id, String name, String lastName,  String email, String phone, String address, String description) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact contact = (Contact) o;
    return Objects.equals(name, contact.name) && Objects.equals(lastName, contact.lastName) && Objects.equals(phone, contact.phone) && Objects.equals(email, contact.email) && Objects.equals(address, contact.address) && Objects.equals(description, contact.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastName, phone, email, address, description);
  }

  public Contact() {
  }

  public Contact(String name, String lastName, String email, String phone, String address, String description) {
    this.name = name;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Contact{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
        ", description='" + description + '\'' +
        '}';
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public static void serializationContact(Contact contact, String path) throws IOException {
    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
    outputStream.writeObject(contact);
  }

  public static Contact deserializationContact(String path) throws IOException, ClassNotFoundException {
    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
    return (Contact) inputStream.readObject();
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Contact contact = new Contact(
        NameAndLastNameGenerator.generateName(),
        NameAndLastNameGenerator.generateLastName(),
        PhoneNumberGenerator.generatePhoneNumber(),
        EmailGenerator.generateEmail(10,5,3, EmailGenerator.EmailType.VALID),
        AddressGenerator.generateAddress(),
        "Test desc...!!!");
    System.out.println(contact.toString());
    serializationContact(contact, "testcontact.ser");
    System.out.println("DE: " + deserializationContact("testcontact.ser").toString());
  }

}
