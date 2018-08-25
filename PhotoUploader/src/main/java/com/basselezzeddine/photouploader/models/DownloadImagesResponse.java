package com.basselezzeddine.photouploader.models;

import java.util.ArrayList;

public class DownloadImagesResponse {
    private ArrayList<String> base64Images;

    public DownloadImagesResponse(ArrayList<String> base64Images) {
        this.base64Images = base64Images;
    }

    public ArrayList<String> getBase64Images() {
        return base64Images;
    }
}
