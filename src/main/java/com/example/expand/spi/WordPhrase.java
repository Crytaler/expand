package com.example.expand.spi;

/**
 * TODO
 *
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2020/12/8 15:35
 * @see com.example.expand.spi
 */
public class WordPhrase implements PhraseDoc{

    @Override
    public void phrase() {
        System.out.println("解析word");
    }
}
