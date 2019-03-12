package com.appdirect.demo.functions.domain.bo;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RawEvent {

  @NonNull
  private String referenceId;

  private Map<String, String> fields;
}
