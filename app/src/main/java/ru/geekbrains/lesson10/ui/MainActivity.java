package ru.geekbrains.lesson10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.geekbrains.lesson10.R;
import ru.geekbrains.lesson10.publisher.Publisher;
import ru.geekbrains.lesson10.ui.main.SocialFragment;

public class MainActivity extends AppCompatActivity {

    // что бы не было утечек памяти создадим один Publisher на все приложение
    Publisher publisher;
    private Navigation navigation;

    public Publisher getPublisher() {
        return publisher;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        publisher = new Publisher();
        navigation = new Navigation(getSupportFragmentManager());

        if (savedInstanceState == null) {
            navigation.replaceFragment(SocialFragment.newInstance(),false);
        }
    }
}