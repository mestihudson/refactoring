package br.com.mestihudson.refactoring;

class NewReleasePrice extends Price {
  int getPriceCode() {
    return Movie.NEW_RELEASE;
  }

  public double getCharge(int daysRented) {
    return daysRented * 3;
  }
}
