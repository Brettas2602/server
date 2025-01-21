package br.ifgoiano.Projeto.Final.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class DozerMapper {
    private static ModelMapper mapper = new ModelMapper();
    
    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> originList, Class<D> destination ) {
        List<D> destinationObjects = new ArrayList<D>();

        for(O originObject : originList) {
            destinationObjects.add(mapper.map(originObject, destination));
        }

        return destinationObjects;
    }
}
