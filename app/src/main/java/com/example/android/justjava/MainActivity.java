package com.example.android.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int pcsOfCups = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        //displayPrice(price);
        createOrderSummary("Aslan Kashiev", pcsOfCups, price);
    }
    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = pcsOfCups * 50;
        return price;
    }

    /**
     * Метод выводит на экран Имя, количество чашек , стоимость заказа и "Thank you"
     * @param name имя клиента
     * @param quantity количество чашек кофе
     * @param totalPrice стоимость заказа
     */
    private void createOrderSummary(String name, int quantity, int totalPrice) {
        displayMessage(
                "Имя: " + name + "\n" +
                "Количество: " + quantity + "\n" +
                "Стоимость: " + NumberFormat.getCurrencyInstance().format(totalPrice) + "\n" +
                "Thank you!"
        );
    }
    /**
     * Этот метод выводит
     * @param message на экран
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    public void increment(View view) {
        pcsOfCups++;
        display(pcsOfCups);
    }

    public void decrement(View view) {
        if (pcsOfCups > 0)
            pcsOfCups--;
            display(pcsOfCups);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityNumber);
        quantityTextView.setText("" + number);
    }
}
