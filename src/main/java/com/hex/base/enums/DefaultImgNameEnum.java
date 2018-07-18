package com.hex.base.enums;

public enum DefaultImgNameEnum {

    MEETING_IMG("default_meeting_img.jpg"),
    MEETING_LOGO("default_meeting_logo.jpg"),
    PRODUCT_ICON("default_product_icon.jpg"),
    SATELLITE_MEETING_IMG("default_satelliteMeeting_img.jpg"),
    SPEAKER_PHOTO("default_speaker_photo.jpg"),;

    private String imgName;

    DefaultImgNameEnum(String imgName) {
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }
}
