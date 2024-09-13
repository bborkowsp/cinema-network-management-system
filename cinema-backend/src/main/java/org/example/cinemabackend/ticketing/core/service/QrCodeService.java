package org.example.cinemabackend.ticketing.core.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.example.cinemabackend.ticketing.core.port.primary.QrCodeUseCases;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
class QrCodeService implements QrCodeUseCases {
    private final static int QR_CODE_WIDTH = 200;
    private final static int QR_CODE_HEIGHT = 200;

    @Override
    public byte[] convertBufferedImageToByteArray(BufferedImage qrImage) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(qrImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Could not convert buffered image to byte array", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public BufferedImage generateQrCodeImage(StringBuilder text) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(String.valueOf(text), BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, hints);
        } catch (Exception e) {
            throw new IllegalStateException("Could not generate QR code", e);
        }
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
