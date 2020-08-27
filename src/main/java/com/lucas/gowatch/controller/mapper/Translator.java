package com.lucas.gowatch.controller.mapper;

import org.modelmapper.ModelMapper;

public class Translator {
    // Match
    public static <Input, Output> Output translate(Input in, Class<Output> out){
        return (new ModelMapper()).map(in, out);
    }
}
