package br.com.mestihudson.refactoring;

import org.junit.*;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;

public class CustomerTest {
  private static Logger logger = Logger.getLogger(CustomerTest.class);

  @Test public void customer_statement_with_none_rental() {
    assert_customer_statement_with_none_rental(null);
    assert_customer_statement_with_none_rental("");
    assert_customer_statement_with_none_rental("João");
  }

  private void assert_customer_statement_with_none_rental(String name) {
    Customer customer = customer(name);
    Assert.assertThat(name, Matchers.equalTo(customer.getName()));
    String actual = String.format("Rental Record for %s\nAmount owed is 0.0\nYou earned 0 frequent renter points", name);
    Assert.assertThat(customer.statement(), Matchers.equalTo(actual));
  }

  private Customer customer(String name) {
    return new Customer(name);
  }

  private Customer customer(String name, Rental... rentals) {
    Customer customer = new Customer(name);
    for(Rental rental : rentals) {
      customer.addRental(rental);
    }
    return customer;
  }

  @Test public void customer_statement_with_rentals() {
    assert_customer_statement_with_rentals("With One Rental", "2.0", "2.0", "1", rental(0, 0));
    assert_customer_statement_with_rentals("With One Rental", "2.0", "2.0", "1", rental(0, 1));
    assert_customer_statement_with_rentals("With One Rental", "2.0", "2.0", "1", rental(0, 2));
    assert_customer_statement_with_rentals("With One Rental", "0.0", "0.0", "1", rental(1, 0));
    assert_customer_statement_with_rentals("With One Rental", "3.0", "3.0", "1", rental(1, 1));
    assert_customer_statement_with_rentals("With One Rental", "6.0", "6.0", "2", rental(1, 2));
    assert_customer_statement_with_rentals("With One Rental", "1.5", "1.5", "1", rental(2, 0));
    assert_customer_statement_with_rentals("With One Rental", "1.5", "1.5", "1", rental(2, 1));
    assert_customer_statement_with_rentals("With One Rental", "1.5", "1.5", "1", rental(2, 2));

    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 0), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 0), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,0.0", "2.0", "2", rental(0, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,3.0", "5.0", "2", rental(0, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,6.0", "8.0", "3", rental(0, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 0), rental(2, 2));
    //assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 1), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 1), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,0.0", "2.0", "2", rental(0, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,3.0", "5.0", "2", rental(0, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,6.0", "8.0", "3", rental(0, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 1), rental(2, 2));
    //assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 2), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 2), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,2.0", "4.0", "2", rental(0, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,0.0", "2.0", "2", rental(0, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,3.0", "5.0", "2", rental(0, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,6.0", "8.0", "3", rental(0, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "2.0,1.5", "3.5", "2", rental(0, 2), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,2.0", "2.0", "2", rental(1, 0), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,2.0", "2.0", "2", rental(1, 0), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,2.0", "2.0", "2", rental(1, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,0.0", "0.0", "2", rental(1, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,3.0", "3.0", "2", rental(1, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,6.0", "6.0", "3", rental(1, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,1.5", "1.5", "2", rental(1, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,1.5", "1.5", "2", rental(1, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "0.0,1.5", "1.5", "2", rental(1, 0), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,2.0", "5.0", "2", rental(1, 1), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,2.0", "5.0", "2", rental(1, 1), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,2.0", "5.0", "2", rental(1, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,0.0", "3.0", "2", rental(1, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,3.0", "6.0", "2", rental(1, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,6.0", "9.0", "3", rental(1, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,1.5", "4.5", "2", rental(1, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,1.5", "4.5", "2", rental(1, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "3.0,1.5", "4.5", "2", rental(1, 1), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,2.0", "8.0", "3", rental(1, 2), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,2.0", "8.0", "3", rental(1, 2), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,2.0", "8.0", "3", rental(1, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,0.0", "6.0", "3", rental(1, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,3.0", "9.0", "3", rental(1, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,6.0", "12.0", "4", rental(1, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,1.5", "7.5", "3", rental(1, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,1.5", "7.5", "3", rental(1, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "6.0,1.5", "7.5", "3", rental(1, 2), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 0), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 0), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,0.0", "1.5", "2", rental(2, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,3.0", "4.5", "2", rental(2, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,6.0", "7.5", "3", rental(2, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 0), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 1), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 1), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,0.0", "1.5", "2", rental(2, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,3.0", "4.5", "2", rental(2, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,6.0", "7.5", "3", rental(2, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 1), rental(2, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 2), rental(0, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 2), rental(0, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,2.0", "3.5", "2", rental(2, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,0.0", "1.5", "2", rental(2, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,3.0", "4.5", "2", rental(2, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,6.0", "7.5", "3", rental(2, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Two Rentals", "1.5,1.5", "3.0", "2", rental(2, 2), rental(2, 2));

    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 0), rental(0, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 0), rental(0, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,0.0", "4.0", "3", rental(0, 0), rental(0, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,3.0", "7.0", "3", rental(0, 0), rental(0, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,6.0", "10.0", "4", rental(0, 0), rental(0, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 0), rental(2, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 1), rental(0, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 1), rental(0, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,0.0", "4.0", "3", rental(0, 0), rental(0, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,3.0", "7.0", "3", rental(0, 0), rental(0, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,6.0", "10.0", "4", rental(0, 0), rental(0, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 1), rental(2, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 2), rental(0, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 2), rental(0, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,2.0", "6.0", "3", rental(0, 0), rental(0, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,0.0", "4.0", "3", rental(0, 0), rental(0, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,3.0", "7.0", "3", rental(0, 0), rental(0, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,6.0", "10.0", "4", rental(0, 0), rental(0, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,2.0,1.5", "5.5", "3", rental(0, 0), rental(0, 2), rental(2, 2));

    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,2.0", "4.0", "3", rental(0, 0), rental(1, 0), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,2.0", "4.0", "3", rental(0, 0), rental(1, 0), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,2.0", "4.0", "3", rental(0, 0), rental(1, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,0.0", "2.0", "3", rental(0, 0), rental(1, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,3.0", "5.0", "3", rental(0, 0), rental(1, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,6.0", "8.0", "4", rental(0, 0), rental(1, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,1.5", "3.5", "3", rental(0, 0), rental(1, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,1.5", "3.5", "3", rental(0, 0), rental(1, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,0.0,1.5", "3.5", "3", rental(0, 0), rental(1, 0), rental(2, 2));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,2.0", "7.0", "3", rental(0, 0), rental(1, 1), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,2.0", "7.0", "3", rental(0, 0), rental(1, 1), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,2.0", "7.0", "3", rental(0, 0), rental(1, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,0.0", "5.0", "3", rental(0, 0), rental(1, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,3.0", "8.0", "3", rental(0, 0), rental(1, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,6.0", "11.0", "4", rental(0, 0), rental(1, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,1.5", "6.5", "3", rental(0, 0), rental(1, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,1.5", "6.5", "3", rental(0, 0), rental(1, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,3.0,1.5", "6.5", "3", rental(0, 0), rental(1, 1), rental(2, 2));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,2.0", "10.0", "4", rental(0, 0), rental(1, 2), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,2.0", "10.0", "4", rental(0, 0), rental(1, 2), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,2.0", "10.0", "4", rental(0, 0), rental(1, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,0.0", "8.0", "4", rental(0, 0), rental(1, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,3.0", "11.0", "4", rental(0, 0), rental(1, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,6.0", "14.0", "5", rental(0, 0), rental(1, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,1.5", "9.5", "4", rental(0, 0), rental(1, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,1.5", "9.5", "4", rental(0, 0), rental(1, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,6.0,1.5", "9.5", "4", rental(0, 0), rental(1, 2), rental(2, 2));

    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 0), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 0), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 0), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,0.0", "3.5", "3", rental(0, 0), rental(2, 0), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,3.0", "6.5", "3", rental(0, 0), rental(2, 0), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,6.0", "9.5", "4", rental(0, 0), rental(2, 0), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 0), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 0), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 0), rental(2, 2));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 1), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 1), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 1), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,0.0", "3.5", "3", rental(0, 0), rental(2, 1), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,3.0", "6.5", "3", rental(0, 0), rental(2, 1), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,6.0", "9.5", "4", rental(0, 0), rental(2, 1), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 1), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 1), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 1), rental(2, 2));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 2), rental(0, 0));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 2), rental(0, 1));
    //assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,2.0", "5.5", "3", rental(0, 0), rental(2, 2), rental(0, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,0.0", "3.5", "3", rental(0, 0), rental(2, 2), rental(1, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,3.0", "6.5", "3", rental(0, 0), rental(2, 2), rental(1, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,6.0", "9.5", "4", rental(0, 0), rental(2, 2), rental(1, 2));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 2), rental(2, 0));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 2), rental(2, 1));
    assert_customer_statement_with_rentals("With Three Rentals", "2.0,1.5,1.5", "5.0", "3", rental(0, 0), rental(2, 2), rental(2, 2));
  }

  private void assert_customer_statement_with_rentals(String name, String v1, String v2, String v3, Rental...rentals) {
    Customer customer = customer(name, rentals);
    String r = "";
    for(String v : v1.split(",")){
      r = r.concat(String.format("\tnull\t%s\n", v));
    }
    String actual = String.format("Rental Record for %s\n%sAmount owed is %s\nYou earned %s frequent renter points", name, r, v2, v3);
    Assert.assertThat(name, Matchers.equalTo(customer.getName()));
    Assert.assertThat(customer.statement(), Matchers.equalTo(actual));
  }

  private Rental rental(Movie movie, int days) {
    return new Rental(movie, days);
  }

  private Rental rental(int moviePriceCode, int days) {
    return new Rental(movie(moviePriceCode), days);
  }

  private Movie movie(String title, int priceCode) {
    return new Movie(title, priceCode);
  }

  private Movie movie(int priceCode) {
    return movie(null, priceCode);
  }
}
