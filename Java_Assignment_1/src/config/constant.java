//raw: 12.5% of the item cost
//        manufactured: 12.5% of the item cost + 2% of (item cost + 12.5% of the item cost)
//        imported: 10% import duty on item cost + a surcharge (surcharge is: Rs. 5 if the final cost
//        after applying tax & import duty is up to Rs. 100, Rs. 10 if the cost exceeds 100 and up to 200
//        and 5% of the final cost if it exceeds 200)

package config;

public class constant {
    public static final double RAW_AND_MANUFACTURE_TAX = 0.125;
    public static final double IMPORT_DUTY = 0.10;
    public static final double SURCHARGE_UPTO_100 = 5;
    public static final double SURCHARGE_BETWEEN_100_TO_200 = 10;
    public static final double SURCHARGE_PERCENT_AFTER_200 = 0.05;
}
