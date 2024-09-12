package org.example.cinemabackend.ticketing.core.port.primary;

import java.awt.image.BufferedImage;

public interface QrCodeUseCases {
    byte[] convertBufferedImageToByteArray(BufferedImage qrImage);

    BufferedImage generateQrCodeImage(StringBuilder text);
}