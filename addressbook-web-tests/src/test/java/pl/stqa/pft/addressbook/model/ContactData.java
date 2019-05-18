package pl.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Transient
  private String allEmails;
  @Transient
  private String allDetails;

  @Column(name = "photo")
  @Type(type = "text")
  @Transient
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
    joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public File getPhoto() {
    if (photo != null)
      return new File(photo);
    return null;
  }

  public ContactData withPhoto(File photo) {
    if (photo != null) {
      this.photo = photo.getPath();
    }
    return this;
  }

  public String getAllDetails() {
    return allDetails;
  }

  public ContactData withAllDetails(String allDetails) {
    this.allDetails = allDetails;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
      Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName) &&
      Objects.equals(homePhone, that.homePhone) &&
      Objects.equals(mobilePhone, that.mobilePhone) &&
      Objects.equals(workPhone, that.workPhone) &&
      Objects.equals(allPhones, that.allPhones) &&
      Objects.equals(email, that.email) &&
      Objects.equals(email2, that.email2) &&
      Objects.equals(email3, that.email3) &&
      Objects.equals(allEmails, that.allEmails) &&
      Objects.equals(allDetails, that.allDetails) &&
      Objects.equals(photo, that.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, homePhone, mobilePhone, workPhone, allPhones,
      email, email2, email3, allEmails, allDetails, photo);
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public String toString() {
    return "ContactData{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", homePhone='" + homePhone + '\'' +
      ", mobilePhone='" + mobilePhone + '\'' +
      ", workPhone='" + workPhone + '\'' +
      ", allPhones='" + allPhones + '\'' +
      ", email='" + email + '\'' +
      ", email2='" + email2 + '\'' +
      ", email3='" + email3 + '\'' +
      ", allEmails='" + allEmails + '\'' +
      ", allDetails='" + allDetails + '\'' +
      ", photo='" + photo + '\'' +
      '}';
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

}