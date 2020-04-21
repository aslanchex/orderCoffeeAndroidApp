package com.example.android.justjava;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int pcsOfCups = 1;
    Toast toast;

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
     * <p>
     * Стоимость кофе без добавок - 50 р, сливки - 15 р, шоколад - 25р.
     * <p>
     * Если поле для ввода имени пустое, то заказ не совершается.
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
        if (hasWhippedCream) {
            price += pcsOfCups * 15;
        }
        if (hasChocolate) {
            price += pcsOfCups * 25;
        }
        if (!name.isEmpty()) {
            createOrderSummary(name, pcsOfCups, price, hasWhippedCream, hasChocolate);
        } else {
            editText.setHintTextColor(getResources().getColor(R.color.hintTextColor));
        }
    }

    /**
     * Метод вычисляет стоимость чашек кофе
     */
    private int calculatePrice() {
        int price = pcsOfCups * 50;
        return price;
    }

    /**
     * Метод выводит на экран Имя, количество чашек , стоимость заказа и "Thank you"
     *
     * @param name       имя клиента
     * @param quantity   количество чашек кофе
     * @param totalPrice стоимость заказа
     */
    private void createOrderSummary(String name, int quantity, int totalPrice, boolean hasWhippedCream, boolean hasChocolate) {
        Resources res = this.getResources();

        displayMessage(
                res.getString(R.string.order_summary_name, name) + "\n" +
                res.getString(R.string.order_summary_whipped_cream) + " "  + (hasWhippedCream ? "Да" : "Нет") + "\n" +
                res.getString(R.string.order_summary_chocolate) + " "  + (hasChocolate ? "Да" : "Нет") + "\n" +
                res.getString(R.string.order_summary_quantity) + " " + quantity + "\n" +
                res.getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(totalPrice)) + "\n" +
                res.getString(R.string.thank_you)
        );
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*\/*");
        intent.putExtra(Intent.EXTRA_EMAIL, "aslan.kashiev@mail.ru");
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.order_summary_email_subject);
        intent.putExtra(Intent.EXTRA_TEXT,
                R.string.order_summary_name + name + "\n" +
                        R.string.order_summary_whipped_cream + (hasWhippedCream ? "Да" : "Нет") + "\n" +
                        String.valueOf(R.string.order_summary_chocolate).toString() + (hasChocolate ? "Да" : "Нет") + "\n" +
                        R.string.order_summary_quantity + quantity + "\n" +
                        R.string.order_summary_price + NumberFormat.getCurrencyInstance().format(totalPrice) + "\n" +
                        R.string.thank_you
        );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/
    }

    /**
     * Этот метод выводит
     *
     * @param message на экран
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * Метод увеличивает счетчик кофе на 1
     *
     * @param view
     */
    public void increment(View view) {
        if (pcsOfCups < 100) {
            pcsOfCups++;
            display(pcsOfCups);
        } else {
            toast = Toast.makeText(getApplicationContext(), "Нельзя заказать больше 100 чашек", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Метод уменьшает счетчик кофе на 1
     *
     * @param view
     */
    public void decrement(View view) {
        if (pcsOfCups > 1) {
            pcsOfCups--;
            display(pcsOfCups);
        } else {
            toast = Toast.makeText(getApplicationContext(), "Невозможно заказать меньше 1 чашки", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }

    /**
     * Метод выводит количество заказанных чашек
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantityNumber);
        quantityTextView.setText("" + number);
    }
}
