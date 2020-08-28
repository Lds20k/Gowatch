package com.lucas.gowatch.controller.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Translator {
    // Associates attributes of instance and classes and return a new instance of this class that was argument
    public static <Input, Output> Output translate(Input in, Class<Output> out){
        return (new ModelMapper()).map(in, out);
    }

    // Associates attributes of instance of array and classes and return a new instance of this class that was argument
    public static <Input, Output> List<Output> translate(Iterable<Input> in, Class<Output> out){
        ArrayList<Output> list = new ArrayList<>();
        Iterator<Input> iterator = in.iterator();
        iterator.forEachRemaining(element -> list.add(Translator.translate(element, out)));
        return list;
    }


}
