import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    public static double fractionalKnapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerUnitWeight).reversed());

        double maxValue = 0;
        for (Item item : items) {
            if (capacity <= 0) {
                break;
            }
            int weightToTake = Math.min(item.weight, capacity);
            maxValue += weightToTake * item.valuePerUnitWeight();
            capacity -= weightToTake;
        }

        return maxValue;
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        double maxTotalValue = fractionalKnapsack(values, weights, capacity);
        System.out.println("Maximum total value: " + maxTotalValue);
    }

    static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public double valuePerUnitWeight() {
            return (double) value / weight;
        }
    }
}
