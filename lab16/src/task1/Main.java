package lab16.src.task1;

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("Іван Петренко");
        invoice.addCharge(new AccommodationCharge("Проживання, номер 305 (3 ночі)", 4500.00));
        invoice.addCharge(new FoodCharge("Сніданки та вечері", 1200.00));
        invoice.addCharge(new SpaCharge("SPA-процедури", 1800.00));
        invoice.addCharge(new RentalCharge("Прокат велосипеда", 300.00));
        invoice.addCharge(new TouristTaxCharge("Туристичний збір", 150.00));

        System.out.println("Рахунок гостя: " + invoice.getGuestName());
        System.out.println();

        System.out.println("=== Операція 1: розрахунок податків ===");
        TaxVisitor taxVisitor = new TaxVisitor();
        invoice.apply(taxVisitor);
        System.out.printf("Загальна сума податків: %.2f грн%n", taxVisitor.getTotalTax());

        System.out.println();
        System.out.println("=== Операція 2: нарахування бонусних балів ===");
        BonusPointsVisitor bonusVisitor = new BonusPointsVisitor();
        invoice.apply(bonusVisitor);
        System.out.println("Загальна кількість бонусних балів: " + bonusVisitor.getTotalPoints());

        System.out.println();
        System.out.println("=== Операція 3: звіт для туроператора ===");
        TourOperatorReportVisitor reportVisitor = new TourOperatorReportVisitor();
        invoice.apply(reportVisitor);
        System.out.println("Позицій у звіті: " + reportVisitor.getItems());
        System.out.printf("Загальна сума за звітом: %.2f грн%n", reportVisitor.getTotalAmount());
    }
}
