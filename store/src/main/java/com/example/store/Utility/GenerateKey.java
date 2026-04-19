package com.example.store.Utility;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;

public class GenerateKey {

    public static void main(String[] args){
        //cree un secret key et apres le transformer de 64 bit
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(base64Key);
    }
}
