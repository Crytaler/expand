package com.example.expand.spi;

import java.util.ServiceLoader;

/**
 * SPI机制（破坏双亲委派）
 *
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2020/12/8 15:46
 * @see com.example.expand.spi
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<PhraseDoc> load = ServiceLoader.load(PhraseDoc.class);
        for (PhraseDoc phraseDoc : load) {
//            phraseDoc.phrase();
            if (phraseDoc instanceof WordPhrase) {
                phraseDoc.phrase();
            }
        }
    }
}
