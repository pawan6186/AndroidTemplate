package com.example.pawan.androidtemplate.theme;

import android.support.annotation.StyleRes;

import com.example.pawan.androidtemplate.R;

public class BlackThemeLoader implements IThemeLoader {
        @Override
        @StyleRes
        public int getApplicationThemeId() {
            return R.style.AppTheme_Black;
        }

}
