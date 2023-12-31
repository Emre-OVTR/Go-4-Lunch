package emre.turhal.go4lunch.ui.workmates_list;

import static emre.turhal.go4lunch.utils.GetTodayDate.getTodayDate;

import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import emre.turhal.go4lunch.R;
import emre.turhal.go4lunch.databinding.FragmentWorkmatesItemBinding;
import emre.turhal.go4lunch.firebase.manager.BookingManager;
import emre.turhal.go4lunch.models.Workmate;
import emre.turhal.go4lunch.utils.ChangeColorWorkmate;

public class WorkmatesViewHolder extends RecyclerView.ViewHolder {

    FragmentWorkmatesItemBinding mBinding;
    private BookingManager mBookingManager = BookingManager.getInstance();

    public WorkmatesViewHolder(@NonNull FragmentWorkmatesItemBinding itemView) {
        super(itemView.getRoot());
        mBinding = itemView;
    }


    public void updateWithWorkmate(Workmate workmate){

        Glide.with(itemView).load(workmate.getUrlPicture()).apply(RequestOptions.circleCropTransform()).into(mBinding.itemListAvatar);

        mBookingManager.getBooking(workmate.getUid(), getTodayDate()).addOnCompleteListener(restaurantTask -> {
            if (restaurantTask.isSuccessful()) {
                if (restaurantTask.getResult().size() == 1) {
                    for (QueryDocumentSnapshot restaurant : restaurantTask.getResult()) {
                        this.mBinding.itemListName.setText(itemView.getResources().getString(R.string.eating_at, workmate.getName(), restaurant.getData().get("restaurantName")));
                        mBinding.itemListName.setTypeface(mBinding.itemListName.getTypeface(), Typeface.BOLD);
                    }

                } else {
                    mBinding.itemListName.setText(itemView.getResources().getString(R.string.hasnt_decided, workmate.getName()));
                    ChangeColorWorkmate.changeTextColor(R.color.colorGrey1, mBinding.itemListName);
                    mBinding.itemListName.setTypeface(mBinding.itemListName.getTypeface(), Typeface.ITALIC);
                }
            }
        });
    }
}
