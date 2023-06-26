package com.rate.api.service;

public interface ImageService {
    byte[] compressImage(byte[] data);
    byte[] decompressImage(byte[] data);
}
