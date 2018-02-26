package com.shm.dim.lab_1;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class MyPasswordTransformationMethod extends PasswordTransformationMethod {

    char[] simbols = {'▲', '►', '▼', '◄', '■', '●', '○', '◊', '▬', '▪'};


    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {

        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            return generateRndSimbol(0, simbols.length - 1);
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }
    }

    public char generateRndSimbol(int min, int max) {
        return simbols[(int)(Math.random()*(max-min))+min];
    }
}