package com.aliyun.or.airport.service;

import org.dom4j.DocumentException;

import java.io.IOException;

public interface WriterCvsService {

    boolean writeCvs(String xml) throws IOException, DocumentException;
}
