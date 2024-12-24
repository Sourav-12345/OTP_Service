package com.otpservice.controller;
import com.otpservice.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp();
        otpService.sendOtp(phoneNumber, otp);
        return "OTP sent successfully to " + phoneNumber;
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(phoneNumber, otp);

        if (isValid) {
            return "OTP is valid!";
        } else {
            return "Invalid or expired OTP.";
        }
    }
}
