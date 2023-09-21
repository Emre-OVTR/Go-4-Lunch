package emre.turhal.go4lunch.utils;

import android.view.View;
import android.widget.RatingBar;

import emre.turhal.go4lunch.models.googleplaces_gson.ResultDetails;

public class DisplayRating {

    public static void displayRating(ResultDetails results, RatingBar mRatingBar) {
        if (results.getRating() != null) {
            double googleRating = results.getRating();
            double MAX_RATING = 5;
            double MAX_STAR = 3;
            double rating = googleRating / MAX_RATING * MAX_STAR;
            mRatingBar.setRating((float) rating);
            mRatingBar.setVisibility(View.VISIBLE);
        } else {
            mRatingBar.setVisibility(View.GONE);
        }
    }
}