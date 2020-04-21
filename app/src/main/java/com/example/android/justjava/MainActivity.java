package com.example.android.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
     * Содержит поля:
     * price - стоимость заказа
     * hasWhippedCream - Добавил ли клент взбитые сливки
     * hasChocolate - добавил ли клиент шоколад
     *
     * Стоимость кофе без добавок - 50 р, сливки - 15 р, шоколад - 25р.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        boolean hasWhippedCream = false;
        boolean hasChocolate = false;
        String name = "";

        CheckBox checkBoxWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_chechbox); // Чекбокс взбитые сливки
        hasWhippedCream = checkBoxWhippedCream.isChecked();

        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.chocolate_chechbox); // Чекбокс шоколад
        hasChocolate = checkBoxChocolate.isChecked();

        EditText editText = (EditText) findViewById(R.id.input_name_view);
        name = editText.getText().toString();

        //Log.v("MainActivity", "The price is " +price);
        if (hasWhippedCream) {price += pcsOfCups * 15;}
        if (hasChocolate) {price += pcsOfCups * 25;}

        if (!name.isEmpty()) {
            createOrderSummary(name, pcsOfCups, price, hasWhippedCream, hasChocolate);
        }
        else {
            editText.setHintTextColor(getResources().getColor(R.color.hintTextColor));
        }
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
    private void createOrderSummary(String name, int quantity, int totalPrice, boolean hasWhippedCream, boolean hasChocolate) {
        displayMessage(
                "Имя: " + name + "\n" +
                "Добавить взбитые сливки? " + (hasWhippedCream ? "Да":"Нет") + "\n" +
                "Добавить шоколад? " + (hasChocolate ? "Да":"Нет") + "\n" +
                "Количество: " + quantity + "\n" +
                "Стоимость: " + NumberFormat.getCurrencyInstance().format(totalPrice) + "\n" +
                "Спасибо за заказ!"
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
