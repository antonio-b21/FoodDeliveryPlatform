package FoodDelivery;

abstract class CsvCompatible {
    abstract String csvHeader();
    abstract String csvString();
    abstract CsvCompatible csvObject(String csvString);
}
