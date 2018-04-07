package com.iege.crypto.client.wrapper;


import java.util.List;

/**
 * Created by jonaspfeifer on 07.05.17.
 */
public class SuggestionWrapper {

  private List<CryptoCurrencyTag> suggestions;

  public List<CryptoCurrencyTag> getSuggestions() {
    return suggestions;
  }

  public void setSuggestions(List<CryptoCurrencyTag> suggestions) {
    this.suggestions = suggestions;
  }
}
