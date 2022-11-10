package com.nof.vamathcalculator;

import android.app.Activity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.db.VAColumns;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;


import java.util.ArrayList;
import java.util.List;

public final class VAUtils {
    public static Double GetCombinedRating(List<Disability> disabilities){
        return 0.0;
    }
    public static List<Disability> GetBilateralDisabilities(){
        return new ArrayList<>();
    }
    public static List<Disability> GetNonBilateralDisabilities(){
        return new ArrayList<>();
    }
    public static List<Disability> GetDescendingDisabilityList(List<Disability> disabilities){
        return new ArrayList<>();
    }


    public abstract static class TextChangedListener<T> implements TextWatcher {
        private T target;
        public TextChangedListener(T target) {
            this.target = target;
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {
            this.onTextChanged(target, s);
        }
        public abstract void onTextChanged(T target, Editable s);
    }

    public static String get_smc_rating(Disability disability){
        String string_rating = new String();
        switch(disability.smc_rating){
            case VAColumns.SMCColumns.SMC_L:
                string_rating = "SMC-L";
                break;
            case VAColumns.SMCColumns.SMC_L_1_2:
                string_rating = "SMC-L 1/2";
                break;
            case VAColumns.SMCColumns.SMC_M:
                string_rating = "SMC-M";
                break;
            case VAColumns.SMCColumns.SMC_M_1_2:
                string_rating = "SMC-M 1/2";
                break;
            case VAColumns.SMCColumns.SMC_N:
                string_rating = "SMC-N";
                break;
            case VAColumns.SMCColumns.SMC_N_1_2:
                string_rating = "SMC-N 1/2";
                break;
            case VAColumns.SMCColumns.SMC_O_P:
                string_rating = "SMC-O/P";
                break;
            case VAColumns.SMCColumns.SMC_R_1:
                string_rating = "SMC-R.1";
                break;
            case VAColumns.SMCColumns.SMC_R_2:
                string_rating = "SMC-R.2/T";
                break;
            case VAColumns.SMCColumns.SMC_S:
                string_rating = "SMC-S";
        }
        return string_rating;
    }
    public static void LinkTextInViewFromStrings(
            TextView view,
            String text,
            String text_to_link,
            String url
    ) {
        VAUtils.setHyperLinkInTextView(
                view,
                text,
                text_to_link,
                url
        );
    }

    public static void LinkTextInViewFromResource(
            Activity context,
            TextView view,
            int text,
            int text_to_link,
            int url
    ) {
        String description_text = context.getString(text);
        String description_text_url = context.getString(url);
        String description_text_to_link = context.getString(text_to_link);

        VAUtils.setHyperLinkInTextView(
                view,
                description_text,
                description_text_to_link,
                description_text_url
        );
    }

    public static void setHyperLinkInTextView(TextView textView, String fullText, String wordTohyperLink, String fullTextUrl){
        if (TextUtils.isEmpty(fullText)){
            textView.setVisibility(View.GONE);
            return;
        }

        if (!TextUtils.isEmpty(fullText) && !TextUtils.isEmpty(wordTohyperLink) && !TextUtils.isEmpty(fullTextUrl)){
            SpannableString ssString = new SpannableString(fullText);
            int startLoc = fullText.indexOf(wordTohyperLink);
            int endLoc = fullText.indexOf(wordTohyperLink) + wordTohyperLink.length();
            //Log.e("setHyperLinkInTextView", Integer.toString(startLoc) + " " + Integer.toString(endLoc) + " " + fullText);
            ssString.setSpan(new URLSpan(fullTextUrl),startLoc,endLoc, 0);
            textView.setText(ssString);
            textView.setMovementMethod(LinkMovementMethod.getInstance());


            /*
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // We display a Toast. You could do anything you want here.
                    Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                }
            };
            ssString.setSpan(clickableSpan, startLoc, endLoc, 0);
            */

        }else{
            textView.setText(fullText);
        }
    }
}
