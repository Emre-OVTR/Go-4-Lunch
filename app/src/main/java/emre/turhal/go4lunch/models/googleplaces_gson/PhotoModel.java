package emre.turhal.go4lunch.models.googleplaces_gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotoModel {

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("photo_reference")
    @Expose
    private String photoReference;

    @SerializedName("width")
    @Expose
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
