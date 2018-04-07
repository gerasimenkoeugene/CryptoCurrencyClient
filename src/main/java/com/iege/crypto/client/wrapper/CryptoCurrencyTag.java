package com.iege.crypto.client.wrapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CryptoCurrencyTag {
  private String value;
  private String data;

  public CryptoCurrencyTag(String name, String data) {
    this.value = name;
    this.data = data;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
