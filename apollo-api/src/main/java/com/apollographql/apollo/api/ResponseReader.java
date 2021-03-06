package com.apollographql.apollo.api;

import java.io.IOException;
import java.util.List;

/*
 * ResponseReader is an abstraction for reading GraphQL fields.
 */
public interface ResponseReader {

  String readString(ResponseField field) throws IOException;

  Integer readInt(ResponseField field) throws IOException;

  Long readLong(ResponseField field) throws IOException;

  Double readDouble(ResponseField field) throws IOException;

  Boolean readBoolean(ResponseField field) throws IOException;

  <T> T readObject(ResponseField field, ObjectReader<T> objectReader) throws IOException;

  <T> List<T> readList(ResponseField field, ListReader listReader) throws IOException;

  <T> T readCustomType(ResponseField.CustomTypeField field) throws IOException;

  <T> T readConditional(ResponseField.ConditionalTypeField field, ConditionalTypeReader<T> conditionalTypeReader)
      throws IOException;

  interface ObjectReader<T> {
    T read(ResponseReader reader) throws IOException;
  }

  interface ListReader<T> {
    T read(ListItemReader reader) throws IOException;
  }

  interface ConditionalTypeReader<T> {
    T read(String conditionalType, ResponseReader reader) throws IOException;
  }

  interface ListItemReader {

    String readString() throws IOException;

    Integer readInt() throws IOException;

    Long readLong() throws IOException;

    Double readDouble() throws IOException;

    Boolean readBoolean() throws IOException;

    <T> T readCustomType(ScalarType scalarType) throws IOException;

    <T> T readObject(ObjectReader<T> objectReader) throws IOException;
  }
}