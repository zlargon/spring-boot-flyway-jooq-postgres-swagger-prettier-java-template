package com.zlargon.springdemo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.jooq.Record;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
public class MapperService {

  @Getter
  private ModelMapper mapper;

  public MapperService() {
    // http://modelmapper.org/user-manual/jooq-integration/
    mapper = new ModelMapper();
    mapper.getConfiguration()
      .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
      .addValueReader(new RecordValueReader());
  }

  public <T> T toObject(Class<T> targetClass, Record record) {
    return mapper.map(record, targetClass);
  }

  public <T> List<T> toList(Class<T> targetClass, List<Record> records) {
    return records
      .stream()
      .map(element -> mapper.map(element, targetClass))
      .collect(Collectors.toList());
  }
}
