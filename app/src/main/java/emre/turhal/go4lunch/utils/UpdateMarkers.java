package emre.turhal.go4lunch.utils;

import static emre.turhal.go4lunch.utils.GetTodayDate.getTodayDate;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

import emre.turhal.go4lunch.R;
import emre.turhal.go4lunch.firebase.manager.BookingManager;
import emre.turhal.go4lunch.ui.MainActivity;


public class UpdateMarkers {

    private static BookingManager mBookingManager = BookingManager.getInstance();

    public static void updateMarkers(GoogleMap map, MainActivity mMainActivity) {
        if (mMainActivity.mLiveData.getValue() != null) {
            map.clear();

            if(mMainActivity.mLiveData.getValue().size() > 0) {
                for (int i = 0; i < mMainActivity.mLiveData.getValue().size(); i++){
                    int finalI = i;
                    mBookingManager.getTodayBooking(mMainActivity.mLiveData.getValue().get(i).getPlaceId(), getTodayDate()).addOnCompleteListener(restaurantTask ->{
                        for (QueryDocumentSnapshot restaurant : restaurantTask.getResult()){
                            if (Objects.equals(restaurant.getData().get("restaurantId"), mMainActivity.mLiveData.getValue().get(finalI).getPlaceId())){
                                Double lat = mMainActivity.mLiveData.getValue().get(finalI).getGeometry().getLocationModel().getLat();
                                Double lng = mMainActivity.mLiveData.getValue().get(finalI).getGeometry().getLocationModel().getLng();
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(new LatLng(lat, lng));
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_green));
                                Marker marker = map.addMarker(markerOptions);
                                assert marker != null;
                                marker.setTag(mMainActivity.mLiveData.getValue().get(finalI).getPlaceId());
                                marker.showInfoWindow();
                            }
                        }
                    });

                }
            }

            if (mMainActivity.mLiveData.getValue().size() > 0) {
                for (int i = 0; i < mMainActivity.mLiveData.getValue().size(); i++) {
                    Double lat = mMainActivity.mLiveData.getValue().get(i).getGeometry().getLocationModel().getLat();
                    Double lng = mMainActivity.mLiveData.getValue().get(i).getGeometry().getLocationModel().getLng();
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(lat, lng));
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    Marker marker = map.addMarker(markerOptions);
                    assert marker != null;
                    marker.setTag(mMainActivity.mLiveData.getValue().get(i).getPlaceId());
                    marker.showInfoWindow();

                }
            }

        }
    }
}
