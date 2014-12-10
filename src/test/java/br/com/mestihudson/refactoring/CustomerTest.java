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
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t2.0\n", "2.0", "1", rental(movie(null, 0), 0));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t0.0\n", "0.0", "1", rental(movie(null, 1), 0));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t1.5\n", "1.5", "1", rental(movie(null, 2), 0));

    assert_customer_statement_with_rentals("With One Rental", "\tnull\t2.0\n", "2.0", "1", rental(movie(null, 0), 1));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t3.0\n", "3.0", "1", rental(movie(null, 1), 1));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t1.5\n", "1.5", "1", rental(movie(null, 2), 1));

    assert_customer_statement_with_rentals("With One Rental", "\tnull\t2.0\n", "2.0", "1", rental(movie(null, 0), 2));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t6.0\n", "6.0", "2", rental(movie(null, 1), 2));
    assert_customer_statement_with_rentals("With One Rental", "\tnull\t1.5\n", "1.5", "1", rental(movie(null, 2), 2));

    assert_customer_statement_with_rentals("With Two Rentals", "\tnull\t2.0\n\tnull\t2.0\n", "4.0", "2", rental(movie(null, 0), 0), rental(movie(null, 0), 0));
    assert_customer_statement_with_rentals("With Two Rentals", "\tnull\t2.0\n\tnull\t2.0\n", "4.0", "2", rental(movie(null, 0), 0), rental(movie(null, 0), 1));

    // assert_customer_statement_with_rentals("With Three Rentals", "\tnull\t2.0\n\tnull\t2.0\n\tnull\t2.0\n", "6.0", "3", rental(movie(null, 0), 0), rental(movie(null, 0), 0), rental(movie("", 0), 0));
    // assert_customer_statement_with_rentals("With Three Rentals", "\tnull\t2.0\n\tnull\t2.0\n\tnull\t2.0\n", "6.0", "3", rental(movie(null, 0), 0), rental(movie(null, 0), 0), rental(movie("", 1), 0));
  }

  private void assert_customer_statement_with_rentals(String name, String v1, String v2, String v3, Rental...rentals) {
    Customer customer = customer(name, rentals);
    String actual = String.format("Rental Record for %s\n%sAmount owed is %s\nYou earned %s frequent renter points", name, v1, v2, v3);
    Assert.assertThat(name, Matchers.equalTo(customer.getName()));
    Assert.assertThat(customer.statement(), Matchers.equalTo(actual));
  }

  private Rental rental(Movie movie, int days) {
    return new Rental(movie, days);
  }

  private Movie movie(String title, int priceCode) {
    return new Movie(title, priceCode);
  }
}
